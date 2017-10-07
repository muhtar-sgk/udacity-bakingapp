package org.saungit.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.saungit.bakingapp.R;
import org.saungit.bakingapp.activity.StepsActivity;
import org.saungit.bakingapp.listener.ItemClickListener;
import org.saungit.bakingapp.model.Baking;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Muhtar on 23/05/2017.
 */

public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.ViewHolder> {
    private List<Baking> bakingList;
    private Context context;

    public BakingAdapter(Context context, List<Baking> bakingList) {
        this.context = context;
        this.bakingList = bakingList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_baking, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Baking baking = bakingList.get(position);
        holder.name.setText(baking.getName());
        holder.servings.setText(baking.getServings());
        Glide.with(context).load(baking.getImage())
                .thumbnail(0.5f)
                .crossFade()
                .error(R.drawable.chef)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return bakingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.textNameBaking) TextView name;
        @BindView(R.id.textServings) TextView servings;
        @BindView(R.id.image) ImageView imageView;

        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, StepsActivity.class);
            intent.putExtra("data",position);
            context.startActivity(intent);
        }
    }
}
