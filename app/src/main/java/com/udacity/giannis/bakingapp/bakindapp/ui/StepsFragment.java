package com.udacity.giannis.bakingapp.bakindapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.adapters.StepsList;
import com.udacity.giannis.bakingapp.bakindapp.model.Steps;

import java.util.ArrayList;

public class StepsFragment extends Fragment {

    private  RecyclerView recyclerViewSteps;
    private ArrayList<Steps> stepsList =  new ArrayList<>();

    public StepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.steps_list_fragment,container,false);

        recyclerViewSteps = (RecyclerView) view.findViewById( R.id.steps_recycler_view );
        populateFragment();

        return view;
    }

    public ArrayList<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(ArrayList<Steps> stepsList) {
        this.stepsList = stepsList;
    }



   public void populateFragment(){
       final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
       recyclerViewSteps.setLayoutManager(linearLayoutManager);
       recyclerViewSteps.setHasFixedSize(true);
       StepsList stepsList = new StepsList(getContext(),getStepsList());
       recyclerViewSteps.setAdapter(stepsList);


   }

}
