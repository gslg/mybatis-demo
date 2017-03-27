package com.lg.entity;

import java.util.List;

/**
 * Created by liuguo on 2017/3/24.
 */
public interface StudentMapper {
    Student selectStudent(int id);

    List<Student> queryList(String name);
}
