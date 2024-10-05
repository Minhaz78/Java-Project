package w10jsfp.w106;

import w10jsfp.web.Book;
import w10jsfp.web.BookEJB;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
//import jakarta.faces.bean.ManagedBean;
//import jakarta.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@Named("myBookController13")
@RequestScoped
public class BookController {

    //Attributes             
    @EJB
    private BookEJB bookEJB;

    private Book book = new Book();
    private List<Book> bookList = new ArrayList<Book>();

    //Public methods           
    public String doCreateBook() {
        book = bookEJB.createBook(book);
        bookList = bookEJB.findBooks();
        return "listBooks.xhtml";
    }

    //Getters & Setters         
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