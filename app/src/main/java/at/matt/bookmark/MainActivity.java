package at.matt.bookmark;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_book) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void setBooks(List<Book> books) {
        Toast.makeText(this, books.get(0).getAuthor() + ": " + books.get(0).getTitle(), Toast.LENGTH_LONG).show();
    }
}
