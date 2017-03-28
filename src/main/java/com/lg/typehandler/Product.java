package com.lg.typehandler;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liuguo on 2017/3/28.
 */
public class Product {
    private final static Log log = LogFactory.getLog(Product.class);
    private ProductId id;

    private String name;

    public Product() {
        super();
    }

    public Product(ProductId id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public ProductId getId() {
        return id;
    }

    public void setId(ProductId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class ProductId {
        private Integer value;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    //typehandler
    public static class ProductIdTypeHandler extends BaseTypeHandler<ProductId>{

        /**
         * 将数据存入DB时，由于domain对象的id是ProductId类型，需要取出int值存入数据库
         * @param preparedStatement
         * @param i
         * @param productId
         * @param jdbcType
         * @throws SQLException
         */
        public void setNonNullParameter(PreparedStatement preparedStatement, int i, ProductId productId, JdbcType jdbcType) throws SQLException {
            log.debug("set ProductId:"+productId.getValue());
            preparedStatement.setInt(i,productId.getValue());
        }

        /**
         * 当从数据库中查询数据时，返回的id是int类型,而domain是存放的ProductId类型，需要返回持有该id的ProductId类型
         * @param resultSet
         * @param s
         * @return
         * @throws SQLException
         */
        public ProductId getNullableResult(ResultSet resultSet, String s) throws SQLException {
            ProductId id = new ProductId();
            id.setValue(resultSet.getInt(s));

            log.debug("getNullableResult(ResultSet resultSet, String s) get ProductId:"+id.getValue());
            return id;
        }

        public ProductId getNullableResult(ResultSet resultSet, int i) throws SQLException {
            ProductId id = new ProductId();
            id.setValue(resultSet.getInt(i));
            log.debug("getNullableResult(ResultSet resultSet, int i) get ProductId:"+id.getValue());
            return id;
        }

        public ProductId getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
            ProductId id = new ProductId();
            id.setValue(callableStatement.getInt(i));
            log.debug("getNullableResult(CallableStatement callableStatement, int i) get ProductId:"+id.getValue());
            return id;
        }
    }

    public static class ConstantProductIdTypeHandler extends BaseTypeHandler<ProductId>{

        public void setNonNullParameter(PreparedStatement preparedStatement, int i, ProductId productId, JdbcType jdbcType) throws SQLException {
            log.debug("ConstantProductIdTypeHandler set");
        }

        public ProductId getNullableResult(ResultSet resultSet, String s) throws SQLException {
            return getConstantId();
        }

        public ProductId getNullableResult(ResultSet resultSet, int i) throws SQLException {
            log.debug("ConstantProductIdTypeHandler.getNullableResult(ResultSet resultSet, int i) set");
            return getConstantId();
        }

        public ProductId getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
            log.debug("ConstantProductIdTypeHandler.getNullableResult(CallableStatement callableStatement, int i) set");
            return getConstantId();
        }

        private ProductId getConstantId() {
            ProductId id = new ProductId();
            id.setValue(999);
            return id;
        }
    }


}
