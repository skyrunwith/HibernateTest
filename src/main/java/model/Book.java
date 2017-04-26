package model;

import javax.persistence.Entity;

/**
 * Created by FZD on 2017/3/17.
 */

public class Book {

    private int id;
    private String name;
    private BookType bookType;


    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}
