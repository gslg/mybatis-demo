package com.lg.typehandler;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by liuguo on 2017/3/28.
 */
public class TypeHandlerTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        Reader reader = Resources.getResourceAsReader("com/lg/typehandler/mybatis-config.xml");

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();

        sqlSessionFactory.getConfiguration().getTypeHandlerRegistry().register(StringTrimmingTypeHandler.class);

        SqlSession session = sqlSessionFactory.openSession();
        Connection connection = session.getConnection();
        reader = Resources.getResourceAsReader("com/lg/typehandler/CreateDB.sql");

        ScriptRunner runner = new ScriptRunner(connection);
        runner.setLogWriter(null);
        runner.runScript(reader);
        reader.close();
        session.close();
    }

    private void addMapper(){
        sqlSessionFactory.getConfiguration().addMapper(Mapper.class);
    }

    @Test
    public void shouldGetUser(){
        addMapper();

        SqlSession session = sqlSessionFactory.openSession();
        try {
            Mapper mapper =  session.getMapper(Mapper.class);

            User user = mapper.getUser(1);
            assertEquals("User1",user.getName());
            assertEquals("Carmel",user.getCity());
            assertEquals("IN",user.getState());
        } finally {
            session.close();
        }
    }

    @Test
    public void shouldApplyTypeHandlerOnGeneratedKey(){
        addMapper();
        SqlSession session  = sqlSessionFactory.openSession();

        try {
            Mapper mapper = session.getMapper(Mapper.class);

            Product product = new Product();
            product.setName("test");
            mapper.insertProduct(product);
            assertNotNull(product.getId());
            assertNotNull(product.getId().getValue());

            session.commit();
        }finally {
            session.close();
        }
    }







}
