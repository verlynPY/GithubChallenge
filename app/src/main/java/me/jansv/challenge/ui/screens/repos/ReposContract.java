package me.jansv.challenge.ui.screens.repos;

import java.util.List;

import me.jansv.challenge.model.Root;
import me.jansv.challenge.model.User;
import me.jansv.challenge.ui.screens.BasePresenter;
import me.jansv.challenge.ui.screens.BaseView;

public interface ReposContract {

    interface View extends BaseView {
        void showNoNetworkMessage();

        void showNetworkErrorMessage();

        void showReposList(List<Root> repos);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchReposList(String path);
    }
}
