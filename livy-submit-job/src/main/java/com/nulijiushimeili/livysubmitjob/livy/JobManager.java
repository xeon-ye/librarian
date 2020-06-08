package com.nulijiushimeili.livysubmitjob.livy;


import com.fasterxml.jackson.databind.JsonNode;

import com.nulijiushimeili.livysubmitjob.livy.bean.LivyBatchJob;
import com.nulijiushimeili.livysubmitjob.livy.bean.LivyJobEntity;
import com.nulijiushimeili.livysubmitjob.livy.client.AbstractRetrofitClient;
import com.nulijiushimeili.livysubmitjob.livy.client.LivyBatch;
import com.nulijiushimeili.livysubmitjob.livy.utils.JsonUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import retrofit2.Call;

/**
 * 使用livy提交spark作业
 */
@Component
public class JobManager extends AbstractRetrofitClient {

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    private LivyBatch livyApi = createService(LivyBatch.class);
    private LivyBatch yarnApp = createYarnService(LivyBatch.class);
    /**
     * 提交spark作业
     * @param livyJobEntity
     * @return spark作业id
     */
    public LivyBatchJob createBatchJob(LivyJobEntity livyJobEntity) {
        if (StringUtils.isEmpty(livyJobEntity.className) || StringUtils.isEmpty(livyJobEntity.file)) {
            throw new IllegalArgumentException("jobParameters.className,file不能为null");
        }
        String tojson = JsonUtil.toJson(livyJobEntity);
        Call<LivyBatchJob> call = livyApi.createBatchJob(RequestBody.create(JSON, tojson));
        return executeCall(call);
    }

    /**
     * 根据applicationId获取application信息
     * @param applicationId
     * @return
     */
    public JsonNode getAppByapplicationId(String applicationId) {

        Call<JsonNode> call = yarnApp.getApps(applicationId);
        return executeCall(call);
    }

    public JsonNode killAppJob(String applicationId){
        String param="{\"state\":\"KILLED\"}";
        Call<JsonNode> call = yarnApp.killAppJob(applicationId, RequestBody.create(JSON, param));
        return executeCall(call);
    }

}
