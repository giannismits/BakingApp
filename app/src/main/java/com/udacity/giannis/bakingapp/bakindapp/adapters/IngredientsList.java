package com.udacity.giannis.bakingapp.bakindapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.model.Ingredients;


import java.util.ArrayList;


public class IngredientsList extends RecyclerView.Adapter<IngredientsList.IngredientsViewHolder> {

private Context context;
private ArrayList<Ingredients> ingredientsList;

public IngredientsList(Context context, ArrayList<Ingredients> ingredientsList) {
        this.context = context;
        this.ingredientsList=ingredientsList;
        }

public class IngredientsViewHolder extends RecyclerView.ViewHolder{
    private final TextView mIngredientsName,mQuantity,mMeasure ;
    private final CardView cv;
    public IngredientsViewHolder(View itemView) {
        super(itemView);
        mIngredientsName = (TextView) itemView.findViewById(R.id.ingradient_name);
        cv = (CardView) itemView.findViewById(R.id.ingradient_card_view);
        mMeasure = (TextView) itemView.findViewById(R.id.measure);
        mQuantity = (TextView) itemView.findViewById(R.id.quantity);

    }
}

    @NonNull
    @Override
    public IngredientsList.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingetients_list,parent,false);
        return new IngredientsList.IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        String ingredient = ingredientsList.get(position).getIngredient();
        holder.mIngredientsName.setText(ingredient);
        String measure = ingredientsList.get(position).getMeasure();
        holder.mMeasure.setText(measure);
        String quantity = String.valueOf(ingredientsList.get(position).getQuantity());
        holder.mQuantity.setText(quantity);
    }


    @Override
    public int getItemCount() {
        if (ingredientsList.size()==0){
            return 0;
        }else{
            return ingredientsList.size();
        }
    }
}
