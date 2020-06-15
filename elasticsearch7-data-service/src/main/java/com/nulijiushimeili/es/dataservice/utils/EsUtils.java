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
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

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


    /**
     * 根据布尔条件进行查询
     * @param boolQueryBuilder
     * @return
     */
    public static SearchResponse boolQuery(RestHighLevelClient restHighLevelClient, String indexName, BoolQueryBuilder boolQueryBuilder) {
        try {

            SearchRequest searchRequest = new SearchRequest(indexName);

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.size(100);
            sourceBuilder.query(boolQueryBuilder);
            log.info(sourceBuilder.toString());
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
            searchResponse.getHits().forEach(message -> {
                try {
                    String sourceAsString = message.getSourceAsString();
                    log.info(sourceAsString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't get Detail");
        }
    }

    /**
     * 单条件检索
     * @param fieldKey
     * @param fieldValue
     * @return
     */
    public static MatchPhraseQueryBuilder uniqueMatchQuery(String fieldKey,String fieldValue){
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery(fieldKey,fieldValue);
        return matchPhraseQueryBuilder;
    }

    /**
     * 多条件检索并集，适用于搜索比如包含腾讯大王卡，滴滴大王卡的用户
     * @param fieldKey
     * @param queryList
     * @return
     */
    public static BoolQueryBuilder orMatchUnionWithList(String fieldKey,List<String> queryList){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (String fieldValue : queryList){
            boolQueryBuilder.should(QueryBuilders.matchPhraseQuery(fieldKey,fieldValue));
        }
        return boolQueryBuilder;
    }

    /**
     * 范围查询，左右都是闭集
     * @param fieldKey
     * @param start
     * @param end
     * @return
     */
    public static RangeQueryBuilder rangeMathQuery(String fieldKey,String start,String end){
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(fieldKey);
        rangeQueryBuilder.gte(start);
        rangeQueryBuilder.lte(end);
        return rangeQueryBuilder;
    }

    /**
     * 根据中文分词进行查询
     * @param fieldKey
     * @param fieldValue
     * @return
     */
    public static MatchQueryBuilder matchQueryBuilder(String fieldKey,String fieldValue){
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(fieldKey,fieldValue).analyzer("ik_smart");
        return matchQueryBuilder;
    }


}
