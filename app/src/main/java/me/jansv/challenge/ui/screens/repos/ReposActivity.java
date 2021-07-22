package me.jansv.challenge.ui.screens.repos;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import me.jansv.challenge.R;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.Root;
import me.jansv.challenge.model.User;
import me.jansv.challenge.network.NetworkManager;
import me.jansv.challenge.ui.screens.users.UsersAdapter;
import me.jansv.challenge.ui.screens.users.UsersContract;
import me.jansv.challenge.ui.screens.users.UsersPresenter;
import me.jansv.challenge.util.schedulers.SchedulerProvider;

public class ReposActivity extends AppCompatActivity implements ReposContract.View {

    @Inject
    GithubService api;

    @Inject
    NetworkManager connectivity; // optional component

    @Inject
    SchedulerProvider schedulers;

    @BindView(R.id.content_repos)
    View contentView;

    @BindView(R.id.rv_user_repositorie)
    RecyclerView reposList;

    private CompositeDisposable subscriptions = new CompositeDisposable();

    private ReposContract.Presenter mPresenter;

    private boolean mIsActive;

    String path = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        ButterKnife.bind(this);
        AndroidInjection.inject(this);

        mPresenter = new ReposPresenter(api);

        setupViews();
        //scheduleForTitleChange();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActive = true;
        mPresenter.bind(this);

        // this call is totally optional, you can without any damage comment it and uncomment the below one
        //fetchUsersWhenReady();
        mPresenter.fetchReposList("moyheen");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsActive = false;
        mPresenter.unbind();
        subscriptions.clear();
    }


    private void setupViews() {
        reposList.setHasFixedSize(true);
        reposList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    @Override
    public void showNoNetworkMessage() {
        Snackbar.make(contentView, R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v ->
                        mPresenter.fetchReposList(path)
                )
                .show();
    }

    @Override
    public void showNetworkErrorMessage() {
        Snackbar.make(contentView, R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v ->
                        mPresenter.fetchReposList(path)
                )
                .show();
    }

    @Override
    public void showReposList(List<Root> repos) {
        reposList.setAdapter(new ReposAdapter(repos));
    }


    @Override
    public boolean isActive() {
        return mIsActive;
    }
}
