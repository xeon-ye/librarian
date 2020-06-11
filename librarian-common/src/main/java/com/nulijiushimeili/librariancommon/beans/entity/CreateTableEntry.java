package com.nulijiushimeili.librariancommon.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTableEntry {


    /**
     *  数据源连接信息
     */
    private ConnectionInfo connInfoEntity;

    /**
     * 元数据信息
     */
    private BaseMetadataEntity baseMetadataEntity;

    /**
     *  元数据的列信息
     */
    private List<BaseColumnInfo> baseColumnInfoList;

}
