package com.udacity.giannis.bakingapp.bakindapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;

import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


import com.udacity.giannis.bakingapp.bakindapp.db.RecipiesDbHelper;
import com.udacity.giannis.bakingapp.bakindapp.model.Ingredients;

import java.util.ArrayList;

public class WidgetList extends RemoteViewsService {


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return (new ListProvider(this.getApplicationContext(), intent));
    }

    class ListProvider implements RemoteViewsFactory  {
        ArrayList<Ingredients> ingredients;
        Context mContext;
        int appWidgetId;
        RecipiesDbHelper recipiesDbHelper = new RecipiesDbHelper( getApplicationContext());

        ListProvider(Context context, Intent intent) {
            this.mContext = context;
            appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 1);
            ingredients = recipiesDbHelper.getIngredients(appWidgetId);
        }




        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            ingredients =  recipiesDbHelper.getIngredients(appWidgetId);


        }


        @Override
        public void onDestroy() {

        }


        @Override
        public int getCount() {
            return ingredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            ingredients=recipiesDbHelper.getIngredients(appWidgetId);
            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item_list);

                    views.setTextViewText(R.id.widget_recipe_name, ingredients.get(position).getIngredient());

                    views.setTextViewText(R.id.widget_recipe_measure,ingredients.get(position).getMeasure());

                    views.setTextViewText(R.id.widget_recipe_quantity,String.valueOf(ingredients.get(position).getQuantity()));

            return views;
        }


        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}