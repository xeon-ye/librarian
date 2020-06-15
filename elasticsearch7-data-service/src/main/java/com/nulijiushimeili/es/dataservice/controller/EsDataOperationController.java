package com.nulijiushimeili.es.dataservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nulijiushimeili.es.dataservice.entity.User;
import com.nulijiushimeili.es.dataservice.utils.EsUtils;
import com.nulijiushimeili.librariancommon.beans.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.util.CollectionUtils;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-08 14:52
 * @Desc: TODO
 ********************************************************/

@Api(value = "es数据操作", tags = {"es数据操作"})
@RestController
@Slf4j
@RequestMapping(value = "/es/data")
public class EsDataOperationController {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * @param indexName 索引名称
     * @return 数量
     * @throws IOException 异常
     */
    @ApiOperation(value = "查询文档个数", notes = "查询文档个数")
    @GetMapping(value = "/docCount")
    public ResultEntity<Object> docCount(@RequestParam String indexName) throws IOException {

        // 构建请求：org.elasticsearch.client.core.CountRequest
        CountRequest request = new CountRequest(indexName);

        // 响应结果
        CountResponse response = restHighLevelClient.count(request, RequestOptions.DEFAULT);

        // 返回文档个数，还可以返回其他一些如分片信息，副本信息等，
        return ResultEntity.success(response.getCount());
    }

