package com.nulijiushimeili.librarianwebui.controller;

import com.nulijiushimeili.librarian.beans.entity.ConnectionInfo;
import com.nulijiushimeili.librarian.beans.result.RequestEntity;
import com.nulijiushimeili.librarianwebui.service.IConnectionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IConnectionInfoService connectionInfoService;


    @GetMapping(value = "/allResourceTypes")
    public RequestEntity queryAllResourceTypes(){


        return RequestEntity.success();
    }



    @GetMapping(value = "/datasourceTypes")
    public RequestEntity queryDatasourceTypeFromDb(){

        return RequestEntity.success();
    }



    @GetMapping(value = "/getConnNameByDatasourceType")
    public RequestEntity getConnNameByDatasourceType(@RequestParam("datasourceType") String datasourceType){

        return RequestEntity.success();
    }


    /**
     * 添加MySQL数据源信息
     * @param connectionInfo
     * @return
     */
    @ApiOperation(value = "测试MySQL数据源连接", notes="测试MySQL数据源连接")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "connName", value = "连接名称",   dataType = "String",required = true),
            @ApiImplicitParam(name = "datasourceType", value = "数据源类型",   dataType = "String",required = true),
            @ApiImplicitParam(name = "datasourceVersion", value = "数据源版本号",  dataType = "String",required = true),
            @ApiImplicitParam(name = "username", value = "用户名",   dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码",   dataType = "String", required = true),
            @ApiImplicitParam(name = "remark", value = "备注",   dataType = "String"),
    })
    @PutMapping(value = "/addMysqlConnInfo")
    public RequestEntity addMysqlConnInfo(ConnectionInfo connectionInfo){
        // todo 仍然需要测试连接可用性


        // 插入连接信息
        int res = connectionInfoService.addConnectionInfo(connectionInfo);

        return RequestEntity.success(res > 0);
    }


    /**
     * 测试MySQL连接
     * @return
     */
    @ApiOperation(value = "测试MySQL数据源连接", notes="测试MySQL数据源连接")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "connName", value = "连接名称",   dataType = "String",required = true),
            @ApiImplicitParam(name = "datasourceType", value = "数据源类型",   dataType = "String",required = true),
            @ApiImplicitParam(name = "datasourceVersion", value = "数据源版本号",  dataType = "String",required = true),
            @ApiImplicitParam(name = "username", value = "用户名",   dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码",   dataType = "String", required = true),
            @ApiImplicitParam(name = "remark", value = "备注",   dataType = "String"),
    })
    @PostMapping(value = "/testMysqlConnection")
    public RequestEntity testMysqlConnection(ConnectionInfo connectionInfo){

        return RequestEntity.success();
    }


    @ApiOperation(value = "条件查询数据源连接信息", notes="条件查询数据源连接信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "connName", value = "连接名称",   dataType = "String"),
            @ApiImplicitParam(name = "datasourceType", value = "数据源类型",   dataType = "String"),
            @ApiImplicitParam(name = "datasourceVersion", value = "数据源版本号",  dataType = "String"),
    })
    @GetMapping(value = "/connections")
    public RequestEntity conditionSearchConnectionInfo(ConnectionInfo connectionInfo){

        return RequestEntity.success();
    }


    /**
     * 查询某个连接的连接信息
     * @param id
     * @return
     */
    @GetMapping(value = "/connection/{id}")
    public RequestEntity getConnectionInfoById(@PathVariable("id") Integer id){

        return RequestEntity.success();
    }


    /**
     * 修改连接信息
     * @param id
     * @return
     */
    @PatchMapping(value = "/connection/{id}")
    public RequestEntity updateConnectionInfoById(@PathVariable("id") Integer id){

        return RequestEntity.success();
    }


}
