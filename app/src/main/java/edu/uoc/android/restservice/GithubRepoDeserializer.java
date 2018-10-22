package edu.uoc.android.restservice;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import edu.uoc.android.restservice.rest.model.GithubRepo;

public class GithubRepoDeserializer implements JsonDeserializer<GithubRepo> {

    final String LOG_TAG = "myLogs";

        @Override
        public GithubRepo deserialize(JsonElement json, Type typeOfT,
                                      JsonDeserializationContext context) throws JsonParseException {
            GithubRepo githubRepo = new GithubRepo();

            JsonObject repoJsonObject =  json.getAsJsonObject();
            githubRepo.setName(repoJsonObject.get("name").getAsString());
            githubRepo.setUrl(repoJsonObject.get("url").getAsString());

            JsonElement ownerJsonElement = repoJsonObject.get("owner");
            JsonObject ownerJsonObject = ownerJsonElement.getAsJsonObject();
            githubRepo.setOwner(ownerJsonObject.get("login").getAsString());
            Log.d(LOG_TAG, "GithubRepoDeserializer: " + githubRepo.getName() + " " + githubRepo.getOwner());
            Data.own  = githubRepo.getOwner();

            return githubRepo;
        }
}
