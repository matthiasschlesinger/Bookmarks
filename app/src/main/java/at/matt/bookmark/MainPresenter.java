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

    public MainPresenter(MainView view) {
        this.mainView = view;
        this.mainModel = new MainModel(this);
        mainModel.retrieveBooks();
    }

    public void actionAddBook() {
        mainView.showAddBookDialog();
    }

    @Override
    public void onFinished(List<Book> books) {
        mainView.setBooks(books);
    }

}
