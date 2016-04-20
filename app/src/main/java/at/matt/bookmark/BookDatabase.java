package at.matt.bookmark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class BookDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BookDB";
    private static final String TABLE_BOOKS = "books";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_PAGE = "page";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_AUTHOR,KEY_PAGE};

    public BookDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOKS + " ( " +
                "id TEXT PRIMARY KEY, " +
                "title TEXT, "+
                "author TEXT, "+
                "page TEXT )";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        this.onCreate(db);
    }

    public void addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, book.getId());
        values.put(KEY_TITLE, book.getTitle());
        values.put(KEY_AUTHOR, book.getAuthor());
        values.put(KEY_PAGE, book.getPage());

        db.insert(TABLE_BOOKS,null,values);

        db.close();
    }

    public Book getBook(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_BOOKS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        Log.d("getBook(" + id + ")", book.toString());

        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        String query = "SELECT  * FROM " + TABLE_BOOKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                books.add(book);
            } while (cursor.moveToNext());
        }

        return books;
    }

    public int updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("page", book.getPage());

        int i = db.update(TABLE_BOOKS, values, KEY_ID + " = ?", new String[] { String.valueOf(book.getId())});

        db.close();

        return i;
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_BOOKS, //table name
                KEY_ID+" = ?",  // selections
                new String[] { String.valueOf(book.getId()) }); //selections args

        db.close();
    }
}