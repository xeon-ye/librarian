package com.nulijiushimeili.librariancommon.beans.entity;

import com.google.common.hash.HashCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.avro.generic.GenericData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;


/**
 * ztree 实体信息
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZtreePerData{

    /**
     *  节点id
     */
    private Long id;

    /**
     * 节点的父id
     */
    private Long pId;

    /**
     * 节点的名称
     */
    private String name;

    /**
     * 节点是否为父节点，可以判断当前 filePath 是文件还是目录
     */
    private Boolean  isParent;
}
