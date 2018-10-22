package edu.uoc.android.restservice.rest.adapter;

import android.util.Log;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import edu.uoc.android.restservice.MainActivity;
import edu.uoc.android.restservice.TLSSocketFactory;
import edu.uoc.android.restservice.rest.contants.ApiConstants;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class BaseAdapter {

    private static Retrofit retrofit;
    private static HttpLoggingInterceptor.Level LEVEL_LOG = HttpLoggingInterceptor.Level.BODY;
    final String LOG_TAG = "myLogs";

    BaseAdapter(String baseUrl) {
        init(baseUrl);
    }

    private void init(String baseUrl) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient();
            try {
                client = new OkHttpClient.Builder()
                        .sslSocketFactory(new TLSSocketFactory())

//                        .addInterceptor(new Interceptor() {
//                            @Override
//                            public okhttp3.Response intercept(Interceptor.Chain chain) throws
//                                    IOException {
//                                Request originalRequest = chain.request();
//
//                                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                                        Credentials.basic(MainActivity.username, MainActivity.password));
//
//                                Log.d(LOG_TAG, "createGithubApi: " + username + " " + password);
//
//                                Request newRequest = builder.build();
//                                return chain.proceed(newRequest);
//                            }
//                        })

                        .build();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .client(client) // .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }


//    okHttpClient = new OkHttpClient.Builder().sslSocketFactory(new TLSSocketFactory())
//            .addInterceptor(new Interceptor() {
//        @Override
//        public okhttp3.Response intercept(Interceptor.Chain chain) throws
//        IOException {
//            Request originalRequest = chain.request();
//
//            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
//                    Credentials.basic(username, password));
//
//            Log.d(LOG_TAG, "createGithubApi: " + username + " " + password);
//
//            Request newRequest = builder.build();
//            return chain.proceed(newRequest);
//        }
//    }).build();



//    private OkHttpClient getClient() {
//        OkHttpClient.Builder builderClientHttp = new OkHttpClient().newBuilder();
//        // Show HTTPS logs in dev mode
//        if (ApiConstants.isDebugging) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(LEVEL_LOG);
//            builderClientHttp.addInterceptor(interceptor);
//        }
//        return builderClientHttp.build();
//    }

    <T> T createService(Class<T> _class) {
        return retrofit.create(_class);
    }
}
