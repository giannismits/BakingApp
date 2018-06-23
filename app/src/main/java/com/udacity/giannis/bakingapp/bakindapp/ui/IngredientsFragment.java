package com.udacity.giannis.bakingapp.bakindapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.adapters.IngredientsList;
import com.udacity.giannis.bakingapp.bakindapp.model.Ingredients;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {

    public IngredientsFragment() {
    }

    private ArrayList<Ingredients> ingredients = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.ingredients_list_fragment,container,false);

        RecyclerView ingredientsRecyclerView = (RecyclerView) rootview.findViewById(R.id.ingredients_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ingredientsRecyclerView.setHasFixedSize(true);
        ingredientsRecyclerView.setLayoutManager(linearLayoutManager);
        IngredientsList ingredientsList = new IngredientsList(getContext(),getIngredients());
        ingredientsRecyclerView.setAdapter(ingredientsList);

        return rootview;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
