package model;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FZD on 2017/3/17.
 */
public class BookType {

    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Set<Book> books = new HashSet<Book>();

    public BookType() {
    }

    public BookType(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
