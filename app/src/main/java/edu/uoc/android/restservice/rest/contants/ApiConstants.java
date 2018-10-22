package edu.uoc.android.restservice.rest.contants;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class ApiConstants {

    // BASE URL
    public static final String BASE_GITHUB_URL = "https://api.github.com";

    // ENDPOINTS
    public static final String GITHUB_USER_ENDPOINT = "users/{owner}";
    public static final String GITHUB_FOLLOWERS_ENDPOINT = "users/{owner}/followers";
    public static final String GITHUB_AUTHORIZE_ENDPOINT = "authorizations";
    public static final String GITHUB_EMAIL_ENDPOINT = "users/emails";

//    public static final String GITHUB_FOLLOWERS_ENDPOINT = "users/{owner}/followers";
//    public static final String GITHUB_FOLLOWERS_ENDPOINT = "users/{owner}/followers";

    public static final String EMAIL = "email";
    public static final String PASS = "password";


    // DEBUG
    public static final boolean isDebugging = true;
}
