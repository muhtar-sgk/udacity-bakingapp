package org.saungit.bakingapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Step {

    private int id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;
    private ArrayList<Step> stepsArrayList;


    public Step(JSONObject jsonObjectSteps) {
        try {
            this.id = jsonObjectSteps.getInt("id");
            this.shortDescription = jsonObjectSteps.optString("shortDescription");
            this.description = jsonObjectSteps.optString("description");
            this.videoURL = jsonObjectSteps.optString("videoURL");
            this.thumbnailURL = jsonObjectSteps.getString("thumbnailURL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public ArrayList<Step> getStepsArrayList() {
        return stepsArrayList;
    }

    public void setStepsArrayList(ArrayList<Step> stepsArrayList) {
        this.stepsArrayList = stepsArrayList;
    }
}




