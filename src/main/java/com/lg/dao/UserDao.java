package com.lg.dao;

import com.lg.entity.User;

import java.util.List;

/**
 * Created by liuguo on 2017/6/13.
 */
public interface UserDao {
    User getUser(int id);

    void insertUsers(List<User> users);
}
