package com.fzd.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by FZD on 2017/4/5.
 */
public class HibernateUtil {

    public static SessionFactory sessionFactory;
    public static Session session;
    public static Session getSession(){
        if(sessionFactory == null){
            try{
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }catch (Throwable ex) {
                System.err.println("Failed to create sessionFactory object." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory.getCurrentSession();
    }
}
