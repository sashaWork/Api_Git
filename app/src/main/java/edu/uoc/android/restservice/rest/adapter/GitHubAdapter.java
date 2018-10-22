package edu.uoc.android.restservice.rest.adapter;

import edu.uoc.android.restservice.rest.contants.ApiConstants;
import edu.uoc.android.restservice.rest.model.Autorization;
import edu.uoc.android.restservice.rest.model.Email;
import edu.uoc.android.restservice.rest.model.Followers;
import edu.uoc.android.restservice.rest.model.GithubIssue;
import edu.uoc.android.restservice.rest.model.GithubRepo;
import edu.uoc.android.restservice.rest.model.Owner;
import edu.uoc.android.restservice.rest.service.GitHubService;
import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class GitHubAdapter extends BaseAdapter implements GitHubService {

    private GitHubService gitHubService;

    public GitHubAdapter() {
        super(ApiConstants.BASE_GITHUB_URL);
        gitHubService = createService(GitHubService.class);
    }

    @Override
    public Call<Owner> getOwner(String owner) {
        return gitHubService.getOwner(owner);
    }

    @Override
    public Call<Email> getEmail() {
        return gitHubService.getEmail();
    }

    @Override
    public Call<Autorization> getAutorization(String owner, String pass) {
        return gitHubService.getAutorization(owner, pass);
    }

    @Override
    public Call<List<Followers>> getFollowers(String owner) {
        return gitHubService.getFollowers(owner);
    }

    @Override
    public Single<List<GithubRepo>> getRepos() {
        return null;
    }

    @Override
    public Single<List<GithubIssue>> getIssues(String owner, String repository) {
        return null;
    }

    @Override
    public Single<ResponseBody> postComment(String url, GithubIssue issue) {
        return null;
    }
}
