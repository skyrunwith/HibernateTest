package com.fzd.hibernate;

import model.Book;
import model.BookType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by FZD on 2017/3/18.
 */
public class Double {

    private static SessionFactory factory;
    public static void main( String[] args )
    {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
//        save();
//        query();
//        del();
    }

    private static void save() {
        Session session = factory.getCurrentSession();
        BookType bookType = new BookType("语文");
        Book book1 = new Book("小学语文");
        Book book2 = new Book("中学语文");
        book1.setBookType(bookType);
        book2.setBookType(bookType);
        bookType.getBooks().add(book1);
        bookType.getBooks().add(book2);

        Transaction tx = session.beginTransaction();
        session.save(bookType);
        session.save(book1);
        session.save(book2);
        tx.commit();
//        session.close();
    }

    private static void query() {
        Session session = factory.getCurrentSession();
        Transaction tx = session.beginTransaction();
      /*  Book book = (Book) session.get(Book.class,1);
        System.out.println("--------------");
        System.out.println("Book.name: " + book.getName());
        System.out.println("--------------");
        System.out.print("BookType.name:" + book.getBookType().getName());*/
        BookType bookType = (BookType) session.get(BookType.class, 1);
        System.out.print("BookType.name:" + bookType.getName());
        System.out.println("--------------");
        for(Book item : bookType.getBooks()) {
            System.out.println("Book.name: " + item.getName());
        }
        tx.commit();
    }

    public static void del(){
        Session session = factory.getCurrentSession();
        Transaction tx = session.beginTransaction();
//        BookType bookType = (BookType) session.get(BookType.class, 8);
        Book book = (Book) session.get(Book.class, 6);
        session.delete(book);
        tx.commit();
    }
}
