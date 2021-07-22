package me.jansv.challenge.ui.screens.repos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jansv.challenge.R;
import me.jansv.challenge.model.Root;
import me.jansv.challenge.model.User;

import java.util.List;


public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepostHolder>{

    private List<Root> repos;

    public ReposAdapter(List<Root> repos) {
        this.repos = repos;
    }

    @NonNull
    @Override
    public RepostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RepostHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_repositories_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepostHolder repostHolder, int i) {
        repostHolder.bind(repos.get(i));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class RepostHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_perfil_photo)
        ImageView userImage;

        @BindView(R.id.tv_username)
        TextView userName;

        @BindView(R.id.tv_user_repositorie)
        TextView userRepositories;

        public RepostHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Root repos) {
            userName.setText(repos.name);
            //userRepositories.setText(user.getHtmlUrl());
            //Glide.with(itemView.getContext()).load(user.getAvatarUrl()).into(userImage);
        }
    }
}
