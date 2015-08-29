package at.matt.bookmark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 29.08.2015.
 */
public class MainModel {
    private OnFinishedListener onFinishedListener;
    public MainModel(OnFinishedListener listener) {
        this.onFinishedListener = listener;
    }

    public void retrieveBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("The Circle", "Dave Eggers"));
        onFinishedListener.onFinished(books);
    }
}
