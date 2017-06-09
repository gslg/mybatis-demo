package com.lg.test;

import com.lg.dao.StudentDao;
import com.lg.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by liuguo on 2017/6/9.
 */
public class StudentDaoTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        StudentDao studentDao = (StudentDao) applicationContext.getBean("studentDaoImpl");

        List<Student> students = studentDao.selectStudents("李四");

        students.forEach(System.out::println);
    }
}
