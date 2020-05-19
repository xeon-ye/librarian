package com.nulijiushimeili.librarian.beans.enums;



/******************************
 * @Project: librarian-parent
 * @FileName: NodeTypeEnum.java
 * @ClassName: NodeTypeEnum
 * @time 2020/5/11 21:13
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

public enum NodeTypeEnum implements BaseEnum {

    /**
     * 主节点
     */
    MASTER(1, "master节点"),
    /**
     * 从节点
     */
    SLAVE(2, "slave节点"),
    /**
     * zk节点
     */
    ZOOKEEPER(3, "zookeeper节点"),
    /**
     * hdfs-master 节点
     */
    HDFS_MASTER(4, "hdfs_Master节点"),
    /**
     * hive-jdbc 节点
     */
    HIVESERVER2(5, "HiveServer2"),


    KAFKA_NODE(6, "kafka-bootstrap.server"),
    /**
     * 其他节点
     */
    OHTER(0, "其他类型");


    private int code;
    private String value;

    NodeTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public static NodeTypeEnum valueOf(int code) {
        switch (code) {
            case 1:
                return NodeTypeEnum.MASTER;
            case 2:
                return NodeTypeEnum.SLAVE;
            case 3:
                return NodeTypeEnum.ZOOKEEPER;
            case 4:
                return NodeTypeEnum.HDFS_MASTER;
            case 5:
                return NodeTypeEnum.HIVESERVER2;
            case 0:
                return NodeTypeEnum.OHTER;
            default:
                return null;
        }
    }


}
