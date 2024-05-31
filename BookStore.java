import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BookStore implements Serializable {
    private List<Book> books;

    public BookStore() {
        books = new ArrayList<>();
        loadSampleBooks();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> searchBooks(String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return books.stream()
                .filter(book -> pattern.matcher(book.toString()).find())
                .collect(Collectors.toList());
    }

    public List<Book> sortBooks(Comparator<Book> comparator) {
        return books.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    private void loadSampleBooks() {
        books.add(new Book("Władca Pierścieni", "J.R.R. Tolkien", 1954, "Fantasy"));
        books.add(new Book("Zabić drozda", "Harper Lee", 1960, "Powieść"));
        books.add(new Book("Rok 1984", "George Orwell", 1949, "Dystopia"));
        books.add(new Book("Duma i uprzedzenie", "Jane Austen", 1813, "Romans"));
        books.add(new Book("Wielki Gatsby", "F. Scott Fitzgerald", 1925, "Powieść"));
        books.add(new Book("Moby Dick", "Herman Melville", 1851, "Przygodowa"));
        books.add(new Book("Wojna i pokój", "Leo Tolstoy", 1869, "Historical"));
        books.add(new Book("Buszujący w zbożu", "J.D. Salinger", 1951, "Powieść"));
        books.add(new Book("Zbrodnia i kara", "Fyodor Dostoevsky", 1866, "Powieść Psychologiczna"));
        books.add(new Book("Hobbit, czyli tam i z powrotem", "J.R.R. Tolkien", 1937, "Fantasy"));
        books.add(new Book("Anna Karenina", "Leo Tolstoy", 1877, "Powieść"));
        books.add(new Book("Sto lat samotności", "Gabriel Garcia Marquez", 1967, "Realizm magiczny"));
        books.add(new Book("Nowy wspaniały świat", "Aldous Huxley", 1932, "Dystopia"));
        books.add(new Book("Dziwne losy Jane Eyre", "Charlotte Brontë", 1847, "Romans"));
        books.add(new Book("Wichrowe Wzgórza", "Emily Brontë", 1847, "Romans"));
        books.add(new Book("Droga", "Cormac McCarthy", 2006, "Postapokaliptyczna"));
        books.add(new Book("Bracia Karamazow", "Fyodor Dostoevsky", 1880, "Powieść"));
        books.add(new Book("Pani Bovary", "Gustave Flaubert", 1857, "Powieść"));
        books.add(new Book("Boska komedia", "Dante Alighieri", 1320, "Epos"));
        books.add(new Book("Ulisses", "James Joyce", 1922, "Powieść"));
        books.add(new Book("Don Kichot", "Miguel de Cervantes", 1605, "Przygodowa"));
        books.add(new Book("Paragraf 22", "Joseph Heller", 1961, "Satyra"));
        books.add(new Book("Grona gniewu", "John Steinbeck", 1939, "Powieść"));
        books.add(new Book("Opowieść o dwóch miastach", "Charles Dickens", 1859, "Powieść Historyczna"));
        books.add(new Book("Portret Doriana Graya", "Oscar Wilde", 1890, "Powieść"));
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    public static BookStore loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (BookStore) in.readObject();
        }
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
