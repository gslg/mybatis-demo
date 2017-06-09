package com.lg.entity;

import com.lg.entity.mappers.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by liuguo on 2017/3/24.
 */
public class Test {
    public static void main(String[] args){
        try {
            InputStream inputStream =  Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = sqlSessionFactory.openSession();

            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            Student student = studentMapper.selectStudent(1);

            List<Student> students = studentMapper.queryList(null);
            for(Student student1 : students){
                System.out.println(student1);
            }
            //Student student =  session.selectOne("com.lg.entity.mappers.StudentMapper.selectStudent",1);
            System.out.println("student:"+student.getStudentName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
