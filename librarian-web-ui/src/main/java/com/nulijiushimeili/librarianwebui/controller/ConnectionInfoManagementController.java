package com.nulijiushimeili.librarianwebui.controller;

import com.nulijiushimeili.librarian.beans.entity.ConnectionInfo;
import com.nulijiushimeili.librarian.beans.result.RequestEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/******************************
 * Project: librarian-parent
 * FileName: ConnectionInfoManagementController.java
 * ClassName: ConnectionInfoManagementController
 * time 2020/5/16 0:01
 * version 1.00
 * author nulijiushimeili
 * Description:  数据源管理
 *
 * Restful API 接口设计规范
 *      GET /zoos：列出所有动物园
 *      POST /zoos：新建一个动物园
 *      GET /zoos/ID：获取某个指定动物园的信息
 *      PUT /zoos/ID：更新某个指定动物园的信息（提供该动物园的全部信息）
 *      PATCH /zoos/ID：更新某个指定动物园的信息（提供该动物园的部分信息）
 *      DELETE /zoos/ID：删除某个动物园
 *      GET /zoos/ID/animals：列出某个指定动物园的所有动物
 *      DELETE /zoos/ID/animals/ID：删除某个指定动物园的指定动物
 ******************************/

@Api(value = "数据源管理", tags = "数据源管理")
@Slf4j
@RestController
@RequestMapping(value = "/conn")
public class ConnectionInfoManagementController {

    @ApiOperation(value = "添加MySQL数据源", notes="添加MySQL数据源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "查询起始时间",   dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "查询结束时间",   dataType = "Date"),
            @ApiImplicitParam(name = "province", value = "省份",  dataType = "String"),
            @ApiImplicitParam(name = "city", value = "城市",   dataType = "String"),
            @ApiImplicitParam(name = "begin3", value = "前三位",   dataType = "Integer"),
            @ApiImplicitParam(name = "mid4", value = "中四位",   dataType = "Integer"),
            @ApiImplicitParam(name = "sp", value = "运营商",  dataType = "String")
    })
    @GetMapping(value = "/allResourceTypes")
    public RequestEntity queryAllResourceTypes(){


        return RequestEntity.success();
    }


    @ApiOperation(value = "添加MySQL数据源", notes="添加MySQL数据源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "查询起始时间",   dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "查询结束时间",   dataType = "Date"),
            @ApiImplicitParam(name = "province", value = "省份",  dataType = "String"),
            @ApiImplicitParam(name = "city", value = "城市",   dataType = "String"),
            @ApiImplicitParam(name = "begin3", value = "前三位",   dataType = "Integer"),
            @ApiImplicitParam(name = "mid4", value = "中四位",   dataType = "Integer"),
            @ApiImplicitParam(name = "sp", value = "运营商",  dataType = "String")
    })
    @GetMapping(value = "/datasourceTypes")
    public RequestEntity queryDatasourceTypeFromDb(){

        return RequestEntity.success();
    }



    @GetMapping(value = "/getConnNameByDatasourceType")
    public RequestEntity getConnNameByDatasourceType(@RequestParam("datasourceType") String datasourceType){

        return RequestEntity.success();
    }



    @ApiOperation(value = "添加MySQL数据源", notes="添加MySQL数据源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "查询起始时间",   dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "查询结束时间",   dataType = "Date"),
            @ApiImplicitParam(name = "province", value = "省份",  dataType = "String"),
            @ApiImplicitParam(name = "city", value = "城市",   dataType = "String"),
            @ApiImplicitParam(name = "begin3", value = "前三位",   dataType = "Integer"),
            @ApiImplicitParam(name = "mid4", value = "中四位",   dataType = "Integer"),
            @ApiImplicitParam(name = "sp", value = "运营商",  dataType = "String")
    })
    @PutMapping(value = "/addMysqlConnInfo")
    public RequestEntity addMysqlConnInfo(ConnectionInfo connectionInfo){


        return RequestEntity.success();
    }

    @ApiOperation(value = "测试MySQL数据源连接", notes="测试MySQL数据源连接")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "查询起始时间",   dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "查询结束时间",   dataType = "Date"),
            @ApiImplicitParam(name = "province", value = "省份",  dataType = "String"),
            @ApiImplicitParam(name = "city", value = "城市",   dataType = "String"),
            @ApiImplicitParam(name = "begin3", value = "前三位",   dataType = "Integer"),
            @ApiImplicitParam(name = "mid4", value = "中四位",   dataType = "Integer"),
            @ApiImplicitParam(name = "sp", value = "运营商",  dataType = "String")
    })
    @PostMapping(value = "/testMysqlConnection")
    public RequestEntity testMysqlConnection(){

        return RequestEntity.success();
    }


    @GetMapping(value = "/connections")
    public RequestEntity conditionSearchConnectionInfo(ConnectionInfo connectionInfo){

        return RequestEntity.success();
    }


    @GetMapping(value = "/connection/{id}")
    public RequestEntity getConnectionInfoById(@PathVariable("id") Integer id){

        return RequestEntity.success();
    }


    @PatchMapping(value = "/connection/{id}")
    public RequestEntity updateConnectionInfoById(@PathVariable("id") Integer id){

        return RequestEntity.success();
    }


}
