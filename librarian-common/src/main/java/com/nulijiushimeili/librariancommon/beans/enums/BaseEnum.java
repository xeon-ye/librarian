package com.nulijiushimeili.librariancommon.beans.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/******************************
 * @Project: librarian-parent
 * @FileName: BaseEnum.java
 * @ClassName: BaseEnum
 * @time 2020/5/11 21:15
 * @version 1.00
 * @author nulijiushimeili
 * @Description: TODO
 ******************************/
public interface BaseEnum {
    int getCode();

    @JsonValue
    String getValue();
}
