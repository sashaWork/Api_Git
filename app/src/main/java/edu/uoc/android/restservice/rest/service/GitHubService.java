package edu.uoc.android.restservice.rest.service;

import edu.uoc.android.restservice.rest.contants.ApiConstants;
import edu.uoc.android.restservice.rest.model.Followers;
import edu.uoc.android.restservice.rest.model.Owner;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET(ApiConstants.GITHUB_USER_ENDPOINT)
    Call<Owner> getOwner(@Path("owner") String owner);

    @GET(ApiConstants.GITHUB_FOLLOWERS_ENDPOINT)
    Call<List<Followers>> getFollowers(@Path("owner") String owner);
}
