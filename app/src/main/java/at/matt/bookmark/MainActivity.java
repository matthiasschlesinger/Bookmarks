package at.matt.bookmark;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Matthias on 29.08.2015.
 * Displays data. Implements the MainView interface so the MainPresenter can be free of all Android
 * dependencies.
 */
public class MainActivity extends ActionBarActivity implements MainView, AdapterView.OnItemLongClickListener {
    private MainPresenter presenter;
    private ListView bookList;
    private ActionMode actionMode;
    private Toolbar toolbar;
    private BookAdapter adapter;
    private int currentPosition;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.d("MainActivity", "Position = " + currentPosition);
            Book book = (Book)adapter.getItem(currentPosition);
            switch (item.getItemId()) {
                case R.id.action_delete:
                    mode.finish(); // Action picked, so close the CAB
                    presenter.deleteBook(book);
                    return true;
                case R.id.action_edit:
                    showEditBookDialog(book);
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookList = (ListView) findViewById(R.id.listview_books);
        View addBookButton = findViewById(R.id.fab);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.actionAddBook();
            }
        });
        MainModel model = new MainModel(this);
        presenter = new MainPresenter(this, model); //this is a MainView
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void setBooks(List<Book> books) {
        adapter = new BookAdapter(this, R.layout.books_list_item, books);
        bookList.setAdapter(adapter);
        bookList.setOnItemLongClickListener(this);
    }

    @Override
    public void showAddBookDialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View addBookDialogView = factory.inflate(R.layout.add_book_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        final EditText authorEditText = (EditText) addBookDialogView.findViewById(R.id.book_author);
        final EditText titleEditText = (EditText) addBookDialogView.findViewById(R.id.book_title);
        final EditText bookmarkEditText = (EditText) addBookDialogView.findViewById(R.id.book_bookmark);
        deleteDialog.setView(addBookDialogView);
        addBookDialogView.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = authorEditText.getText().toString();
                String title = titleEditText.getText().toString();
                String page = bookmarkEditText.getText().toString();
                deleteDialog.dismiss();
                presenter.addBook(author, title, page);
            }
        });

        deleteDialog.show();
    }

    private void showEditBookDialog(final Book book) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View addBookDialogView = factory.inflate(
                R.layout.add_book_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
        final EditText authorEditText = (EditText) addBookDialogView.findViewById(R.id.book_author);
        final EditText titleEditText = (EditText) addBookDialogView.findViewById(R.id.book_title);
        final EditText bookmarkEditText = (EditText) addBookDialogView.findViewById(R.id.book_bookmark);
        authorEditText.setText(book.getAuthor());
        titleEditText.setText(book.getTitle());
        bookmarkEditText.setText(book.getPage());
        deleteDialog.setView(addBookDialogView);
        ((Button)addBookDialogView.findViewById(R.id.btn_add)).setText("Edit Bookmark");
        addBookDialogView.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = authorEditText.getText().toString();
                String title = titleEditText.getText().toString();
                String page = bookmarkEditText.getText().toString();
                deleteDialog.dismiss();
                presenter.updateBook(book.getId(), title, author, page);
            }
        });

        deleteDialog.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (actionMode != null) {
            return false;
        }
        actionMode = toolbar.startActionMode(mActionModeCallback);
        view.setSelected(true);
        currentPosition = position;
        return false;
    }

    @Override
    public void notifyMissingParameter() {
        Toast.makeText(this, "Missing Parameter", Toast.LENGTH_LONG).show();
    }

}
