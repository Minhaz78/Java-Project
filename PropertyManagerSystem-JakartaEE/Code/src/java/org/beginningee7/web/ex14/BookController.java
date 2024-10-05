package org.beginningee7.web.ex14;

import jakarta.annotation.PostConstruct;
import w10jsfp.web.Book;
import w10jsfp.web.BookEJB;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
//import jakarta.faces.bean.ManagedBean;
//import jakarta.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@Named("myBookController14")
@RequestScoped
public class BookController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private BookEJB bookEJB;

    private Book book = new Book();
    private List<Book> bookList = new ArrayList<Book>();

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doCreateBook() {
        book = bookEJB.createBook(book);
        bookList = bookEJB.findBooks();
        return "newBook.xhtml";
    }
    
    public String doCreateBook1() {
        book = bookEJB.createBook(book);
        bookList = bookEJB.findBooks();
        return "newBookJS.xhtml";
    }
    // ======================================
    // =       Initialization Method        =
    // ======================================
    
    // This method will be called right after the bean is created
    @PostConstruct
    public void init() {
        bookList = bookEJB.findBooks();  // Populate the book list when the bean is initialized
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}