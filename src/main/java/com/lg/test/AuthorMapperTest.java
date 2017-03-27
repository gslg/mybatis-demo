package com.lg.test;

import com.lg.entity.Author;
import com.lg.entity.mappers.AuthorMapper;
import com.lg.factory.SqlSesstionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguo on 2017/3/27.
 */
public class AuthorMapperTest {
    public static void main(String[] args) {
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
}
