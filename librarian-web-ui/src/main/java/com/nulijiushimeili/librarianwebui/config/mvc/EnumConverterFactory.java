package com.nulijiushimeili.librarianwebui.config.mvc;

import com.nulijiushimeili.librariancommon.beans.enums.BaseEnum;
import com.nulijiushimeili.librarianwebui.config.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Spring MVC 枚举类转换器
 */
public class EnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> enumClass) {
        return new EnumConverter<T>(enumClass);
    }

    class EnumConverter<T extends BaseEnum> implements Converter<String, T> {
        private final Class<T> enumType;

        public EnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        /**
         * 将value转换成枚举类
         * @param s
         * @return
         */
        @Override
        public T convert(String s) {
            return EnumUtils.valueOf(enumType, s);
        }
    }
}
