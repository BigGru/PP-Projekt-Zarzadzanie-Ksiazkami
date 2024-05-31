import javax.swing.*;
import java.util.Comparator;

public class BookManagerController {
    private BookStore bookStore;

    public BookManagerController(BookStore bookStore) {
        this.bookStore = bookStore;
    }

    public DefaultListModel<Book> getBooks() {
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : bookStore.getBooks()) {
            model.addElement(book);
        }
        return model;
    }

    public void addBook(Book book) {
        bookStore.addBook(book);
    }

    public void removeBook(Book book) {
        bookStore.removeBook(book);
    }

    public DefaultListModel<Book> searchBooks(String regex) {
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : bookStore.searchBooks(regex)) {
            model.addElement(book);
        }
        return model;
    }

    public DefaultListModel<Book> sortBooks(Comparator<Book> comparator) {
        DefaultListModel<Book> model = new DefaultListModel<>();
        for (Book book : bookStore.sortBooks(comparator)) {
            model.addElement(book);
        }
        return model;
    }
}
