package com.udacity.giannis.bakingapp.bakindapp.ui;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.udacity.giannis.bakingapp.bakindapp.NetworkUtils.Network;
import com.udacity.giannis.bakingapp.bakindapp.NetworkUtils.onResponseListener;
import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.adapters.RecipiesList;
import com.udacity.giannis.bakingapp.bakindapp.check.IsConnected;
import com.udacity.giannis.bakingapp.bakindapp.db.RecipiesContract;
import com.udacity.giannis.bakingapp.bakindapp.model.Recipes;

import java.util.List;

import retrofit.Response;

public class BakingMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_main);

        if (IsConnected.isNetworkOn( this )){
            Network.getRecipies(new onResponseListener() {
                @Override
                public void onSucces(Response<List<Recipes>> response) {



                    RecyclerView mRecycleView  = (RecyclerView) findViewById(R.id.main_recycle_view);


                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecycleView.setLayoutManager(linearLayoutManager);
                    mRecycleView.setHasFixedSize(true);

                    RecipiesList recipiesListAdapter = new RecipiesList(getApplicationContext(),response.body());
                    mRecycleView.setAdapter(recipiesListAdapter);
                    addToDatabase(response.body());


                }
                @Override
                public void onFailure(String msg) {
                    Log.v(getLocalClassName(),msg);
                }
            });
        }else{
            setContentView( R.layout.no_internet_connection );
        }


    }
    private void addToDatabase(List<Recipes> recipes){
        Uri uri = RecipiesContract.RecipiesEntry.CONTENT_URI;
        ContentResolver cv = getContentResolver();
        ContentValues contentValues = new ContentValues(  );
        contentValues.clear();
        for (int i=0;i<=recipes.size()-1;i++){
            contentValues.put( RecipiesContract.RecipiesEntry.COLUMN_ID, recipes.get( i ).getId());
            contentValues.put( RecipiesContract.RecipiesEntry.COLUMN_Name,recipes.get( i ).getName() );
            contentValues.put( RecipiesContract.RecipiesEntry.COLUMN_Servings,recipes.get( i ).getServings() );
            cv.insert( uri,contentValues );
        }
    }


}
