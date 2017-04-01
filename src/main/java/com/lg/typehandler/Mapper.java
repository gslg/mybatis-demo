package com.lg.typehandler;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

/**
 * Created by liuguo on 2017/3/28.
 */
public interface Mapper {

    @Select("select *from users where id = #{id}")
    @Results({

            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "city", column = "city", jdbcType = JdbcType.CHAR),
            @Result(property = "state", column = "state", jdbcType = JdbcType.VARCHAR)
    }
    )
    User getUser(int id);

    @Insert({"insert into Product (name) values (#{name})"})
    @Options(keyProperty = "id",useGeneratedKeys = true)
    int insertProduct(Product product);

    @Insert({"insert into product (id,name) values (#{id},#{name})"})
    void insertProduct2(Product product);

    @Select("select id,name from Product where name = #{name}")
    Product getProductByName(String name);

    @Select("select id from Product where name = #{name}")
    Product.ProductId getProductIdByName(String name);

    Product getProductByNameXml(String name);

    @Select("select id,name from product where name = #{name}")
    @ConstructorArgs({
        @Arg(id = true, column = "id", jdbcType = JdbcType.INTEGER, javaType = Product.ProductId.class),
        @Arg(column = "name",jdbcType = JdbcType.VARCHAR,javaType = String.class)
      }
    )
    Product getProductByNameUsingConstructor(String name);
}
