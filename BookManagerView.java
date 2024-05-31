import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class BookManagerView {
    private BookManagerController controller;
    private JPanel panel;

    public BookManagerView(BookManagerController controller) {
        this.controller = controller;
        this.panel = new JPanel();
        initView();
    }

    private void initView() {
        panel.setLayout(new BorderLayout());

        // Wyszukiwanie
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Szukaj");
        searchPanel.add(new JLabel("Szukaj:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Lista Ksiazek
        DefaultListModel<Book> bookListModel = controller.getBooks();
        JList<Book> bookListView = new JList<>(bookListModel);
        JScrollPane scrollPane = new JScrollPane(bookListView);

        // Sortowanie
        JPanel sortPanel = new JPanel();
        JComboBox<String> sortComboBox = new JComboBox<>(new String[]{"Tytuł", "Autor", "Rok Wydania", "Gatunek"});
        JButton sortButton = new JButton("Sortuj");
        sortPanel.add(new JLabel("Sortuj po:"));
        sortPanel.add(sortComboBox);
        sortPanel.add(sortButton);

        // Dodaj/Usun
        JPanel controlPanel = new JPanel();
        JButton addBookButton = new JButton("Dodaj książke");
        JButton removeBookButton = new JButton("Usuń książke");
        controlPanel.add(addBookButton);
        controlPanel.add(removeBookButton);

        // Dodaj ksiazke
        JPanel addBookPanel = new JPanel(new GridLayout(5, 2));
        addBookPanel.setVisible(false);
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField yearField = new JTextField(10);
        JTextField genreField = new JTextField(10);
        JButton confirmAddButton = new JButton("Confirm Add");
        addBookPanel.add(new JLabel("Tytuł:"));
        addBookPanel.add(titleField);
        addBookPanel.add(new JLabel("Autor:"));
        addBookPanel.add(authorField);
        addBookPanel.add(new JLabel("Rok Wydania:"));
        addBookPanel.add(yearField);
        addBookPanel.add(new JLabel("Gatunek:"));
        addBookPanel.add(genreField);
        addBookPanel.add(new JLabel());
        addBookPanel.add(confirmAddButton);

        // Działania
        searchButton.addActionListener(e -> {
            String regex = searchField.getText();
            bookListView.setModel(controller.searchBooks(regex));
        });

        sortButton.addActionListener(e -> {
            String criteria = (String) sortComboBox.getSelectedItem();
            Comparator<Book> comparator = null;
            switch (criteria) {
                case "Tytuł":
                    comparator = Comparator.comparing(Book::getTitle);
                    break;
                case "Autor":
                    comparator = Comparator.comparing(Book::getAuthor);
                    break;
                case "Rok Wydania":
                    comparator = Comparator.comparingInt(Book::getPublicationYear);
                    break;
                case "Gatunek":
                    comparator = Comparator.comparing(Book::getGenre);
                    break;
            }
            if (comparator != null) {
                bookListView.setModel(controller.sortBooks(comparator));
            }
        });

        addBookButton.addActionListener(e -> addBookPanel.setVisible(true));

        confirmAddButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            int year = Integer.parseInt(yearField.getText());
            String genre = genreField.getText();
            Book book = new Book(title, author, year, genre);
            controller.addBook(book);
            bookListView.setModel(controller.getBooks());
            addBookPanel.setVisible(false);
            titleField.setText("");
            authorField.setText("");
            yearField.setText("");
            genreField.setText("");
        });

        removeBookButton.addActionListener(e -> {
            Book selectedBook = bookListView.getSelectedValue();
            if (selectedBook != null) {
                controller.removeBook(selectedBook);
                bookListView.setModel(controller.getBooks());
            }
        });

        // Pozycjonowanie
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(sortPanel, BorderLayout.EAST);
        panel.add(controlPanel, BorderLayout.SOUTH);
        panel.add(addBookPanel, BorderLayout.WEST);
    }

    public JPanel getPanel() {
        return panel;
    }
}
