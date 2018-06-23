package com.udacity.giannis.bakingapp.bakindapp.NetworkUtils;


import com.udacity.giannis.bakingapp.bakindapp.model.Recipes;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by giann on 5/25/2018.
 */

public class Network {

    private static List<Recipes> recipesList;
    static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    public static void getRecipies(final onResponseListener listener){

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetroInter retroInter = retrofit.create(RetroInter.class);

        Call<List<Recipes>> call = retroInter.getRecipies();

        call.enqueue(new Callback<List<Recipes>>() {
            @Override
            public void onResponse(Response<List<Recipes>> response, Retrofit retrofit) {
                listener.onSucces(response);
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }
}
