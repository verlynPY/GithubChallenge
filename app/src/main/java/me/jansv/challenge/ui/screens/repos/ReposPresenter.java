package me.jansv.challenge.ui.screens.repos;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
        api.getRepository(path)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Root>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull List<Root> root) {
                        if(!mView.isActive())
                            return;
                            mView.showReposList(root);

                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                /*.enqueue(new Callback<List<Root>>() {
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
        });*/
    }
}
