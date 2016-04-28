package com.example.ruby.materiallab.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.ruby.materiallab.R;
import com.example.ruby.materiallab.ui.fragments.StartFragment;

/**
 * Shows steps for transitions between fragments
 */
public class TransitionFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_fragment);

        StartFragment startFragment = new StartFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, startFragment)
                .commit();
    }
}
