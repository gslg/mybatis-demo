package com.lg.dao.impl;

import com.lg.dao.UserDao;
import com.lg.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuguo on 2017/6/13.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    private SqlSession sqlSession;

    @Override
    public User getUser(int id) {
        return sqlSession.selectOne("com.lg.entity.mappers.UserMapper.getUser",id);
    }

    @Override
    public void insertUsers(List<User> users) {
        for (User user : users){
            sqlSession.insert("com.lg.entity.mappers.UserMapper.insertUser",user);
        }
    }
}
