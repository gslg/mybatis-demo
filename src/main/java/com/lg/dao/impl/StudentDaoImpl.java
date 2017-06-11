package com.lg.dao.impl;

import com.lg.dao.CommonDao;
import com.lg.dao.StudentDao;
import com.lg.entity.Student;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liuguo on 2017/6/9.
 */
@Repository
public class StudentDaoImpl extends CommonDao implements StudentDao {


    @Override
    public List<Student> selectStudents(String name) {
        SqlSession session = getSession();
        return session.selectList("com.lg.entity.mappers.StudentMapper.queryList",name);
    }

    @Override
    public void insertStudent(Student student) {
        SqlSession sqlSession = getSqlSession();
        sqlSession.insert("com.lg.entity.mappers.StudentMapper.insertStudent",student);
    }

    @Override
    public void batchInsert(List<Student> students) {
        SqlSession sqlSession = getSqlSession();
        sqlSession.insert("com.lg.entity.mappers.StudentMapper.batchInsert",students);
    }

    SqlSession getSession(){
        return getSqlSession();
    }
}
