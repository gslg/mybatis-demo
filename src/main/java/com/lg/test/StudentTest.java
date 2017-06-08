package com.lg.test;

import com.lg.entity.Student;
import com.lg.entity.StudentMapper;
import com.lg.factory.SqlSesstionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by liuguo on 2017/6/8.
 */
public class StudentTest {
    @Test
    public void testStudent(){
        try {
            InputStream inputStream =  Resources.getResourceAsStream("mybatis-config.xml");

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = sqlSessionFactory.openSession();

            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            Student student = studentMapper.selectStudent(1);

            Student student2 = session.selectOne("com.lg.entity.StudentMapper.selectStudent",1);

            Assert.assertNotNull(student2);

            List<Student> students = studentMapper.queryList(null);
            for(Student student1 : students){
                System.out.println(student1);
            }
            //Student student =  session.selectOne("com.lg.entity.StudentMapper.selectStudent",1);
            System.out.println("student:"+student.getStudentName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStudentCache(){
            SqlSession session = SqlSesstionUtil.openSqlSession();

        try {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);
            Student student =  studentMapper.selectStudent(1);
            System.out.println(student);

            student.setStudentAge(60);
            studentMapper.updateStudent(student);

            student =  studentMapper.selectStudent(1);
            System.out.println(student);
            session.commit();
        } catch (Exception e) {
            session.rollback();
        } finally {
            session.close();
        }

    }
}
