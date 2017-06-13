package com.lg.Service.impl;

import com.lg.Service.UserService;
import com.lg.entity.User;
import com.lg.entity.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liuguo on 2017/6/13.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public User getUser(int id) {
        return userMapper.getUser(id);
    }
}
