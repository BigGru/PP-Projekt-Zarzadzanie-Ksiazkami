import javax.swing.*;
import java.io.IOException;

public class BookManager {
    private static final String DATA_FILE = "books.ser";
    private BookStore bookStore;
    private BookManagerController controller;
    private BookManagerView view;

    public BookManager() {
        bookStore = loadBooks();
        controller = new BookManagerController(bookStore);
        view = new BookManagerView(controller);
    }

    private BookStore loadBooks() {
        try {
            return BookStore.loadFromFile(DATA_FILE);
        } catch (IOException | ClassNotFoundException e) {
            return new BookStore();
        }
    }

    private void saveBooks() {
        try {
            bookStore.saveToFile(DATA_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        JFrame frame = new JFrame("Zarządzanie Książkami");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view.getPanel());
        frame.setSize(800, 600);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveBooks();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookManager().run());
    }
}
