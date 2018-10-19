package edu.uoc.android.restservice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import edu.uoc.android.restservice.rest.adapter.GitHubAdapter;
import edu.uoc.android.restservice.rest.model.Autorization;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    private TextView textView, textView2;
    String result = null;
    private static final String API_URL = "https://api.github.com/user";
    String response;
    private Call<Autorization> callOwner;
    String username = "oshturniev@griddynamics.com", password = "Sasha502633!";
    String encode = Base64.encodeToString((username + ":" + password).getBytes(), Base64.DEFAULT).replace("\n", "");

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

//            result = Authorization("oshturniev@griddynamics.com", "Sasha502633!");

            textView = (TextView)findViewById(R.id.textView);
            textView2 = (TextView)findViewById(R.id.textView2);
//
////            textView.setText(result);
            Log.d(LOG_TAG, "MainActivity:  " + result);
//            getUserInformation(username, password);
//            Authorization(username, password);
            Log.d(LOG_TAG, "MainActivity:  " + result);

//
//            // Define the interceptor, add authentication headers
//            Interceptor interceptor = new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request newRequest = chain.request().newBuilder().addHeader("Authorization", "Retrofit-Sample-App").build();
//                    return chain.proceed(newRequest);
//                }
//            };
//
//            String str = "username:password";
//            String base64EncodedUsernamePassword;
//
//            try {
//                base64EncodedUsernamePassword = Base64.encode(str.getBytes("UTF-8"), Base64.NO_WRAP);
//            } catch (UnsupportedEncodingException e) {
//                // Weird, no UTF-8 encoding found?
//            }

//            RequestInterceptor interceptor = new RequestInterceptor() {
//                @Override
//                public void intercept(RequestFacade request) {
//                    request.addHeader("Authentication", "Basic " + username + ":" + password);
//                }
//            };
//
//            RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint(GithubClient.API_URL)
//                    .setRequestInterceptor(interceptor)
//                    .build();


//            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//                @Override
//                public okhttp3.Response intercept(Chain chain) throws IOException {
//                    Request newRequest  = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer " + encode)
//                            .build();
//                    return chain.proceed(newRequest);
//                }
//            }).build();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .client(client)
//                    .baseUrl("https://api.github.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
////    passwd = getpass.getpass() # <- this just puts a string in passwd (plaintext)
//
////    req = urllib2.Request("https://api.github.com/authorizations")
//
////            # add the username and password info to the request
////            base64string = base64.encodestring('%s:%s' % (username, passwd)).replace('\n', '')
//
////            String encode = Base64.encodeToString((username + ":" + password).getBytes(), Base64.DEFAULT).replace("\n", "");
////
//        Request request = new Request.Builder()
//                .header("Authorization", "Basic " + encode).url("https://api.github.com/authorizations").build();


//req.add_header("Authorization", "Basic %s" % base64string)

//    String data = {"scopes":["repo"], "note":"Access to your repository."};
//    String result = urllib2.urlopen(request, data);
//    result = json.loads('\n'.join(result.readlines()));
//    token = result['token'];
//
////    Once you have this token, it can be used for any "repo" scope actions. Lets add a new issue to a repository:
////
////            # add an issue to the tracker using the new token
//            repo = 'name_of_repo'
//    data = json.dumps({'title': 'My automated issue.'})
//    req = urllib2.Request("https://api.github.com/repos/%s/%s/issues" % (username, repo))
//            req.add_header("Authorization", "token %s" % token)
//    result = urllib2.urlopen(req, data)
//
//    result = json.loads('\n'.join(result.readlines()))
//    print result['number']



        }


}
