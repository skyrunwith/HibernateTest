package com.fzd.hibernate;

import model.Book;
import model.BookType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    private static SessionFactory factory;
    public static void main( String[] args )
    {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        //单向一对多
//        save();
//        query();
//        updata();
//        delete();
        //单向多对一
          saveManyToOne();
//        queryManyToOne();
    }

    public static void  saveManyToOne(){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        BookType bookType = new BookType("语文书");
        Book book1 = new Book("小学语文");
        Book book2 = new Book("中学语文");
        book1.setBookType(bookType);
        book2.setBookType(bookType);
        session.save(bookType);
//        session.save(book1);
//        session.save(book2);
        tx.commit();
        session.close();
    }

    public static void  queryManyToOne(){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Book book = (Book) session.get(Book.class,1);
        System.out.println("--------------");
        System.out.println("Book.name: " + book.getName());
        System.out.println("--------------");
        System.out.print("BookType.name:" + book.getBookType().getName());
        tx.commit();
        session.close();
    }


    public static void  save(){
        Session session = factory.openSession();

        Book book = new Book("小学数学");
        Book book1 = new Book("中学数学");

        BookType bookType = new BookType("数学书");

        bookType.getBooks().add(book);
        bookType.getBooks().add(book1);

        Transaction tx = session.beginTransaction();
        session.save(bookType);
        session.save(book);
        session.save(book1);
        tx.commit();
        session.close();
    }

    public static void query(){
        Session session = factory.openSession();
        BookType bookType = (BookType) session.load(BookType.class, 1);
        System.out.println("bookType.id: " + bookType.getId());

        System.out.println("--------------");

        for(Book item: bookType.getBooks()){
            System.out.println("Book.id: " + item.getId());
        }
    }

    public  static void updata(){
        Session session = factory.openSession();
        Transaction tx =  session.beginTransaction();
        BookType bookType = new BookType("中学数学书");
        Book book = (Book) session.load(Book.class,2);
        bookType.getBooks().add(book);
        session.save(bookType);
        tx.commit();
        session.close();
    }

    public  static void delete(){
        Session session = factory.openSession();
        Transaction tx =  session.beginTransaction();
        Book book = (Book) session.load(Book.class,2);
        session.delete(book);
        tx.commit();
        session.close();
    }
}
