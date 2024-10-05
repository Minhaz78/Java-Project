package w10jsfp.w104;

import w10jsfp.web.Book;
import w10jsfp.web.BookEJB;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
//import jakarta.faces.bean.ManagedBean;
//import jakarta.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@Named("myBookController11")
@RequestScoped
public class BookController {

    //Attributes             
    @EJB
    private BookEJB bookEJB;

    private Book book = new Book();
    private List<Book> bookList = new ArrayList<Book>();

    //Public methods           
    public String doCreateBook() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (book.getIsbn() == null || "".equals(book.getIsbn())) {
            ctx.addMessage("bookForm:isbn", new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong isbn", "You should enter an ISBN number"));
        }
        if (book.getTitle() == null || "".equals(book.getTitle())) {
            ctx.addMessage("bookForm:title", new FacesMessage(FacesMessage.SEVERITY_WARN, "Wrong title", "You should enter a title for the book"));
        }

        if (ctx.getMessageList().size() != 0)
            return null;

        try {
            book = bookEJB.createBook(book);
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book has been created", "The book" + book.getTitle() + " has been created with id=" + book.getId()));
        } catch (Exception e) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Book hasn't been created", e.getMessage()));
        }
        return null;
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