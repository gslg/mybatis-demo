package com.lg.test;

import com.lg.Service.UserService;
import com.lg.dao.UserDao;
import com.lg.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguo on 2017/6/13.
 */
public class UserServiceTest {
    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        UserService userService = applicationContext.getBean("userServiceImpl",UserService.class);

        User user = userService.getUser(1);

        System.out.println(user);
    }

    @Test
    public void testUserDao(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");

        UserDao userDao = applicationContext.getBean("userDaoImpl",UserDao.class);
        System.out.println(userDao.getUser(1));

        List<User> users = new ArrayList<>();
        users.add(new User(2,"lg","四川","IN"));
        users.add(new User(3,"aa","重庆","out"));
        users.add(new User(4,"bb","湖南","out"));
        users.add(new User(5,"cc","江苏","IN"));

        userDao.insertUsers(users);
    }
}
