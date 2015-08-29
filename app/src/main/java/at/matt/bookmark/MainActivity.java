package at.matt.bookmark;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Matthias on 29.08.2015.
 * Displays data. Implements the MainView interface so the MainPresenter can be free of all Android
 * dependencies.
 */
public class MainActivity extends ActionBarActivity implements MainView {
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this); //this is a MainView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_book) {
            presenter.actionAddBook();
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

    @Override
    public void showAddBookDialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View addBookDialogView = factory.inflate(
                R.layout.add_book_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        deleteDialog.setView(addBookDialogView);
        addBookDialogView.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //your business logic
                deleteDialog.dismiss();
            }
        });

        deleteDialog.show();
    }
}
