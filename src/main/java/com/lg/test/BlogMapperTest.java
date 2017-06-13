package com.lg.test;

import com.lg.entity.Blog;
import com.lg.entity.mappers.BlogMapper;
import com.lg.factory.SqlSesstionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liuguo on 2017/3/27.
 */
public class BlogMapperTest {
    public static void main(String[] args) {

        SqlSession sqlSession = SqlSesstionUtil.openSqlSession();
        try {

            BlogMapper blogMapper =sqlSession.getMapper(BlogMapper.class);
            Blog blog = blogMapper.selectBlog(1);
            System.out.println(blog);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        SqlSessionFactory sqlSessionFactory = applicationContext.getBean("sqlSessionFactory",SqlSessionFactory.class);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        //Blog blog = blogMapper.selectBlog(1);
        Blog blog = blogMapper.selectBlog2(1);

        System.out.println(blog);
    }

    @Test
    public void testBlogDetail(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        SqlSessionFactory sqlSessionFactory = applicationContext.getBean("sqlSessionFactory",SqlSessionFactory.class);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = blogMapper.selectBlogDetails(1);
        System.out.println(blog);

         blog.getPosts().forEach(System.out::println);
    }
}
