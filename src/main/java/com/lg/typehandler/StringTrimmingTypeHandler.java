package com.lg.typehandler;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liuguo on 2017/3/28.
 */
@MappedTypes(String.class)
@MappedJdbcTypes(value = {JdbcType.CHAR,JdbcType.VARCHAR},includeNullJdbcType = true)
public class StringTrimmingTypeHandler extends BaseTypeHandler<String> {

    private final Log log = LogFactory.getLog(StringTrimmingTypeHandler.class);

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        log.debug("StringTrimmingTypeHandler.setNonNullParameter......."+i+","+s);
        preparedStatement.setString(i, trim(s));
    }

    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        log.debug("StringTrimmingTypeHandler.getNullableResult(ResultSet resultSet, String s)...."+s);
        return trim(resultSet.getString(s));
    }

    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String result = resultSet.getString(i);
        log.debug("StringTrimmingTypeHandler.getNullableResult(ResultSet resultSet, int i)......."+result);
        return trim(result);
    }

    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String result = callableStatement.getString(i);
        log.debug("StringTrimmingTypeHandler.getNullableResult(CallableStatement callableStatement, int i)......."+result);
        return trim(result);
    }

    private String trim(String s) {
        if (s == null) {
            return null;
        } else {
            return s.trim();
        }
    }
}
