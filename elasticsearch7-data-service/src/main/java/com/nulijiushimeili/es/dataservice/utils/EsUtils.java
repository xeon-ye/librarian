package com.nulijiushimeili.es.dataservice.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nulijiushimeili.librariancommon.beans.entity.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/******************************************************
 * @Program: cdr-voice-search
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-09 09:48
 * @Desc: TODO
 ********************************************************/


@Slf4j
public class EsUtils {


    /**
     * elasticsearch 查询操作
     *
     * @param restHighLevelClient
     * @param searchRequest
     * @return
     */
    public static ResultEntity doSearch(RestHighLevelClient restHighLevelClient, SearchRequest searchRequest) {
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            JSONArray jsonArray = new JSONArray();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                JSONObject jsonObject = JSON.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            return ResultEntity.success(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultEntity.error(500, "查询失败");
        }
    }

//
//    /**
//     * 构建cdr的查询条件
//     *
//     * @param cdr
//     * @return
//     */
//    public static List<QueryBuilder> buildCdrQueryConditions(CdrQueryConditionEntity cdr) {
//
//        List<QueryBuilder> queryBuilderList = new ArrayList<>();
//        //如果用name直接查询，其实是匹配name分词过后的索引查到的记录(倒排索引)；如果用name.keyword查询则是不分词的查询，正常查询到的记录
//        //范围查询, 电路号区间查询
//        if (cdr.getCicMin() != null && cdr.getCicMax() != null) {
//            RangeQueryBuilder cic = QueryBuilders.rangeQuery("cic").from(cdr.getCicMin()).to(cdr.getCicMax());
//            queryBuilderList.add(cic);
//        }
//        // 通话时长 区间查询
//        if (null != cdr.getNtalkperiodMin() && null != cdr.getNtalkperiodMax()) {
//            RangeQueryBuilder ntalkperiod = QueryBuilders.rangeQuery("ntalkperiod").from(cdr.getNtalkperiodMin()).to(cdr.getNtalkperiodMax());
//            queryBuilderList.add(ntalkperiod);
//        }
//        // 开始通话时间 区间查询
//        if (/*!"".equals(cdr.getStarttimeBegin()) && !"".equals(cdr.getStarttimeEnd()) &&*/ null != cdr.getStarttalktimeBegin() && null != cdr.getStarttalktimeEnd()) {
//            RangeQueryBuilder hjStartTime = QueryBuilders.rangeQuery("starttime").from(cdr.getStarttalktimeBegin()).format("yyyy-MM-dd HH:mm:ss").to(cdr.getStarttalktimeEnd()).format("yyyy-MM-dd HH:mm:ss");
//            queryBuilderList.add(hjStartTime);
//        }
//        // 精准查询 主叫号码
//        if (!"".equals(cdr.getCallernm()) && null != cdr.getCallernm()) {
//            TermQueryBuilder callernm = QueryBuilders.termQuery("callernm.keyword", cdr.getCallernm());
//            queryBuilderList.add(callernm);
//        }
//        // 精准查询 被叫号码
//        if (!"".equals(cdr.getCallednm()) && null != cdr.getCallednm()) {
//            TermQueryBuilder callednm = QueryBuilders.termQuery("callednm.keyword", cdr.getCallednm());
//            queryBuilderList.add(callednm);
//        }
//        // 局号
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder officenumber = QueryBuilders.termQuery("officenumber.keyword", cdr.getOfficenumber());
//            queryBuilderList.add(officenumber);
//        }
//        // JX 号
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder directionnumber = QueryBuilders.termQuery("directionnumber.keyword", cdr.getDirectionnumber());
//            queryBuilderList.add(directionnumber);
//        }
//
//        // 检出标识：
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder autoattrib1 = QueryBuilders.termQuery("autoattrib1.keyword", cdr.getAutoattrib1());
//            queryBuilderList.add(autoattrib1);
//        }
//
//        // MB 号
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder modulenumber = QueryBuilders.termQuery("modulenumber.keyword", cdr.getModulenumber());
//            queryBuilderList.add(modulenumber);
//        }
//
//        // LJ标识：
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder isholdup = QueryBuilders.termQuery("isholdup.keyword", cdr.getIsholdup());
//            queryBuilderList.add(isholdup);
//        }
//
//        // 策略标识
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder ccsflag = QueryBuilders.termQuery("ccsflag.keyword", cdr.getCcsflag());
//            queryBuilderList.add(ccsflag);
//        }
//
//        // 来 QH 标识
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder callinflag = QueryBuilders.termQuery("callinflag.keyword", cdr.getCallinflag());
//            queryBuilderList.add(callinflag);
//        }
//
//        // 信誉度查询结果
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder creditworthinessresult = QueryBuilders.termQuery("creditworthinessresult.keyword", cdr.getCreditworthinessresult());
//            queryBuilderList.add(creditworthinessresult);
//        }
//
//        // ZJ 地区名称 （大陆，其他）
//        if (!"".equals(cdr.getOfficenumber()) && null != cdr.getOfficenumber()) {
//            TermQueryBuilder caller_country_name = QueryBuilders.termQuery("caller_country_name.keyword", cdr.getCaller_country_name());
//            queryBuilderList.add(caller_country_name);
//        }
//
//        return queryBuilderList;
//    }

}
