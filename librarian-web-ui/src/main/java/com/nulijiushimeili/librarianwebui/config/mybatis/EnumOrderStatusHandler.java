package com.nulijiushimeili.librarianwebui.config.mybatis;

import com.nulijiushimeili.librarian.beans.enums.BaseEnum;
import com.nulijiushimeili.librarianwebui.config.EnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义枚举类转换器
 */
public class EnumOrderStatusHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {

    private Class<E> type;

    public EnumOrderStatusHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("枚举类为空");
        }

        this.type = type;
    }

    /**
     * 枚举类写入数据库时的转换器
     * 将枚举类转为code
     * @param preparedStatement
     * @param i
     * @param baseEnum
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseEnum baseEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, baseEnum.getCode());
    }

    /**
     * 通过字段名获取数据时，把数据库类型转换为Java类型
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int code = resultSet.getInt(s);
        return resultSet.wasNull() ? null : EnumUtils.codeOf(type, code);
    }

    /**
     * 通过字段索引获取数据时，把数据库类型转换为Java类型
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public BaseEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int code = resultSet.getInt(i);
        return resultSet.wasNull() ? null : EnumUtils.codeOf(type, code);
    }

    /**
     * 调用存储过程时，把数据库类型转换为Java类型
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public BaseEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int code = callableStatement.getInt(i);
        return callableStatement.wasNull() ? null : EnumUtils.codeOf(type, code);
    }
}
