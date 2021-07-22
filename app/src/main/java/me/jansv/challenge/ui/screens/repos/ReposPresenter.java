package me.jansv.challenge.ui.screens.repos;

import java.util.List;

import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.Root;
import me.jansv.challenge.model.UserList;
import me.jansv.challenge.ui.screens.users.UsersContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposPresenter implements ReposContract.Presenter{

    private ReposContract.View mView;

    private GithubService api;

    public ReposPresenter(GithubService api) {
        this.api = api;
    }

    @Override
    public void bind(ReposContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void fetchReposList(String path) {
        //final String path = "";
        api.getRepository(path).enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if(!mView.isActive())
                    return;
                if (response.isSuccessful()) {
                    mView.showReposList(response.body());
                } else {
                    mView.showNetworkErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                if (mView.isActive()) {
                    mView.showNetworkErrorMessage();
                }
            }
        });
    }
}
