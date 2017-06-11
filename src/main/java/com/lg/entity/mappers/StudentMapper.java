package com.lg.entity.mappers;

import com.lg.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuguo on 2017/3/24.
 */
public interface StudentMapper {
    Student selectStudent(int id);

    List<Student> queryList(String name);

    List<Student> queryList2(@Param("name") String name);

    void updateStudent(Student student);

    List<Student> selectWithCondition(Student student);

    void insertStudent(Student student);

    void batchInsert(List<Student> students);

}
