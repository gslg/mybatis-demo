package com.lg.entity.mappers;

import com.lg.entity.Post;

import java.util.List;

/**
 * Created by liuguo on 2017/6/9.
 */
public interface PostMapper {
    List<Post> selectPostIn(int[] ids);
}
