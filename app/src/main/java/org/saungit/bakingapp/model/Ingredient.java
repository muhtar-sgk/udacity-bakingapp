package org.saungit.bakingapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ingredient {

    private double quantity;
    private String measure;
    private String ingredient;
    private ArrayList<Ingredient> ingredientArrayList;

    public Ingredient(JSONObject jsonObjectIngredient) {
        try {
            this.quantity = jsonObjectIngredient.getDouble("quantity");
            this.measure = jsonObjectIngredient.optString("measure");
            this.ingredient = jsonObjectIngredient.optString("ingredient");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredientArrayList(ArrayList<Ingredient> ingredientArrayList) {
        this.ingredientArrayList = ingredientArrayList;
    }

    public ArrayList<Ingredient> getIngredientArrayList() {
        return ingredientArrayList;
    }
}




