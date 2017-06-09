package com.lg.dao;

import com.lg.entity.Student;

import java.util.List;

/**
 * Created by liuguo on 2017/6/9.
 */
public interface StudentDao {
    List<Student> selectStudents(String name);
}
