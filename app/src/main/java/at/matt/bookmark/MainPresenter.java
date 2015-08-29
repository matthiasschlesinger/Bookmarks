package at.matt.bookmark;

import java.util.List;

/**
 * Created by Matthias on 29.08.2015.
 */
public class MainPresenter implements OnFinishedListener {
    private MainView mainView;
    private MainModel mainModel;

    public MainPresenter(MainView view) {
        this.mainView = view;
        this.mainModel = new MainModel(this);
        mainModel.retrieveBooks();
    }

    @Override
    public void onFinished(List<Book> books) {
        mainView.setBooks(books);
    }
}
