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

    /**
     * 测试查询用户
     */
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

    /**
     * 测试自动生成主键
     */
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

    @Test
    public void shoudApplyTypeHandlerOnSetNonNullParameter(){
        addMapper();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            Mapper mapper = session.getMapper(Mapper.class);

            Product product = new Product();
            Product.ProductId id = new Product.ProductId();
            id.setValue(10);
            product.setId(id);
            product.setName("zzzz");
            /**
             * 会打印日志: set ProductId:10,因为ProductId不为null，所以会经过Prodcut$ProductIdTypeHandler.setNonNullParameter
             */
            mapper.insertProduct2(product);

        }finally {
            session.close();
        }
    }

    @Test
    public void shouldApplyTypeHandlerWithJdbcTypeSpecified(){
        addMapper();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            Mapper mapper = session.getMapper(Mapper.class);

            Product product = mapper.getProductByName("ipad");

            assertEquals(Integer.valueOf(2),product.getId().getValue());
        }finally {
            session.close();
        }
    }

    @Test
    public void shouldApplyTypeHandlerOnReturnTypeWithJdbcTypeSpecified(){
        addMapper();
        SqlSession session = sqlSessionFactory.openSession();

        try {
            Mapper mapper = session.getMapper(Mapper.class);

            Product.ProductId productId = mapper.getProductIdByName("ipad");

            assertEquals(Integer.valueOf(2),productId.getValue());
        }finally {
            session.close();
        }
    }

    @Test
    public void shouldPickSoleTypeHandlerOnXmlResultMap() throws Exception {
        addMapper();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            Product product = mapper.getProductByNameXml("iPad");
            assertEquals(Integer.valueOf(2), product.getId().getValue());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void shouldApplyTypeHandlerUsingConstructor() throws Exception {
        addMapper();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            Product product = mapper.getProductByNameUsingConstructor("iPad");
            assertEquals(Integer.valueOf(2), product.getId().getValue());
        } finally {
            sqlSession.close();
        }
    }








}
