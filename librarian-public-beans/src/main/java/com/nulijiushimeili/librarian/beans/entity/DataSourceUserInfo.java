package com.nulijiushimeili.librarian.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/******************************
 * @Project: librarian-parent
 * @FileName: DataSourceUserInfo.java
 * @ClassName: DataSourceUserInfo
 * @time 2020/5/11 21:57
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataSourceUserInfo {


    /**
     * 数据源用户名
     */
    private String username;


    /**
     * 数据源密码
     */
    private String password;



}
