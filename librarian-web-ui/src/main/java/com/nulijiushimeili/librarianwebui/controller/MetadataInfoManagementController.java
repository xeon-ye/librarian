package com.nulijiushimeili.librarianwebui.controller;

import com.nulijiushimeili.librariancommon.beans.BaseMetadataEntity;
import com.nulijiushimeili.librariancommon.beans.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/******************************
 * @Project: librarian
 * @FileName: MetadataInfoManagementController.java
 * @ClassName: MetadataInfoManagementController
 * @time 2020/5/12 0:06
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/


@Api(value = "", tags = "")
@RestController
@RequestMapping(value = "/metadataInfo")
public class MetadataInfoManagementController {


    @ApiOperation(value = "添加元数据信息", notes="添加元数据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "查询起始时间",   dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "查询结束时间",   dataType = "Date"),
            @ApiImplicitParam(name = "province", value = "省份",  dataType = "String"),
            @ApiImplicitParam(name = "city", value = "城市",   dataType = "String"),
            @ApiImplicitParam(name = "begin3", value = "前三位",   dataType = "Integer"),
            @ApiImplicitParam(name = "mid4", value = "中四位",   dataType = "Integer"),
            @ApiImplicitParam(name = "sp", value = "运营商",  dataType = "String")
    })
    @PutMapping(value = "/addMetadataInfo")
    public ResultEntity addMetadataInfo(BaseMetadataEntity baseMetadataEntity){

        return ResultEntity.success();
    }



    @ApiOperation(value = "查询元数据信息", notes="条件查询所有的元数据信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "beginTime", value = "查询起始时间",   dataType = "Date"),
            @ApiImplicitParam(name = "endTime", value = "查询结束时间",   dataType = "Date"),
            @ApiImplicitParam(name = "province", value = "省份",  dataType = "String"),
            @ApiImplicitParam(name = "city", value = "城市",   dataType = "String"),
            @ApiImplicitParam(name = "begin3", value = "前三位",   dataType = "Integer"),
            @ApiImplicitParam(name = "mid4", value = "中四位",   dataType = "Integer"),
            @ApiImplicitParam(name = "sp", value = "运营商",  dataType = "String")
    })
    @GetMapping(value = "/metadataInfos")
    public ResultEntity conditionSearchMetadataInfos(HttpServletRequest request){

        return ResultEntity.success();
    }
}
