package edu.uoc.android.restservice.ui.followers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import edu.uoc.android.restservice.R;
import edu.uoc.android.restservice.rest.adapter.GitHubAdapter;
import edu.uoc.android.restservice.rest.model.Followers;
import edu.uoc.android.restservice.rest.model.Owner;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFollowersActivity extends AppCompatActivity {

    private static final String BUNDLE_EXTRA_USERNAME = "username";

    private View content;
    private ProgressBar progressBar;
    private TextView tvRepos;
    private TextView tvFollowing;
    private TextView tvError;
    private RecyclerView rvFollowers;
    private ImageView ivUser;

    // Rest
    private Call<Owner> callOwner;
    private Call<List<Followers>> callFollowers;

    public static Intent makeIntent(Context context, String username) {
        Intent intent = new Intent(context, UserFollowersActivity.class);
        intent.putExtra(BUNDLE_EXTRA_USERNAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_followers);
        String username = extractUserName();
        initViews();
        getUserInformation(username);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (callOwner != null) {
            callOwner.cancel();
        }
        if (callFollowers != null) {
            callFollowers.cancel();
        }
    }

    private String extractUserName() {
        return getIntent().getStringExtra(BUNDLE_EXTRA_USERNAME);
    }

    private void initViews() {
        content = findViewById(R.id.user_followers_content);
        progressBar = (ProgressBar) findViewById(R.id.user_followers_progress);
        tvRepos = (TextView) findViewById(R.id.user_followers_profile_repos_text);
        tvFollowing = (TextView) findViewById(R.id.user_followers_profile_following_text);
        tvError = (TextView) findViewById(R.id.user_followers_error_text);
        rvFollowers = (RecyclerView) findViewById(R.id.user_followers_recycler_view);
        ivUser = (ImageView) findViewById(R.id.user_followers_profile_image);
    }

    private void getUserInformation(final String username) {
        showLoading();
        callOwner = new GitHubAdapter().getOwner(username);
        callOwner.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                Owner owner = response.body();
                if (owner != null) {
                    Picasso.with(UserFollowersActivity.this).load(owner.getAvatarUrl()).into(ivUser);
                    tvRepos.setText(getString(R.string.user_followers_repositories, owner.getPublicRepos()));
                    tvFollowing.setText(getString(R.string.user_followers_following, owner.getId()));
                    getFollowers(username);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                showError();
            }
        });
    }

    private void getFollowers(String username) {
        callFollowers = new GitHubAdapter().getFollowers(username);
        callFollowers.enqueue(new Callback<List<Followers>>() {
            @Override
            public void onResponse(Call<List<Followers>> call, Response<List<Followers>> response) {
                List<Followers> list = response.body();
                if (list != null) {
                    FollowerAdapter adapter = new FollowerAdapter(list);
                    rvFollowers.setAdapter(adapter);
                    showUserContent();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Followers>> call, Throwable t) {
                showError();
            }
        });
    }

    private void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
    }

    private void showError() {
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
    }

    private void showUserContent() {
        progressBar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);
    }
}
