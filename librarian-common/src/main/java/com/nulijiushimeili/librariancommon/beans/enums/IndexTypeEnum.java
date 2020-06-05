package com.nulijiushimeili.librariancommon.beans.enums;



/******************************
 * @Project: librarian-parent
 * @FileName: NodeTypeEnum.java
 * @ClassName: NodeTypeEnum
 * @time 2020/5/11 21:13
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/

public enum IndexTypeEnum implements BaseEnum {

    /**
     * 非索引
     */
    NOT_INDEX(0,"非索引"),

    /**
     * 主键
     */
    PRIMARY_KEY(1, "主键"),
    /**
     * 唯一索引
     */
    UNIQUE(2, "唯一索引"),

    /**
     *  普通索引
     */
    NORMAL(3, "普通索引"),
    /**
     * 全文索引
     */
    FULLTEXT(4, "全文索引");



    private int code;
    private String value;

    IndexTypeEnum(int code, String value) {
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

    public static IndexTypeEnum valueOf(int code) {
        switch (code) {
            case 1:
                return IndexTypeEnum.PRIMARY_KEY;
            case 2:
                return IndexTypeEnum.UNIQUE;
            case 3:
                return IndexTypeEnum.NORMAL;
            case 4:
                return IndexTypeEnum.FULLTEXT;
            case 0:
                return IndexTypeEnum.NOT_INDEX;
            default:
                return null;
        }
    }


}
