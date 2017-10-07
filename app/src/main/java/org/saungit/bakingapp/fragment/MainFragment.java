package org.saungit.bakingapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.saungit.bakingapp.R;
import org.saungit.bakingapp.activity.MainActivity;
import org.saungit.bakingapp.adapter.BakingAdapter;
import org.saungit.bakingapp.model.Baking;
import org.saungit.bakingapp.model.Ingredient;
import org.saungit.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */

public class MainFragment extends Fragment {
    RecyclerView recyclerViewBaking;

    RecyclerView.LayoutManager layoutManager;

    public static ArrayList<Baking> bakingList = new ArrayList<>();
    public static ArrayList<Step> stepArrayList = new ArrayList<>();
    public static ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();

    private BakingAdapter bakingAdapter;

    private ProgressDialog progressDialog;

    private String name, servings, image;


    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(getActivity(), view);

        recyclerViewBaking = (RecyclerView) view.findViewById(R.id.recyclerViewBaking);

        bakingAdapter = new BakingAdapter(getActivity(), bakingList);
        if (MainActivity.isMultiPane){
            layoutManager = new GridLayoutManager(getActivity(),2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }

        recyclerViewBaking.setLayoutManager(layoutManager);
        recyclerViewBaking.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBaking.setAdapter(bakingAdapter);

        getActivity().setTitle("Baking Time");

        fetchDataCooking();

        return view;
    }

    public void fetchDataCooking() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        StringRequest request =
                new StringRequest(Request.Method.GET,
                        "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                showJSON(response);
                                progressDialog.dismiss();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                if (volleyError instanceof NetworkError) {
                                    Toast.makeText(getActivity(), "check your internet connection", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                } else if (volleyError instanceof ServerError) {
                                    Toast.makeText(getActivity(), "server not found", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                } else if (volleyError instanceof AuthFailureError) {
                                    Toast.makeText(getActivity(), "check your internet connection", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                } else if (volleyError instanceof ParseError) {
                                    Toast.makeText(getActivity(), "fetching data failed", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                } else if (volleyError instanceof NoConnectionError) {
                                    Toast.makeText(getActivity(), "check your internet connection", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                } else if (volleyError instanceof TimeoutError) {
                                    Toast.makeText(getActivity(), "timeout connection", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    private void showJSON(String response) {
        try {
            JSONArray jsonArrayBaking = new JSONArray(response);
            for (int i = 0; i < jsonArrayBaking.length(); i++){
                JSONObject jsonObjectBaking = jsonArrayBaking.getJSONObject(i);

                Baking baking = new Baking();

                name = jsonObjectBaking.getString("name");
                servings = jsonObjectBaking.getString("servings");
                image = jsonObjectBaking.getString("image");

                baking.setName(name);
                baking.setServings(servings);
                baking.setImage(image);

                bakingList.add(baking);

                JSONArray jsonArrayIngredients = jsonObjectBaking.getJSONArray("ingredients");
                for (int k = 0; k < jsonArrayIngredients.length(); k++) {
                    Ingredient ingredient = new Ingredient(jsonArrayIngredients.getJSONObject(k));
                    ingredientArrayList.add(ingredient);
                    ingredient.setIngredientArrayList(ingredientArrayList);
                }

                JSONArray jsonArraySteps = jsonObjectBaking.getJSONArray("steps");
                for (int j = 0; j < jsonArraySteps.length(); j++) {
                    Step step = new Step(jsonArraySteps.getJSONObject(j));
                    step.setStepsArrayList(stepArrayList);
                    stepArrayList.add(step);
                }
            }
            bakingAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
