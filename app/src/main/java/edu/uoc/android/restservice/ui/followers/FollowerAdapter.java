package edu.uoc.android.restservice.ui.followers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import edu.uoc.android.restservice.R;
import edu.uoc.android.restservice.rest.model.Followers;
import java.util.List;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.ViewHolder> {

    private Context context;
    private List<Followers> followersList;

    public FollowerAdapter(List<Followers> followersList) {
        this.followersList = followersList;
    }

    @Override
    public FollowerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
        context = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvUsername.setText(followersList.get(position).getLogin());
        Picasso.with(context).load(followersList.get(position).getAvatarUrl()).into(holder.ivAvatar);
    }

    @Override
    public int getItemCount() {
        return followersList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        ImageView ivAvatar;

        ViewHolder(View view) {
            super(view);
            tvUsername = (TextView) view.findViewById(R.id.item_follower_tv_username);
            ivAvatar = (ImageView) view.findViewById(R.id.item_follower_iv_avatar);
        }
    }
}
