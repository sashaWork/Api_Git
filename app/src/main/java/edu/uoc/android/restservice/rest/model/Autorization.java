package edu.uoc.android.restservice.rest.model;

import com.google.gson.annotations.SerializedName;

public class Autorization {

    @SerializedName("id")
    private Integer id;

    @SerializedName("url")
    private String url;

    @SerializedName("token")
    private String token;

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }
}
