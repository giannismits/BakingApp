package com.udacity.giannis.bakingapp.bakindapp.adapters;


import android.content.Context;
;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.giannis.bakingapp.bakindapp.R;


import com.udacity.giannis.bakingapp.bakindapp.model.Steps;
import com.udacity.giannis.bakingapp.bakindapp.ui.ActivityDetails;

import java.util.ArrayList;


public class StepsList extends RecyclerView.Adapter<StepsList.StepsViewHolder>{
    private Context context;
    private ArrayList<Steps>  steps;

    public StepsList(Context context, ArrayList<Steps> steps) {
        this.context = context;
        this.steps = steps;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {
        private  TextView mSteps ;
        private  CardView cv;
        StepsViewHolder(View itemView) {
            super(itemView);
            mSteps = (TextView) itemView.findViewById(R.id.steps_list_text);
            cv = (CardView) itemView.findViewById(R.id.card_view_steps);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = getAdapterPosition();
                if (context instanceof ActivityDetails) {
                    ((ActivityDetails) context).stepsFrag(position);
                }

            }
        });

}
        }


 @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list,parent,false);
        return new StepsList.StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        String shDe = steps.get(position).getDescription();
        holder.mSteps.setText(shDe);
    }

    @Override
    public int getItemCount() {
        if (steps.size()==0){
            return 0;
        }else{
            return steps.size();
        }
    }
}
