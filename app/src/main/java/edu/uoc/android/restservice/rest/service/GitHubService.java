package edu.uoc.android.restservice.rest.service;

import edu.uoc.android.restservice.rest.contants.ApiConstants;
import edu.uoc.android.restservice.rest.model.Autorization;
import edu.uoc.android.restservice.rest.model.Email;
import edu.uoc.android.restservice.rest.model.Followers;
import edu.uoc.android.restservice.rest.model.Owner;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GitHubService {

//    @GET("/authorizations/1")

//@GET("user")
//Call<Owner> getUser(@Header("Authorization") String authorization);

    @GET(ApiConstants.GITHUB_USER_ENDPOINT)
    Call<Owner> getOwner(@Path("owner") String owner);

    @GET(ApiConstants.GITHUB_AUTHORIZE_ENDPOINT)
    Call<Autorization> getAutorization(@Path("authorizations") String owner, String owner2);

    @GET(ApiConstants.GITHUB_EMAIL_ENDPOINT)
    Call<Email> getEmail(@Path("emails") String owner);

//    @GET("api/Profiles/GetProfile?id={id}")
//    Call<Autorization> getUser(@Path("id") String id, @Header("Authorization") String authHeader);

    @GET(ApiConstants.GITHUB_FOLLOWERS_ENDPOINT)
    Call<List<Followers>> getFollowers(@Path("owner") String owner);
}
