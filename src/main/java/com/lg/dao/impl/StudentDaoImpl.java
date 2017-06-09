package com.lg.dao.impl;

import com.lg.dao.StudentDao;
import com.lg.entity.Student;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuguo on 2017/6/9.
 */
@Repository
public class StudentDaoImpl extends SqlSessionDaoSupport implements StudentDao {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<Student> selectStudents(String name) {
        SqlSession session = getSession();
        return session.selectList("StudentMapper.queryList",name);
    }

    SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}
