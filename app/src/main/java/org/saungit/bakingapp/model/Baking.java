package org.saungit.bakingapp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Baking {

    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;
    private String servings;
    private String image;

    public Baking(JSONObject jsonObjectBaking) {
        try {
            this.name = jsonObjectBaking.getString("name");
            this.ingredients = new ArrayList<>();
            JSONArray ingredientsJA = jsonObjectBaking.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsJA.length(); i++) {
                ingredients.add(new Ingredient(ingredientsJA.getJSONObject(i)));
            }
            this.steps = new ArrayList<>();
            JSONArray stepsJA = jsonObjectBaking.getJSONArray("steps");
            for (int i = 0; i < stepsJA.length(); i++) {
                steps.add(new Step(stepsJA.getJSONObject(i)));
            }
            this.servings = jsonObjectBaking.getString("servings");
            this.image = jsonObjectBaking.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Baking(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getServings() {
        return servings;
    }
}




