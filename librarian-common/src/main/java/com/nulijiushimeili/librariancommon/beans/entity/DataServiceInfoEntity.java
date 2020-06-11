package com.nulijiushimeili.librariancommon.beans.entity;

import com.nulijiushimeili.librariancommon.utils.MyDateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-11 15:37
 * @Desc:         注册数据源服务，提交的数据源服务相关信息
 ********************************************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataServiceInfoEntity {

    /**
     *  数据源服务名称
     */
    private String datasourceType;

    /**
     * 注册数据源服务的时间，用于判断数据源服务心跳
     */
    private Date registryTime;
}
