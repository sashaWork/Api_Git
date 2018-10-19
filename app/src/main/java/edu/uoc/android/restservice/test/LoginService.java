package edu.uoc.android.restservice.test;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    @GET("authorize")
//    Call<User> getLogin(); authorizations user/following GET https://github.com/login/oauth/authorize
    Call<User> basicLogin();
}
