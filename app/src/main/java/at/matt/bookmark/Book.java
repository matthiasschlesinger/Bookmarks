package at.matt.bookmark;

/**
 * Created by Matthias on 29.08.2015.
 */
public class Book {
    public String title;
    public String author;
    public String coverURL;
    public String ISBN;

    public Book(String title, String author, String coverURL, String ISBN) {
        this.title = title;
        this.author = author;
        this.coverURL = coverURL;
        this.ISBN = ISBN;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
