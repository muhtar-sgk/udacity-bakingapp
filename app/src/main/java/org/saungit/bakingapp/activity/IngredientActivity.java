package org.saungit.bakingapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.saungit.bakingapp.R;
import org.saungit.bakingapp.adapter.IngredientsAdapter;
import org.saungit.bakingapp.fragment.MainFragment;
import org.saungit.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class IngredientActivity extends AppCompatActivity {
    private RecyclerView ingredientRecyclerView;
    private IngredientsAdapter ingredientsAdapter;
    public static ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        ingredientRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewIngredient);
        ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        index = getIntent().getExtras().getInt("data");
        ingredientArrayList = MainFragment.ingredientArrayList.get(index).getIngredientArrayList();
        ingredientsAdapter = new IngredientsAdapter(ingredientArrayList);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
    }
}
