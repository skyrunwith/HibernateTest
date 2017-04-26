package com.fzd.hibernate;

import model.User;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by FZD on 2017/4/5.
 */
public class ThreeStateTest {
    @Test
    public void testTransient(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = new User();
        user.setPassWord("123");
        user.setUserName("23");
        session.save(user);
        session.getTransaction().commit();
    }

    /**
     * 持久状态调用save和update没有意义
     * 如果一个对象以及是持久化状态了，那么此时对该对象进行各种修改，或者调用多次update、save方法时，
     * hibernate都不会发送sql语句，只有当事物提交的时候，此时hibernate才会拿当前这个对象与之前保存
     * 在session中的持久化对象进行比较，如果不相同就发送一条update的sql语句，否则就不会发送update语句
     */
    @Test
    public void testTransient1(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = new User();
        user.setPassWord("123");
        user.setUserName("23");
        session.save(user);
        user.setUserName("fzd");
        session.save(user);
        user.setUserName("rxq");
        session.update(user);
        session.getTransaction().commit();
    }

    /**
     * 当session调用load、get方法时，此时如果数据库中有该对象，则该对象也变成了一个持久化对象，被session所托管
     */
    @Test
    public void testTransient2(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, 1);
        user.setUserName("111");
        session.save(user);
        session.getTransaction().commit();
    }

    /**
     * 当调用clear方法，对象变为游离状态,saveOrUpdate，会恢复持久状态
     */
    @Test
    public void testTransient3(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, 1);
        user.setUserName("110");
        session.clear();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    /**
     * 当调用clear方法，对象变为游离状态,在调用save方法，会更新该记录
     */
    @Test
    public void testTransient4(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, 1);
        session.clear();
        user.setUserName("110");
        session.update(user);
        session.getTransaction().commit();
    }

    /**
     *
     */
    @Test
    public void testTransient5(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.load(User.class, 1);
        user.setId(10);
        session.getTransaction().commit();
    }
}
