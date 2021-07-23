package me.jansv.challenge.ui.screens.users;

import static me.jansv.challenge.util.Constants.KEY_USER_PATH;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.jansv.challenge.R;
import me.jansv.challenge.model.Root;
import me.jansv.challenge.model.User;
import me.jansv.challenge.ui.screens.repos.ReposActivity;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.Holder>{



    private List<User> users;
    private Context mContext;

    public UsersAdapter(List<User> users, Context context) {

        this.users = users;
        this.mContext = context;
    }
    //private final OnClickListener mOnClickListener = new MyOnClickListener();


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        /*View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "OK", Toast.LENGTH_LONG).show();
            }
        });
        return new Holder(view);*/

        return new Holder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        User user = users.get(i);

        holder.bind(users.get(i));
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), user.getLogin(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), ReposActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(KEY_USER_PATH, user.getLogin());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_name)
        TextView userName;

        @BindView(R.id.user_state)
        TextView userState;

        @BindView(R.id.profile_image)
        ImageView userImage;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(User user) {
            userName.setText(user.getLogin());
            userState.setText("Lagos");
            Glide.with(itemView.getContext()).load(user.getAvatarUrl()).into(userImage);
        }
    }
}
