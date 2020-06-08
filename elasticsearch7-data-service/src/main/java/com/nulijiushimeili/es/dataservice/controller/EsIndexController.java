package com.nulijiushimeili.es.dataservice.controller;

import com.nulijiushimeili.es.dataservice.entity.EsFieldSchema;
import com.nulijiushimeili.librariancommon.beans.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/******************************
 * @Project: librarian-parent
 * @FileName: EsController.java
 * @ClassName: EsController
 * @time 2020/5/11 20:46
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@Api(value = "es索引操作", tags = {"es索引操作"})
@Slf4j
@RestController
@RequestMapping(value = "/es/index")
public class EsIndexController {

    @Resource
    private RestHighLevelClient restHighLevelClient;


    @ApiOperation(value = "es创建索引(带数据结构)", notes = "es创建索引(带数据结构)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "indexName", value = "索引名称", dataType = "String"),
            @ApiImplicitParam(name = "fieldSchemaList", value = "模式信息", dataType = "List<EsFieldSchema>"),
    })
    @PutMapping(value = "/createIndexWithSchema")
    public ResultEntity createIndexWithSchema(@RequestParam String indexName, @RequestParam List<EsFieldSchema> fieldSchemaList) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("properties")
                    .startObject();

            for (EsFieldSchema esFieldSchema : fieldSchemaList) {
                // 日期格式化
                if ("DATE".equals(esFieldSchema.getFieldName().toUpperCase())) {
                    builder.field(esFieldSchema.getFieldName()).startObject().field("index", "true").
                            field("type", esFieldSchema.getFieldType())
                            .field("format", "strict_date_optional_time||epoch_millis").endObject();
                }
                builder.field(esFieldSchema.getFieldName()).startObject().field("index", "true").
                        field("type", esFieldSchema.getFieldType()).endObject();
            }
            builder.endObject()
                    .endObject();

            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            createIndexRequest.mapping(builder);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            if (acknowledged) {
                return ResultEntity.success();
            } else {
                return ResultEntity.error(500, "创建失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @ApiOperation(value = "es创建索引", notes = "es创建索引")
    @PutMapping(value = "/createIndex")
    public ResultEntity createIndex(@RequestParam String indexName) {
        try {

            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            if (acknowledged) {
                return ResultEntity.success();
            } else {
                return ResultEntity.error(500, "创建索引失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultEntity.error(500, "创建索引失败！");
    }


    @ApiOperation(value = "查询所有索引", notes = "查询所有索引")
    @GetMapping(value = "/listIndex")
    public ResultEntity<List<String>> listIndex() {

        try {
            // 构建请求,注意*号的写法
            GetIndexRequest getIndexRequest = new GetIndexRequest("*");

            // 构建获取所有索引的请求：org.elasticsearch.client.indices.GetIndexRequest
            GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);

            // 获取所有的索引
            String[] indices = getIndexResponse.getIndices();

            // 转化为list形式
            List<String> asList = Arrays.asList(indices);

            // 复制一下，不然不能追加
            List<String> res = new ArrayList<>(asList);
            return ResultEntity.success(res);
        } catch (Exception e) {

            log.error("获取所有索引失败：{}", e);
            throw new RuntimeException(e);
        }
    }


    @ApiOperation(value = "查询匹配索引", notes = "查询匹配索引")
    @GetMapping(value = "/listMatchIndex")
    public ResultEntity listIndexMatch(@RequestParam String matchIndexStr) {

        try {
            // 构建请求,注意*号的写法
            GetIndexRequest getIndexRequest = new GetIndexRequest(String.format("*%s*", matchIndexStr));

            // 构建获取所有索引的请求：org.elasticsearch.client.indices.GetIndexRequest
            GetIndexResponse getIndexResponse = restHighLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);

            // 获取所有的索引
            String[] indices = getIndexResponse.getIndices();

            // 转化为list形式
            List<String> asList = Arrays.asList(indices);

            // 复制一下，不然不能追加
            List<String> res = new ArrayList<>(asList);
            return ResultEntity.success(res);
        } catch (Exception e) {

            log.error("获取所有索引失败：{}", e);
            throw new RuntimeException(e);
        }
    }


    @ApiOperation(value = "es查询是否存在索引", notes = "es查询是否存在索引")
    @GetMapping(value = "/index/exists")
    public ResultEntity indexExists(@RequestParam String indexName) {
        boolean isExists = false;
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
            getIndexRequest.humanReadable(true);
            isExists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultEntity.success(isExists);
    }

    @ApiOperation(value = "es删除索引", notes = "es删除索引")
    @DeleteMapping(value = "/delete/index")
    public ResultEntity deleteIndex(@RequestParam String indexName) {
        boolean isDelete = false;
        try {
            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
            deleteIndexRequest.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN);
            AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
            isDelete = delete.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isDelete) {
            return ResultEntity.success();
        } else {
            return ResultEntity.error(500, "删除失败");
        }
    }


}
