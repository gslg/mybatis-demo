package com.lg.test;

import com.lg.entity.Blog;
import com.lg.entity.mappers.BlogMapper;
import com.lg.factory.SqlSesstionUtil;
import org.apache.ibatis.session.SqlSession;

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
}
