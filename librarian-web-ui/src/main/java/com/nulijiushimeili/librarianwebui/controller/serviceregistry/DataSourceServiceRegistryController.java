package com.nulijiushimeili.librarianwebui.controller.serviceregistry;

import com.nulijiushimeili.librariancommon.beans.entity.DataServiceInfoEntity;
import com.nulijiushimeili.librariancommon.utils.MyDateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-10 20:02
 * @Desc: TODO
 ********************************************************/

@Api(tags = "服务注册接口")
@Slf4j
@RestController
@RequestMapping(value = "/datasourceServiceRegistry")
public class DataSourceServiceRegistryController {

    // 数据源的数据治理服务注册
    // todo 定时删除，等待服务定时注册，保证服务可用
    private static Set<String> datasourceTypeList = new TreeSet<>();


    @ApiOperation(value = "注册数据源服务", notes = "注册数据源服务")
    @PostMapping(value = "/registry")
    public void registry(String datasourceType){
        datasourceTypeList.add(datasourceType);
    }

    @ApiOperation(value = "查询数据源服务列表", notes = "查询数据源服务列表")
    @GetMapping(value = "/getAllDatasourceTypeList")
    public Set<String> getAllDatasourceTypeList(){
        return datasourceTypeList;
    }


}
