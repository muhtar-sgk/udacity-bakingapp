package org.saungit.bakingapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import org.saungit.bakingapp.R;
import org.saungit.bakingapp.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    public static boolean isMultiPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            if (findViewById(R.id.bakingGrid) != null) {
                isMultiPane = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                MainFragment mainFragment = new MainFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.bakingGrid, mainFragment)
                        .commit();
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                MainFragment mainFragment = new MainFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.bakingframe, mainFragment)
                        .commit();
            }
        }
    }
}
