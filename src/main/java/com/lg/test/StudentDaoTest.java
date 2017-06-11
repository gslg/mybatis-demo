package com.lg.test;

import com.lg.dao.StudentDao;
import com.lg.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguo on 2017/6/9.
 */
public class StudentDaoTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentDao studentDao = (StudentDao) applicationContext.getBean("studentDaoImpl");

        Student student = new Student(10,"李四",20,"12345678");
        studentDao.insertStudent(student);
        List<Student> students = studentDao.selectStudents(null);

        students.forEach(System.out::println);
    }

    @Test
    public void testBatchInsert(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentDao studentDao = (StudentDao) applicationContext.getBean("studentDaoImpl");

        List<Student> students = new ArrayList<>();
        students.add(new Student("张三",30,"3333"));
        students.add(new Student("ala",40,"4444"));
        //将都不会插入
        //students.add(new Student(null,40,"4444"));
        students.add(new Student("ele",50,"55555"));

        studentDao.batchInsert(students);

    }
}
