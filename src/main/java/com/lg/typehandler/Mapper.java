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
}
