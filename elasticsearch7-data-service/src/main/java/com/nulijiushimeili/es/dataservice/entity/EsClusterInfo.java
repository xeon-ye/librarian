package com.nulijiushimeili.es.dataservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/******************************************************
 * @Program: librarian-parent
 * @Author: 努力就是魅力
 * @QQ : 734131757
 * @DateTime: 2020-06-10 16:16
 * @Desc: TODO
 ********************************************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EsClusterInfo {

    private String clusterName;
    private String clusterUUID;
    private String version ;
    private String nodeName;
    private String tagLine;


}
