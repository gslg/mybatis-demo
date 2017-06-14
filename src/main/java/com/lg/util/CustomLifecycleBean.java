package com.lg.util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by liuguo on 2017/6/14.
 */
public class CustomLifecycleBean implements InitializingBean,DisposableBean {

    private String name;

    /**
     * Bean属性都设置完毕后调用afterPropertiesSet()方法做一些初始化的工作
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy<---------------------->name="+name);
    }

    /**
     * Bean生命周期结束前调用destory()方法做一些收尾工作
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet<---------------------->name="+name);
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set <---------------------->name="+ name);
    }

    public void init(){
        System.out.println("init<------------->name="+name);
    }

    public void end(){
        System.out.println("end<----------------->name+"+name);
    }
}
