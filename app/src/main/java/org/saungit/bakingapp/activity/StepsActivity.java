package org.saungit.bakingapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import org.saungit.bakingapp.R;
import org.saungit.bakingapp.fragment.StepsDetailsFragment;
import org.saungit.bakingapp.fragment.StepsFragment;

public class StepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (MainActivity.isMultiPane) {
                StepsFragment stepsFragment = new StepsFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.stepsframe, stepsFragment)
                        .commit();

                StepsDetailsFragment stepsDetailsFragment = new StepsDetailsFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.stepsdetailsframe, stepsDetailsFragment)
                        .commit();
            } else {
                StepsFragment stepsFragment = new StepsFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.stepsframe, stepsFragment)
                        .commit();
            }
        }
    }
}