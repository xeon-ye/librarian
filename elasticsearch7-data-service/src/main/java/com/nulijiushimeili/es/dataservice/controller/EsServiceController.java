package com.nulijiushimeili.es.dataservice.controller;

import com.nulijiushimeili.es.dataservice.entity.EsClusterInfo;
import com.nulijiushimeili.librariancommon.beans.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.Build;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-08 14:58
 * @Desc: TODO
 ********************************************************/


@Api(value = "ES 服务信息", tags = {"ES 服务信息"})
@RestController
@RequestMapping(value = "/es/service")
public class EsServiceController {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @ApiOperation(value = "获取ES集群信息", notes = "获取ES集群信息")
    @GetMapping(value = "/getEsClusterInfo")
    public ResultEntity getEsClusterInfo (){
        MainResponse response = null;
        try {
            response = restHighLevelClient.info(RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EsClusterInfo esClusterInfo = new EsClusterInfo();
        //返回集群的各种信息
        if (response != null) {
            //集群名称
            String clusterName = response.getClusterName();
            esClusterInfo.setClusterName(clusterName);
            //群集的唯一标识符
            String clusterUuid = response.getClusterUuid();
            esClusterInfo.setClusterUUID(clusterUuid);
            //已执行请求的节点的名称
            String nodeName = response.getNodeName();
            esClusterInfo.setNodeName(nodeName);
            //已执行请求的节点的版本
            String version  = response.getVersion().getNumber();
            esClusterInfo.setVersion(version);
            //
            String tagline = response.getTagline();
            esClusterInfo.setTagLine(tagline);

        }
        return ResultEntity.success(esClusterInfo);

    }


}
