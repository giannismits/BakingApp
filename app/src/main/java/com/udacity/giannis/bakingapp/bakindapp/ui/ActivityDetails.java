package com.udacity.giannis.bakingapp.bakindapp.ui;



import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;


import com.udacity.giannis.bakingapp.bakindapp.R;

import com.udacity.giannis.bakingapp.bakindapp.adapters.StepsList;
import com.udacity.giannis.bakingapp.bakindapp.model.Ingredients;
import com.udacity.giannis.bakingapp.bakindapp.model.Recipes;
import com.udacity.giannis.bakingapp.bakindapp.model.Steps;
import com.udacity.giannis.bakingapp.bakindapp.check.IsTablet;


import java.util.ArrayList;


public class ActivityDetails extends AppCompatActivity{
    private ArrayList<Ingredients> ingredients = new ArrayList<>();
    private ArrayList<Steps> steps= new ArrayList<>();
    private int position;
    private FrameLayout mIngridients,mSteps,mStepsDetails;
    private ImageButton mPrev,mNext;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mIngridients = (FrameLayout) findViewById(R.id.ingredients_container);
        mSteps = (FrameLayout) findViewById(R.id.steps_container);

        mPrev = (ImageButton) findViewById(R.id.prev);
        mNext = (ImageButton) findViewById(R.id.next);


        Bundle intent = getIntent().getBundleExtra("BUNDLE");
    String name = intent.getString("RECIPIE_NAME");
    ingredients =  intent.getParcelableArrayList("INGREDIENTS");
    steps = intent.getParcelableArrayList("STEPS");
    position = intent.getInt("POSITION");
    setTitle(name);

    if ( IsTablet.tablet( this ) ){
    //ingredients List
    if (ingredients!=null && position==0){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setIngredients(ingredients);
        fragmentManager.beginTransaction().replace(R.id.ingredients_container,ingredientsFragment).commit();
    }
    //steps List
    if(steps!=null && position==0){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setStepsList(steps);
        fragmentManager.beginTransaction().replace(R.id.steps_container,stepsFragment).commit();
    }

}else {

    if (ingredients!=null){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setIngredients(ingredients);
        fragmentManager.beginTransaction().replace(R.id.ingredients_container,ingredientsFragment).commit();
    }
//    steps List
    if(steps!=null){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        StepsFragment stepsFragment = new StepsFragment();
        stepsFragment.setStepsList(steps);
        fragmentManager.beginTransaction().replace(R.id.steps_container,stepsFragment).commit();



    }
    if(position!=-1){
        android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
        StepByStepFragment stepByStepFragment = new StepByStepFragment();
        stepByStepFragment.setSteps(steps);
        stepByStepFragment.setPosition( position );
        fragmentManager1.beginTransaction().replace(R.id.steps12_container,stepByStepFragment).commit();
    }
}
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
      outState.putParcelableArrayList( "ingredients", ingredients );
        outState.putParcelableArrayList( "steps", steps );
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ingredients = savedInstanceState.getParcelableArrayList( "ingredients" );
        steps = savedInstanceState.getParcelableArrayList( "steps" );
    }




    public void stepsFrag(int pos){
        if (IsTablet.tablet( this )){
            Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("STEPS_DETAILS",(ArrayList<?extends Parcelable>) steps);
                    bundle.putInt( "POSITION",pos);

                    Intent stepBystep=new Intent(this,StepByStepActivity.class);
                    stepBystep.putExtra("BUNDLE",bundle);
                    stepBystep.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(stepBystep);
        }else {
            android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
            StepByStepFragment stepByStepFragment = new StepByStepFragment();
            stepByStepFragment.setSteps(steps);
            stepByStepFragment.setPosition( pos );
            fragmentManager1.beginTransaction().replace(R.id.steps12_container,stepByStepFragment).commit();
        }

    }


}
