package com.nulijiushimeili.livysubmitjob.livy.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nulijiushimeili.livysubmitjob.livy.bean.OozieYarnWorkflowBean;
import net.sf.json.JSONObject;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create by liush on 2018-4-21
 */
public class JsonUtil {
    private static ObjectMapper objectMapper;
    public static  String toJson(Object model) {
        String json = null;

        try {
            // ADD ROOT
            // Setting SerializationConfig.Feature.WRAP_ROOT_VALUE at mapper
            // did not read annotated label properly, use withRootName
            objectMapper =  getObjectMapper();
            json = objectMapper.writeValueAsString(model);
            // ApiKey needs to be wrapped in a root node without the array
            // container, hack the standards!
        } catch (IOException e) {
            throw new RuntimeException("Cannot parse model to object",
                    e);
        }
        return json;
    }

    public static <T> List<T> toObjects(
            String body, Class<T> clazz) throws JsonParseException,
            JsonMappingException, IOException {
        List<T> objs;
        CollectionType collectionType = TypeFactory.defaultInstance()
                .constructCollectionType(ArrayList.class, clazz);
        try {
            objs = getObjectMapper().readValue(body, collectionType);
        } catch (IOException e) {
            throw new RuntimeException(String.format(
                    "转换失败 [%s] to %s.", body, clazz), e);
        }
        return objs;
    }
    public static <T> T toObject(String body,
                                 Class<T> clazz) {
        T obj;
        try {
            obj = (T) getObjectMapper().readValue(body, clazz);
        } catch (IOException e) {
            throw new RuntimeException(String.format(
                    "Unable to parse [%s] to %s.", body, clazz), e);
        }
        return obj;
    }
    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            ObjectMapper retval = new ObjectMapper();
            retval.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            retval.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            retval.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            retval.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
            retval.configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH,false);
            retval.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, false);
            objectMapper = retval;
        }
        return objectMapper;
    }


    public static List<OozieYarnWorkflowBean> sendJSONPost(String url, String param){

        RestTemplate restTemplate = new RestTemplate();
        List<OozieYarnWorkflowBean> resultList = new ArrayList();
        MultiValueMap<String, Object> vars = new LinkedMultiValueMap<>();
        vars.add("userName", param);
        Map map = restTemplate.postForObject(url, vars, Map.class);

        JSONObject jsonObject = JSONObject.fromObject(map);
        String msg = jsonObject.getJSONObject("resultInfo").get("isSuccess").toString();
        if(!"true".equals(msg)){
            return resultList;
        }
        JSONObject userAuths = jsonObject.getJSONObject("rspInfo")
                .getJSONObject("authList").getJSONObject("userAuths");
        Set<String> keySet = userAuths.keySet();
        String result = "null";//最终结果
        for (String s : keySet) {
            if(!("admin".equals(s))){//不能是admin用户
                Set<String> set = userAuths.getJSONObject(s).keySet();
                JSONObject jsonElement = userAuths.getJSONObject(s);
                for (String key : set) {
                    String v2 = userAuths.getJSONObject(s).get(key).toString();
                    List v3 = (List)com.alibaba.fastjson.JSONArray.parseArray(v2);
                    for (Object vv : v3) {
                        JSONObject json1 = JSONObject.fromObject(vv);

                        Object json2 = json1.get("HDFS权限:1");
                        if(json2 != null){
                            for (String keyIn : set) {
                                if(keyIn.contains("ResourceAuths")){
                                    String vv2 = userAuths.getJSONObject(s).get(keyIn).toString();//[{"WorkFlow权限:7":["suspend","restart","kill"]}]
                                    List vv3 = (List) com.alibaba.fastjson.JSONArray.parseArray(vv2);//"HDFS权限:1": ["Read","Write","Execute"]
                                    for (Object hdfs : vv3) {
                                        JSONObject hdfsObject = JSONObject.fromObject(hdfs);
                                        Set<String> hdfsSet = hdfsObject.keySet();
                                        for (String hdfsKey1 : hdfsSet) {
                                            OozieYarnWorkflowBean oyw1 = new OozieYarnWorkflowBean();
                                            oyw1.setType("hdfs");
                                            oyw1.setUserName(s.toString());
                                            oyw1.setRouteValue(hdfsKey1);
                                            resultList.add(oyw1);
                                        }
                                    }
                                }
                            }
                        }

                        Object json3 = json1.get("Yarn权限:6");
                        if(json3 != null){

                            String[] str = key.split(":");
                            String backgroundUser = str[0];
                            OozieYarnWorkflowBean backgroundUserBean = new OozieYarnWorkflowBean();
                            backgroundUserBean.setUserName(backgroundUser);
                            backgroundUserBean.setType("backgroundUser");
                            resultList.add(backgroundUserBean);

                            for (String keyIn : set) {
                                if(keyIn.contains("ResourceAuths")){
                                    String vv2 = userAuths.getJSONObject(s).get(keyIn).toString();//[{"WorkFlow权限:7":["suspend","restart","kill"]}]
                                    List vv3 = (List) com.alibaba.fastjson.JSONArray.parseArray(vv2);//"HDFS权限:1": ["Read","Write","Execute"]
                                    for (Object hdfs : vv3) {
                                        JSONObject hdfsObject = JSONObject.fromObject(hdfs);
                                        Set<String> hdfsSet = hdfsObject.keySet();
                                        for (String hdfsKey1 : hdfsSet) {
                                            OozieYarnWorkflowBean oyw2 = new OozieYarnWorkflowBean();
                                            oyw2.setType("yarn");
                                            oyw2.setUserName(s.toString());
                                            oyw2.setQueue(hdfsKey1);
                                            resultList.add(oyw2);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return resultList;
    }

}
