package com.lg.test;

import com.lg.entity.Student;
import com.lg.entity.mappers.StudentMapper;
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

            Student student2 = session.selectOne("selectStudent",1);

            Assert.assertNotNull(student2);

            List<Student> students = studentMapper.queryList("Lily");
            List<Student> students2 = studentMapper.queryList2("Lily");
            for(Student student1 : students){
                System.out.println(student1);
            }
            //Student student =  session.selectOne("com.lg.entity.mappers.StudentMapper.selectStudent",1);
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

    @Test
    public void testCondition(){
        SqlSession session = SqlSesstionUtil.openSqlSession();

        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        Student student = new Student(0,"Lily",21,null);

        List<Student> students = studentMapper.selectWithCondition(student);

        students.forEach(System.out::println);
    }

    @Test
    public void testUpdate(){
        SqlSession session = SqlSesstionUtil.openSqlSession();

        try {
            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            Student student = new Student(1,"张三",22,null);

            studentMapper.updateStudent(student);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void insertTest(){
        SqlSession session = SqlSesstionUtil.openSqlSession();
        try {

            StudentMapper studentMapper = session.getMapper(StudentMapper.class);

            Student student = new Student(10,"李四",211,"123556");

            studentMapper.insertStudent(student);
            System.out.println(student);
            List<Student> students = studentMapper.queryList(null);
            students.forEach(System.out::println);

            /**
             * StudentId:1	StudentName:张三	StudentAge:22	StudentPhone:22
             StudentId:2	StudentName:Mark Lily	StudentAge:21	StudentPhone:21
             StudentId:3	StudentName:Lily	StudentAge:22	StudentPhone:22
             StudentId:4	StudentName:Lucy	StudentAge:23	StudentPhone:23
             StudentId:5	StudentName:李四	StudentAge:211	StudentPhone:211
             */

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
    }
}
