package me.jansv.challenge.ui.screens.users;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.UserList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersPresenter implements UsersContract.Presenter {

    private UsersContract.View mView;

    private GithubService api;

    public UsersPresenter(GithubService api) {
        this.api = api;
    }

    @Override
    public void bind(UsersContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void fetchUserList() {
        final String filter = "language:java location:lagos";
        api.getUserList(filter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable disposable) {

                    }

                    @Override
                    public void onNext(@NonNull UserList userList) {
                        if(!mView.isActive())
                            return;
                            mView.showUserList(userList.getItems());
                            Log.e(TAG, userList.getItems().toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        if (mView.isActive()) {
                            mView.showNetworkErrorMessage();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
                /*.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if(!mView.isActive())
                    return;
                if (response.isSuccessful()) {
                    mView.showUserList(response.body().getItems());
                } else {
                    mView.showNetworkErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                if (mView.isActive()) {
                    mView.showNetworkErrorMessage();
                }
            }
        });*/
    }
}
