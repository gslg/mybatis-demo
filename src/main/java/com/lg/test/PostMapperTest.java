package com.lg.test;

import com.lg.entity.Post;
import com.lg.entity.mappers.PostMapper;
import com.lg.factory.SqlSesstionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;


/**
 * Created by liuguo on 2017/6/9.
 */
public class PostMapperTest {
    @Test
    public void testSelectPostIn(){
        SqlSession session = SqlSesstionUtil.openSqlSession();
       PostMapper postMapper = session.getMapper(PostMapper.class);

       List<Post> posts = postMapper.selectPostIn(new int[]{2,3});

       posts.forEach(System.out::println);
    }
}
