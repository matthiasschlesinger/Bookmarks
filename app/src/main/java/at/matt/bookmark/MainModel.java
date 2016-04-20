package at.matt.bookmark;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 29.08.2015.
 */
public class MainModel {
    private OnFinishedListener onFinishedListener;
    private Context context;
    private BookDatabase database;

    public MainModel(Context context) {this.context = context;}

    public void retrieveBooks() {
        database = new BookDatabase(context);
        List<Book> books = database.getAllBooks();
        onFinishedListener.onFinished(books);
    }

    public void setOnFinishedListener(MainPresenter onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }

    public void addBook(Book book) {
        database.addBook(book);
    }

    public void deleteBook(Book book) {
        database.deleteBook(book);
    }

    public void updateBook(String id, String author, String title, String page) {
        database.updateBook(new Book(id, author, title, page));
    }
}
