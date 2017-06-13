package com.lg.test;

import org.apache.ibatis.jdbc.SQL;
import org.junit.Test;

/**
 * Created by liuguo on 2017/6/13.
 */
public class SQLTest {
    @Test
    public void testInsert(){
        String sql  = new SQL()
                .INSERT_INTO("author")
                .VALUES("username,password","#{username},#{password}")
                .VALUES("email","#{email}")
                .toString();

        System.out.println(sql);
    }
}
