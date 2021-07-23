package me.jansv.challenge.api;


import androidx.annotation.Nullable;

import java.util.List;

import io.reactivex.Observable;
import me.jansv.challenge.model.Root;
import me.jansv.challenge.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    @GET("/search/users")
    Observable<UserList> getUserList(@Query("q") @Nullable String filter);

    @GET("/users/{user}/repos")
    Observable<List<Root>> getRepository(@Path("user") @Nullable String user);
}
