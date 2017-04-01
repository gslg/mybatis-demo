package com.lg.test;

import com.lg.entity.Author;
import com.lg.entity.mappers.AuthorMapper;
import com.lg.factory.SqlSesstionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguo on 2017/3/27.
 */
public class AuthorMapperTest {

    @Test
    public void testInsertAuthor(){
        SqlSession sqlSession = SqlSesstionUtil.openSqlSession();
        try {

            AuthorMapper authorMapper =sqlSession.getMapper(AuthorMapper.class);

            Author author =  authorMapper.selectAuthor(101);
            System.out.println(author);

            Author author1 = new Author();
            author1.setUsername("lg");
            author1.setPassword("lg");
            author1.setEmail("lg@qq.com");
            author1.setBio("zzzzzzzzzzzz");

            Author author2 = new Author();
            author2.setUsername("ly");
            author2.setPassword("ly");
            author2.setEmail("ly@qq.com");
            author2.setBio("kkkkkkkkkkkkkk");

            List<Author> authors = new ArrayList<Author>();
            authors.add(author1);
            authors.add(author2);
            authorMapper.insertAuthor(authors);

            sqlSession.commit();

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAuthor(){
        int id = 109;
        SqlSession sqlSession = SqlSesstionUtil.openSqlSession();
        try {
            AuthorMapper authorMapper =  sqlSession.getMapper(AuthorMapper.class);
            Author author =  authorMapper.selectAuthor(id);
            System.out.println(author);

            author.setUsername("lb");
            authorMapper.updateAuthor(author);

            Author author1 = authorMapper.selectAuthor(id);
            System.out.println(author1);
            /**
             DEBUG [main] - ==>  Preparing: SELECT id,username,password,email,bio,favourite_section from author WHERE id = ?
             DEBUG [main] - ==> Parameters: 109(Integer)
             DEBUG [main] - <==      Total: 1
             Author : 109 : ly : ly@qq.com
             Author : 109 : ly : ly@qq.com
             DEBUG [main] - ==>  Preparing: UPDATE author SET username = ?, password = ?, email = ?, bio = ? WHERE id = ?
             DEBUG [main] - ==> Parameters: lb(String), ly(String), ly@qq.com(String), kkkkkkkkkkkkkk(String), 109(Integer)
             DEBUG [main] - <==    Updates: 1
             DEBUG [main] - Cache Hit Ratio [com.lg.entity.mappers.AuthorMapper]: 0.0
             DEBUG [main] - ==>  Preparing: SELECT id,username,password,email,bio,favourite_section from author WHERE id = ?
             DEBUG [main] - ==> Parameters: 109(Integer)
             DEBUG [main] - <==      Total: 1
             Author : 109 : lb : ly@qq.com
             */
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateAuthor(){
        int id = 108;
        SqlSession sqlSession = SqlSesstionUtil.openSqlSession();
        try {
            AuthorMapper authorMapper =  sqlSession.getMapper(AuthorMapper.class);
            Author author =  authorMapper.selectAuthor(id);

            author.setBio("hahha333");
            author.setUsername("lgzzz");
            author.setPassword("demo");
            author.setEmail("lgzzz@163.com");

            authorMapper.updateAuthor(author);

            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteAuthor(){
        int id = 107;
        SqlSession session = SqlSesstionUtil.openSqlSession();

        try {
            AuthorMapper authorMapper = session.getMapper(AuthorMapper.class);
            authorMapper.deleteAuthor(id);
            session.commit();
        } finally {
            session.close();
        }
    }

}
