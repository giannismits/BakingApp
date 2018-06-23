package com.udacity.giannis.bakingapp.bakindapp.NetworkUtils;

import com.udacity.giannis.bakingapp.bakindapp.model.Recipes;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by giann on 5/25/2018.
 */

public interface RetroInter {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipes>> getRecipies();
}
