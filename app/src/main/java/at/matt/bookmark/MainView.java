package at.matt.bookmark;

/**
 * Created by Matthias on 29.08.2015.
 */

import java.util.List;

public interface MainView {
    public void addBook(Book book);
    public void setBooks(List<Book> books);
}