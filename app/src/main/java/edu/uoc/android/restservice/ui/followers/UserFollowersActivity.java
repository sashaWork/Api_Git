package edu.uoc.android.restservice.ui.followers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import edu.uoc.android.restservice.Data;
import edu.uoc.android.restservice.GithubAPI;
import edu.uoc.android.restservice.MainActivity;
import edu.uoc.android.restservice.R;
import edu.uoc.android.restservice.rest.adapter.GitHubAdapter;
import edu.uoc.android.restservice.rest.model.Email;
import edu.uoc.android.restservice.rest.model.Followers;
import edu.uoc.android.restservice.rest.model.GithubIssue;
import edu.uoc.android.restservice.rest.model.GithubRepo;
import edu.uoc.android.restservice.rest.model.Owner;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static edu.uoc.android.restservice.rest.contants.ApiConstants.EMAIL;
import static edu.uoc.android.restservice.rest.contants.ApiConstants.PASS;

public class UserFollowersActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private static final String BUNDLE_EXTRA_USERNAME = "sashaWork";

    private View content;
    private ProgressBar progressBar;
    private TextView tvRepos;
    private TextView tvFollowing;
    private TextView tvError;
    private RecyclerView rvFollowers;
    private ImageView ivUser;

    Button button;

    private TextView tv_user_email;
//    GithubRepo githubRepo;

    // Rest
    private Call<Owner> callOwner;
    private Call<List<Followers>> callFollowers;
    private Call<Email> callEmail;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    GithubAPI githubAPI;

    public static Intent makeIntent(Context context, String username) {
        Intent intent = new Intent(context, UserFollowersActivity.class);
        intent.putExtra(BUNDLE_EXTRA_USERNAME, username);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_followers);
//        String username = extractUserName();
//        GithubRepo githubRepo = new GithubRepo();

        Log.d(LOG_TAG, "UserFollowers onCreate: " + Data.getOwn() + " " + Data.own + " ");
        String username = Data.own;
        initViews();
        getUserInformation(username);
        getEmail();

//        button = (Button)findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                compositeDisposable.add(githubAPI.getRepos()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(getRepositoriesObserver()));
//
//                GithubRepo githubRepo = new GithubRepo();
//                compositeDisposable.add(githubAPI.getIssues(githubRepo.owner, githubRepo.name)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(getIssuesObserver()));
//
//
////                btnIn.setClickable(false); // setEnabled
//                Toast.makeText(UserFollowersActivity.this, "Need more latters in a field", Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }



//    private DisposableSingleObserver<List<GithubRepo>> getRepositoriesObserver() {
//        return new DisposableSingleObserver<List<GithubRepo>>() {
//            @Override
//            public void onSuccess(List<GithubRepo> value) {
//                if (!value.isEmpty()) {
////                    ArrayAdapter<GithubRepo> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
////                            android.R.layout.simple_spinner_dropdown_item, value);
////                    repositoriesSpinner.setAdapter(spinnerAdapter);
////                    repositoriesSpinner.setEnabled(true);
//                } else {
////                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
////                            android.R.layout.simple_spinner_dropdown_item, new String[]{"User has no repositories"});
////                    repositoriesSpinner.setAdapter(spinnerAdapter);
////                    repositoriesSpinner.setEnabled(false);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//                Toast.makeText(UserFollowersActivity.this, "Can not load repositories", Toast.LENGTH_SHORT).show();
//
//            }
//        };
//    }
//
//    private DisposableSingleObserver<List<GithubIssue>> getIssuesObserver() {
//        return new DisposableSingleObserver<List<GithubIssue>>() {
//            @Override
//            public void onSuccess(List<GithubIssue> value) {
//                if (!value.isEmpty()) {
////                    ArrayAdapter<GithubIssue> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
////                            android.R.layout.simple_spinner_dropdown_item, value);
////                    issuesSpinner.setEnabled(true);
////                    commentEditText.setEnabled(true);
////                    sendButton.setEnabled(true);
////                    issuesSpinner.setAdapter(spinnerAdapter);
//                } else {
////                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
////                            android.R.layout.simple_spinner_dropdown_item, new String[]{"Repository has no issues"});
////                    issuesSpinner.setEnabled(false);
////                    commentEditText.setEnabled(false);
////                    sendButton.setEnabled(false);
////                    issuesSpinner.setAdapter(spinnerAdapter);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                e.printStackTrace();
//                Toast.makeText(UserFollowersActivity.this, "Can not load issues", Toast.LENGTH_SHORT).show();
//            }
//        };
//    }


    @Override
    protected void onStop() {
        super.onStop();
        if (callOwner != null) {
            callOwner.cancel();
        }
        if (callFollowers != null) {
            callFollowers.cancel();
        }
        if (callEmail != null) {
            callEmail.cancel();
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

        tv_user_email = (TextView) findViewById(R.id.tv_user_email);
    }

    private void getUserInformation(final String username) {
        showLoading();
        callOwner = new GitHubAdapter().getOwner(username);
        callOwner.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                Owner owner = response.body();
                Log.d(LOG_TAG, "owner: " + owner);
                if (owner != null) {
                    Picasso.with(UserFollowersActivity.this).load(owner.getAvatarUrl()).into(ivUser);
                    tvRepos.setText(getString(R.string.user_followers_repositories, owner.getPublicRepos()));
                    tvFollowing.setText(getString(R.string.user_followers_following, owner.getFollowers()));
                    Log.d(LOG_TAG, "owner2: " + owner + "   " + owner.getFollowers());
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

    private void getEmail() {
        Log.d(LOG_TAG, "getEmail: " );
        callEmail = new GitHubAdapter().getEmail();
        callEmail.enqueue(new Callback<Email>() {
            @Override
            public void onResponse(Call<Email> call, Response<Email> response) {
                Email email = response.body();
//                Log.d(LOG_TAG, "email: " + email);
                if (email != null) {
                    tv_user_email.setText(email.getEmail()); //
                    Toast.makeText(UserFollowersActivity.this, email.getEmail(), Toast.LENGTH_SHORT).show();
//                    Log.d(LOG_TAG, "email2: " + email + "  " + email.getEmail() + "  " + email.getVisibility());
                } else {
                    showError();
                }
            }
            @Override
            public void onFailure(Call<Email> call, Throwable t) {
                Log.d(LOG_TAG, "showError: ");
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

    private void getRepositoris(String username) {
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
