package edu.uoc.android.restservice.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Email {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("primary")
    @Expose
    private Boolean primary;
    @SerializedName("visibility")
    @Expose
    private String visibility;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
