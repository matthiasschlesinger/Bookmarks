package at.matt.bookmark;

import java.util.List;

/**
 * Created by Matthias on 29.08.2015.
 * Link between Model and View. Asks Model to retrieve all books. Model notifies Presenter via
 * OnFinishedListener
 */
public class MainPresenter implements OnFinishedListener {
    private MainView mainView;
    private MainModel mainModel;

    public MainPresenter(MainView view, MainModel model) {
        this.mainView = view;
        this.mainModel = model;
        mainModel.setOnFinishedListener(this);
        mainModel.retrieveBooks();
    }

    @Override
    public void onFinished(List<Book> books) {
        mainView.setBooks(books);
    }

    public void addBook(String author, String title, String page) {
        if(author == null || author == "" || author.trim().length() == 0 || title == null || title == "" || title.trim().length() == 0 ||  page == null || page == "" || page.trim().length() == 0)
            mainView.notifyMissingParameter();
        mainModel.addBook(new Book(author, title, page));
        mainModel.retrieveBooks();
    }

    public void actionAddBook() {
        mainView.showAddBookDialog();
    }

    public void deleteBook(Book book) {
        mainModel.deleteBook(book);
        mainModel.retrieveBooks();
    }

    public void updateBook(String id, String author, String title, String page) {
        mainModel.updateBook(id, author, title, page);
        mainModel.retrieveBooks();
    }
}
