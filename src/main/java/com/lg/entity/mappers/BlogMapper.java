package com.lg.entity.mappers;

import com.lg.entity.Blog;

import java.util.List;

/**
 * Created by liuguo on 2017/3/27.
 */
public interface BlogMapper {
    List<Blog> findAll();
    Blog selectBlog(int id);
    Blog selectBlog2(int id);
}
