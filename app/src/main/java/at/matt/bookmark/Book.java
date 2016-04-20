package at.matt.bookmark;

import java.util.UUID;

/**
 * Created by Matthias on 29.08.2015.
 */
public class Book {
    public String title;
    public String author;
    public String coverURL;
    public String ISBN;
    public String page;
    public String id;

    public Book(String title, String author, String coverURL, String ISBN, String page) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.page = page;
        this.coverURL = coverURL;
        this.ISBN = ISBN;
    }

    public Book(String title, String author, String page) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.page = page;
    }

    public Book(String id, String title, String author, String page) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.page = page;
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

    public String getPage() {return page;}

    public String getId() {
        return id;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setId(String id) {
        this.id = id;
    }
}
