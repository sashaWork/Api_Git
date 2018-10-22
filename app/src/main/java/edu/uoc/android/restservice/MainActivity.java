package edu.uoc.android.restservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.uoc.android.restservice.rest.contants.ApiConstants;
import edu.uoc.android.restservice.rest.model.Autorization;
//import edu.uoc.android.restservice.test.LoginResponse;
import edu.uoc.android.restservice.rest.model.GithubIssue;
import edu.uoc.android.restservice.rest.model.GithubRepo;
import edu.uoc.android.restservice.rest.service.GitHubService;
import edu.uoc.android.restservice.ui.followers.UserFollowersActivity;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static edu.uoc.android.restservice.rest.contants.ApiConstants.EMAIL;
import static edu.uoc.android.restservice.rest.contants.ApiConstants.PASS;


public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    private EditText etEmail, etPass;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    //    GitHubService githubAPI;
    GithubAPI githubAPI;
//    String username;
//    String password;

    String result = null;
    //    private static final String API_URL = "https://api.github.com/user";
    String response;
    private Call<Autorization> callOwner;
    public String username = "oshturniev@griddynamics.com", password = "Sasha502633!";
//    String encode = Base64.encodeToString((username + ":" + password).getBytes(),
//            Base64.DEFAULT).replace("\n", "");

    CheckBox cbSave;
    Button btnIn, btnOffline;
    String email, pass;

    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

//        JsonReader.setLenient(true);

//        LoginService loginService =
//                ServiceGenerator.createService(LoginService.class, username, password);
//        Call<User> call = loginService.basicLogin();
//            Log.d(LOG_TAG, "loginService" + " " );
//        call.enqueue(new Callback<User>() {
//                         @Override
//                         public void onResponse(Call<User> call, Response<User> response) {
//                             if (response.isSuccessful()) {
//                                 // user object available
//                                 Log.d(LOG_TAG, "isSuccessful: " + EMAIL + " " + PASS);
//                                 Toast.makeText(MainActivity.this, User.getResource_id(), Toast.LENGTH_LONG).show();
//                             } else {
//                                 // error response, no access to resource?
//                                 Log.d(LOG_TAG, "error" + " " + response);
//                             }
//                         }
//
//                         @Override
//                         public void onFailure(Call<User> call, Throwable t) {
//                             // something went completely south (like no internet connection)
//                             Log.d(LOG_TAG, "T: " + t.getMessage());
//                         }
//                     });

//        Log.d(LOG_TAG, "onCreate: " + EMAIL + " " + PASS);
//        Log.d(LOG_TAG, "MainActivity :  " + result);
//        Log.d(LOG_TAG, "MainActivity 1 :  " + result);
//
//        Request request = new Request.Builder()
//                .header("Authorization", "Basic " + encode).url("https://api.github.com/authorizations").build();
    }

    void init() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        cbSave = (CheckBox) findViewById(R.id.cbSave);
        btnIn = (Button) findViewById(R.id.btnIn);
        btnOffline = (Button) findViewById(R.id.btnOffline);

//        username = etEmail.getText().toString();
//        password = etPass.getText().toString();


        cbSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "Save", Toast.LENGTH_SHORT).show();
//                    saveText();
                } else {
                    Toast.makeText(MainActivity.this, "Not save", Toast.LENGTH_SHORT).show();
//                    loadText();
                }
            }
        });

        Log.d(LOG_TAG, "MainActivity: " + username + " " + password);
        createGithubAPI();
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, UserFollowersActivity.class);
                startActivity(intent);
//                btnIn.setClickable(false); // setEnabled
                Toast.makeText(MainActivity.this, "Need more latters in a field", Toast.LENGTH_SHORT).show();

            }
        });

        btnOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compositeDisposable.add(githubAPI.getRepos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getRepositoriesObserver()));

//                GithubRepo githubRepo = new GithubRepo();
//                compositeDisposable.add(githubAPI.getIssues(githubRepo.owner, githubRepo.name)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(getIssuesObserver()));

//                btnIn.setClickable(false); // setEnabled
//                Toast.makeText(MainActivity.this, "Need more latters in a field", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private DisposableSingleObserver<List<GithubRepo>> getRepositoriesObserver() {
        return new DisposableSingleObserver<List<GithubRepo>>() {
            @Override
            public void onSuccess(List<GithubRepo> value) {
                if (!value.isEmpty()) {
//                    ArrayAdapter<GithubRepo> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
//                            android.R.layout.simple_spinner_dropdown_item, value);
//                    repositoriesSpinner.setAdapter(spinnerAdapter);
//                    repositoriesSpinner.setEnabled(true);
                } else {
//                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
//                            android.R.layout.simple_spinner_dropdown_item, new String[]{"User has no repositories"});
//                    repositoriesSpinner.setAdapter(spinnerAdapter);
//                    repositoriesSpinner.setEnabled(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Can not load repositories", Toast.LENGTH_SHORT).show();

            }
        };
    }

    private DisposableSingleObserver<List<GithubIssue>> getIssuesObserver() {
        return new DisposableSingleObserver<List<GithubIssue>>() {
            @Override
            public void onSuccess(List<GithubIssue> value) {
                if (!value.isEmpty()) {
//                    ArrayAdapter<GithubIssue> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
//                            android.R.layout.simple_spinner_dropdown_item, value);
//                    issuesSpinner.setEnabled(true);
//                    commentEditText.setEnabled(true);
//                    sendButton.setEnabled(true);
//                    issuesSpinner.setAdapter(spinnerAdapter);
                } else {
//                    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(MainActivity.this,
//                            android.R.layout.simple_spinner_dropdown_item, new String[]{"Repository has no issues"});
//                    issuesSpinner.setEnabled(false);
//                    commentEditText.setEnabled(false);
//                    sendButton.setEnabled(false);
//                    issuesSpinner.setAdapter(spinnerAdapter);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Can not load issues", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void createGithubAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(GithubRepo.class, new GithubRepoDeserializer())
                .create();

        Log.d(LOG_TAG, "createGithubApi: " + username + " " + password);
        OkHttpClient okHttpClient = null;
        try {
            okHttpClient = new OkHttpClient.Builder().sslSocketFactory(new TLSSocketFactory())
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();

                            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                                    Credentials.basic(username, password));

//                            Request request = new Request.Builder()
//                .header("Authorization", Credentials.basic(username, password)).build();
//
//                            return chain.proceed(request);

                            Request newRequest = builder.build();
                            return chain.proceed(newRequest);
                        }
                    }).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "createGithubApi1 : " + username + " " + password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GithubAPI.ENDPOINT) // ApiConstants.BASE_GITHUB_URL
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        githubAPI = retrofit.create(GithubAPI.class); // GitHubService
    }
}
