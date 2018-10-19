package edu.uoc.android.restservice.rest.model;

import com.google.gson.annotations.SerializedName;

public class Email {

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("verified")
    private boolean verified;

    @SerializedName("primary")
    private boolean login;

    @SerializedName("user_visibility")
    private String user_visibility;


    public String getUser_email() {
        return user_email;
    }

    public boolean isVerified() {
        return verified;
    }

    public boolean isLogin() {
        return login;
    }

    public String getUser_visibility() {
        return user_visibility;
    }
}
