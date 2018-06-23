package com.udacity.giannis.bakingapp.bakindapp.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.check.IsTablet;
import com.udacity.giannis.bakingapp.bakindapp.model.Steps;

import java.util.ArrayList;

public class StepByStepActivity extends AppCompatActivity{
   private ArrayList<Steps> steps = new ArrayList<>();
   private int adapterPostition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle intent = getIntent().getBundleExtra("BUNDLE");
        steps=intent.getParcelableArrayList("STEPS_DETAILS");
        adapterPostition = intent.getInt( "POSITION" );

        if (!IsTablet.tablet( this )){
            android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setStepsList(steps);
            fragmentManager1.beginTransaction().replace(R.id.steps_container,stepsFragment).commit();
        }


        FragmentManager fragmentManager = getSupportFragmentManager();
        StepByStepFragment stepByStepFragment = new StepByStepFragment();
        stepByStepFragment.setSteps(steps);
        stepByStepFragment.setPosition( adapterPostition );
        fragmentManager.beginTransaction().replace(R.id.steps12_container,stepByStepFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
