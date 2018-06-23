package com.udacity.giannis.bakingapp.bakindapp.NetworkUtils;

import com.udacity.giannis.bakingapp.bakindapp.model.Recipes;

import java.util.List;

import retrofit.Response;

/**
 * Created by giann on 5/26/2018.
 */

public interface onResponseListener {

    void onSucces(Response<List<Recipes>> response);
    void onFailure(String msg);
}
