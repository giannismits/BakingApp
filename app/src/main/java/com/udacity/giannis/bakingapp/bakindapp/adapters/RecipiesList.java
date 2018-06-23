package com.udacity.giannis.bakingapp.bakindapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.giannis.bakingapp.bakindapp.ui.ActivityDetails;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.model.Recipes;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by giann on 5/26/2018.
 */

public class RecipiesList extends RecyclerView.Adapter<RecipiesList.RecipiesViewHolder> {

    private Context context;
    private List<Recipes> recipesList;


    public RecipiesList(Context context, List<Recipes> recipesList) {
        this.context = context;
        this.recipesList=recipesList;
    }

    public class RecipiesViewHolder extends RecyclerView.ViewHolder{
        private final TextView mRecipieName,mServings ;
        private final CardView cv;
        private final ImageView mRecipieImage;
        public RecipiesViewHolder(View itemView) {
            super(itemView);
          mRecipieName = (TextView) itemView.findViewById(R.id.recipe_name);
          cv = (CardView) itemView.findViewById(R.id.card_view_recipies);
          mRecipieImage = (ImageView) itemView.findViewById(R.id.sweet_image);
          mServings = (TextView) itemView.findViewById(R.id.servings);

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  int position = getAdapterPosition();
                  Intent details=new Intent(context,ActivityDetails.class);

                  Bundle bundle=new Bundle();
                  bundle.putParcelableArrayList("STEPS",
                          (ArrayList<? extends Parcelable>) recipesList.get(position).getSteps());
                  bundle.putParcelableArrayList("INGREDIENTS",(ArrayList<? extends Parcelable>) recipesList.get(position).getIngredients());
                  bundle.putString("RECIPIE_NAME",recipesList.get(position).getName());
                  details.putExtra("BUNDLE",bundle);
                  details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(details);
              }
          });

        }
    }

    @NonNull
    @Override
    public RecipiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_list,parent,false);
        return new RecipiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipiesViewHolder holder, final int position) {
        String recipeName = recipesList.get(position).getName();
        holder.mRecipieName.setText(recipeName);
        String servings = String.valueOf(recipesList.get(position).getServings());
        holder.mServings.setText( servings);
        switch (recipeName){
            case "Nutella Pie":
                holder.mRecipieImage.setImageResource(R.drawable.nutellapie);
                break;
            case "Brownies":
                holder.mRecipieImage.setImageResource(R.drawable.brownies);
                break;
            case "Yellow Cake":
                holder.mRecipieImage.setImageResource(R.drawable.yellowcake);
                break;
            case "Cheesecake":
                holder.mRecipieImage.setImageResource(R.drawable.cheesecake);
                break;
        }




    }

    @Override
    public int getItemCount() {
        if (recipesList.size()==0){
            return 0;
        }else{
           return recipesList.size();
        }
    }

}
