package com.fzd.hibernate;

import model.Book;
import model.BookType;
import model.Idcard;
import model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by FZD on 2017/4/26.
 */
public class Annotation {
    private static SessionFactory factory;
    public static void main(String... args){
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        save();
    }

    private static void save() {
        Session session = factory.openSession();


        Transaction tx = session.beginTransaction();
        Idcard idcard= new Idcard();
        idcard.setNum("323");
//        session.save(idcard);
        Student student = new Student();
        student.setName("123");
        student.setIdcard(idcard);
        session.save(student);
        tx.commit();
        session.close();
    }
}
