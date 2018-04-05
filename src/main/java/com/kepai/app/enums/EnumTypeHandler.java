package com.kepai.app.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author huang
 * @ProjectName wine
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/3/19
 * @note 通过MybatisEnumConfig注入, 需要解析的枚举都要必须包含在这个包下面
 * @note
 */
public class EnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<E> {

    private E[] enums;

    public EnumTypeHandler() {
    }

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E e, JdbcType jdbcType) throws SQLException {
        ps.setString(i, e.getJdbcValue().toString());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return convertEnum(resultSet.getString(s));
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return convertEnum(resultSet.getString(i));
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return convertEnum(callableStatement.getString(i));
    }

    ///////////////////////////////////////////////////////////////////////////
    // 逻辑方法
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 查找
     *
     * @param jdbcValue
     * @return
     */
    private E convertEnum(String jdbcValue) {
        for (E e : enums) {
            if (e.getJdbcValue().toString().equals(jdbcValue)) {
                return e;
            }
        }
        return null;
    }

}
