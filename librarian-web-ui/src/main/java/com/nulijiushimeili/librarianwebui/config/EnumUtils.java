package com.nulijiushimeili.librarianwebui.config;


import com.nulijiushimeili.librarianwebui.beans.enums.BaseEnum;

public class EnumUtils {

    /**
     * 将code转为对应的枚举类
     * @param enumClass
     * @param code
     * @param <E>
     * @return
     */
    public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("该枚举类没有对应的code：" + code);
    }

    /**
     * 将value转化为对应的枚举类
     * @param enumClass
     * @param value
     * @param <E>
     * @return
     */
    public static <E extends BaseEnum> E valueOf(Class<E> enumClass, String value) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("该枚举类没有对应的value：" + value);
    }
}
