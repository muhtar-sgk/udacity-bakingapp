package org.saungit.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.saungit.bakingapp.R;
import org.saungit.bakingapp.model.Ingredient;

import java.util.ArrayList;


public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ArticleViewHolder> {

    final private ArrayList<Ingredient> ingredients;

    public IngredientsAdapter(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredient_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ArticleViewHolder viewHolder = new ArticleViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView ingredient;
        TextView quantity;
        TextView measure;


        public ArticleViewHolder(View itemView) {
            super(itemView);
            ingredient = (TextView) itemView.findViewById(R.id.ingredient);
            measure = (TextView) itemView.findViewById(R.id.measure);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
        }

        void onBind(int position) {
            if (!ingredients.isEmpty()) {
                ingredient.setText(ingredients.get(position).getIngredient());
                measure.setText(ingredients.get(position).getMeasure());
                quantity.setText(ingredients.get(position).getQuantity()+"");
            }
        }
    }
}