    @ApiOperation(value = "普通查询", notes = "普通查询")
    @GetMapping(value = "/normalSearch")
    public ResultEntity normalSearch(@RequestParam String indexName) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        return EsUtils.doSearch(restHighLevelClient, searchRequest);
    }



    @ApiOperation(value = "es插入数据", notes = "es插入数据")
    @PutMapping(value = "/insertData")
    public ResultEntity insertUser(@RequestBody User user, @RequestParam String indexName) {
        IndexRequest indexRequest = new IndexRequest(indexName);
        String userJson = JSONObject.toJSONString(user);
        indexRequest.source(userJson, XContentType.JSON);
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (indexResponse != null) {
                String id = indexResponse.getId();
                String index = indexResponse.getIndex();
                long version = indexResponse.getVersion();
                log.info("index:{},id:{}", index, id);
                if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                    log.info("新增文档成功!" + index + "-" + id + "-" + version);
                    return ResultEntity.success();
                } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                    log.info("修改文档成功!");
                    return ResultEntity.error(500, "插入数据失败！");
                }
                // 分片处理信息
                ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
                if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                    log.info("分片处理信息.....");
                }
                // 如果有分片副本失败，可以获得失败原因信息
                if (shardInfo.getFailed() > 0) {
                    for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                        String reason = failure.reason();
                        log.error("副本失败原因：" + reason);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @ApiOperation(value = "es条件查询", notes = "es条件查询")

    @RequestMapping(value = "/queryData", method = RequestMethod.GET)
    public ResultEntity testESFind(@RequestParam String indexName) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //如果用name直接查询，其实是匹配name分词过后的索引查到的记录(倒排索引)；如果用name.keyword查询则是不分词的查询，正常查询到的记录
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("birthday").from("1991-01-01").to("2010-10-10").format("yyyy-MM-dd");//范围查询
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name.keyword", name);//精准查询
        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("name.keyword", "张");//前缀查询
//        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("name.keyword", "*三");//通配符查询
//        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "三");//模糊查询
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("age");//按照年龄排序
        fieldSortBuilder.sortMode(SortMode.MIN);//从小到大排序

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(rangeQueryBuilder).should(prefixQueryBuilder);//and or  查询

        sourceBuilder.query(boolQueryBuilder).sort(fieldSortBuilder);//多条件查询
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            JSONArray jsonArray = new JSONArray();
            for (SearchHit hit : hits) {
                String sourceAsString = hit.getSourceAsString();
                JSONObject jsonObject = JSON.parseObject(sourceAsString);
                jsonArray.add(jsonObject);
            }
            return ResultEntity.success();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultEntity.error(500, "查询失败");
        }
    }

    @ApiOperation(value = "es聚合查询", notes = "es聚合查询")
    @GetMapping(value = "/aggQuery")
    public ResultEntity testESFindAgg(@RequestParam String indexName) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("by_age").field("age");
        sourceBuilder.aggregation(termsAggregationBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            Map<String, Aggregation> stringAggregationMap = aggregations.asMap();
            ParsedLongTerms parsedLongTerms = (ParsedLongTerms) stringAggregationMap.get("by_age");
            List<? extends Terms.Bucket> buckets = parsedLongTerms.getBuckets();
            Map<Integer, Long> map = new HashMap<>();
            for (Terms.Bucket bucket : buckets) {
                long docCount = bucket.getDocCount();//个数
                Number keyAsNumber = bucket.getKeyAsNumber();//年龄
                System.err.println(keyAsNumber + "岁的有" + docCount + "个");
                map.put(keyAsNumber.intValue(), docCount);
            }
            return ResultEntity.success();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "es更新数据", notes = "es测试更新数据")
    @PostMapping(value = "/updateData")
    public ResultEntity testESUpdate(@RequestParam String indexName, @RequestParam String id, @RequestParam Double money) {
        UpdateRequest updateRequest = new UpdateRequest(indexName, id);
        Map<String, Object> map = new HashMap<>();
        map.put("money", money);
        updateRequest.doc(map);
        try {
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                return ResultEntity.success();
            } else {
                return ResultEntity.error(500, "删除失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultEntity.error(500, "删除异常");
        }
    }

    @ApiOperation(value = "es删除数据", notes = "es删除数据")
    @DeleteMapping(value = "/deleteData")
    public ResultEntity testESDelete(@RequestParam String indexName, @RequestParam String id) {
        DeleteRequest deleteRequest = new DeleteRequest(indexName);
        deleteRequest.id(id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
                return ResultEntity.error(500, "删除失败");
            } else {
                return ResultEntity.success();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultEntity.error(500, "删除异常");
        }
    }



    /**
     *
     * elasticsearch 通用查询数据demo
     * @param index
     * @param from
     * @param size
     * @param where
     * @param sortFieldsToAsc
     * @param includeFields
     * @param excludeFields
     * @param timeOut
     * @return
     */
    public List<Map<String, Object>> searchIndex(String index, int from, int size, Map<String, Object> where,
                                                 Map<String, Boolean> sortFieldsToAsc,
                                                 String[] includeFields,
                                                 String[] excludeFields,
                                                 int timeOut) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        try {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            //条件
            if (where != null && !where.isEmpty()) {
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                where.forEach((k, v) -> {
                    if (v instanceof Map) {
                        //范围选择map  暂定时间
                        Map<String, Date> mapV = (Map<String, Date>) v;
                        if (mapV != null) {
                            boolQueryBuilder.must(
                                    QueryBuilders.rangeQuery(k).
                                            gte(format.format(mapV.get("start"))).
                                            lt(format.format(mapV.get("end"))));
                        }
                    } else {
                        //普通模糊匹配
                        boolQueryBuilder.must(QueryBuilders.wildcardQuery(k, v.toString()));
                    }
                });
                sourceBuilder.query(boolQueryBuilder);
            }

            //分页
            from = from <= -1 ? 0 : from;
            size = size >= 1000 ? 1000 : size;
            size = size <= 0 ? 15 : size;
            sourceBuilder.from(from);
            sourceBuilder.size(size);

            //超时
            sourceBuilder.timeout(new TimeValue(timeOut, TimeUnit.SECONDS));

            //排序
            if (sortFieldsToAsc != null && !sortFieldsToAsc.isEmpty()) {
                sortFieldsToAsc.forEach((k, v) -> {
                    sourceBuilder.sort(new FieldSortBuilder(k).order(v ? SortOrder.ASC : SortOrder.DESC));
                });
            } else {
                sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
            }

            //返回和排除列
            if (!CollectionUtils.isEmpty(includeFields) || !CollectionUtils.isEmpty(excludeFields)) {
                sourceBuilder.fetchSource(includeFields, excludeFields);
            }

            SearchRequest rq = new SearchRequest();
            //索引
            rq.indices(index);
            //各种组合条件
            rq.source(sourceBuilder);

            //请求
            System.out.println(rq.source().toString());
            SearchResponse rp = restHighLevelClient.search(rq,RequestOptions.DEFAULT);

            //解析返回
            if (rp.status() != RestStatus.OK || rp.getHits().getTotalHits().value <= 0) {
                return Collections.emptyList();
            }

            //获取source
            return Arrays.stream(rp.getHits().getHits()).map(b -> {
                return b.getSourceAsMap();
            }).collect(Collectors.toList());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }


    /**
     * 向ES中写入单条数据
     * @param indexName
     * @param f
     */
    public void writeFraudCdr2Es(String indexName, Object f) {
        try {
            IndexRequest request = new IndexRequest("post");
            request.index(indexName).id(String.valueOf(UUID.randomUUID())).source(JSON.toJSONString(f), XContentType.JSON);
            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            log.info("elastic 索引新增成功");
        } catch (Exception e) {
            log.error("索引数据变更失败:{}", e);
        }

    }


    /**
     * es 批量插入数据
     *
     * @param indexName
     * @param dataList
     * @return
     */
    public BulkResponse batchWrite2Es(String indexName, List<Object> dataList) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            IndexRequest request = null;
            for (Object data : dataList) {
                request = new IndexRequest("post");
                request.index(indexName).id(String.valueOf(UUID.randomUUID())).source(JSON.toJSONString(data), XContentType.JSON);
                bulkRequest.add(request);
            }
            log.info("插入数据条数：" + dataList.size());
            BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

            return response;
        } catch (Exception e) {
            log.error("批量插入索引失败:{}", e);
            e.printStackTrace();
        }
        return null;
    }


}
