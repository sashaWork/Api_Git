package edu.uoc.android.restservice.rest.model;

public class GithubRepo {

    public String name;
    public String owner;
    String url;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
//        return(name + " " +  url);
        return(name);
    }
}
