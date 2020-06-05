package com.nulijiushimeili.librarianwebui.config.mybatis;

import com.nulijiushimeili.librariancommon.beans.enums.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自动枚举类转换器
 * @param <E>
 */
public class AutoEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private BaseTypeHandler typeHandler;

    /**
     * 如果枚举类是BaseEnum的实现，使用自定义EnumOrderStatusHandler
     * 如果没有实现BaseEnum接口，则使用Mybatis默认的EnumTypeHandler
     * @param type
     */
    public AutoEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }

        if (BaseEnum.class.isAssignableFrom(type)) {
            typeHandler = new EnumOrderStatusHandler(type);
        } else {
            typeHandler = new EnumTypeHandler(type);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        typeHandler.setNonNullParameter(preparedStatement, i, e, jdbcType);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return (E) typeHandler.getNullableResult(resultSet, s);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return (E) typeHandler.getNullableResult(resultSet, i);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return (E) typeHandler.getNullableResult(callableStatement, i);
    }
}
