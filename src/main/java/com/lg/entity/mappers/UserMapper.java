package com.lg.entity.mappers;

import com.lg.entity.User;
import com.lg.util.annotation.MyBatisMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by liuguo on 2017/6/13.
 */
@MyBatisMapper
public interface UserMapper {

    @Select("select *from users where id = #{id}")
    User getUser(@Param("id") int id);

    @Insert("insert into users values (#{id},#{name},#{city},#{state})")
    void insertUser(User user);
}
