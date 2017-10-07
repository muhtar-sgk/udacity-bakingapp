package org.saungit.bakingapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.saungit.bakingapp.R;
import org.saungit.bakingapp.activity.IngredientActivity;
import org.saungit.bakingapp.activity.MainActivity;
import org.saungit.bakingapp.activity.StepsDetailActivity;
import org.saungit.bakingapp.adapter.StepsAdapter;
import org.saungit.bakingapp.model.Step;

import java.util.ArrayList;

public class StepsFragment extends Fragment implements StepsAdapter.ListItemClickListener {

    private RecyclerView stepsRecyclerView;
    private TextView textIngredient;

    private StepsAdapter stepsAdapter;

    private View rootView;
    private int index = 0;
    public static ArrayList<Step> steps = new ArrayList<>();

    public StepsFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_steps, container, false);
        stepsRecyclerView = (RecyclerView) rootView.findViewById(R.id.stepslist);
        textIngredient = (TextView) rootView.findViewById(R.id.textIngredient);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        index = getActivity().getIntent().getExtras().getInt("data");
        steps = MainFragment.stepArrayList.get(index).getStepsArrayList();
        stepsAdapter = new StepsAdapter(this, steps);
        stepsRecyclerView.setAdapter(stepsAdapter);

        textIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IngredientActivity.class);
                intent.putExtra("data", index);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (!MainActivity.isMultiPane) {
            Intent intent = new Intent(getActivity(), StepsDetailActivity.class);
            intent.putExtra("data", clickedItemIndex);
            startActivity(intent);
        } else {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            StepsDetailsFragment stepsDetailsFragment = new StepsDetailsFragment();
            stepsDetailsFragment.index = clickedItemIndex;
            fragmentManager.beginTransaction()
                    .replace(R.id.stepsdetailsframe, stepsDetailsFragment)
                    .commit();
        }
    }
}

