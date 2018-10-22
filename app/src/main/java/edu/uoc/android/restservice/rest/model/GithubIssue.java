package edu.uoc.android.restservice.rest.model;

import com.google.gson.annotations.SerializedName;

public class GithubIssue {
    String id;
    String title;
    String comments_url;

    @SerializedName("body")
    String comment;

    @Override
    public String toString() {
        return id +  " - " + title;
    }
}
