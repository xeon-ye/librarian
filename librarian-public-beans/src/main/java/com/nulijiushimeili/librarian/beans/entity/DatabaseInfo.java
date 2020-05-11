package com.nulijiushimeili.librarian.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/******************************
 * @Project: librarian-parent
 * @FileName: DatabaseInfo.java
 * @ClassName: DatabaseInfo
 * @time 2020/5/11 21:13
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DatabaseInfo {

    private String connName;

    private String dbName;

    private String dbDesc;

}
