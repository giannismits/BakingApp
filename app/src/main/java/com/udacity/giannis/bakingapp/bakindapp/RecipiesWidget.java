package com.udacity.giannis.bakingapp.bakindapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.db.RecipiesContract;
import com.udacity.giannis.bakingapp.bakindapp.db.RecipiesDbHelper;
import com.udacity.giannis.bakingapp.bakindapp.model.Ingredients;
import com.udacity.giannis.bakingapp.bakindapp.ui.BakingMainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link RecipiesWidgetConfigureActivity RecipiesWidgetConfigureActivity}
 */
public class RecipiesWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId , int pos) {
        int position = pos+1;

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews( context.getPackageName(), R.layout.recipies_widget );

        Intent intent = new Intent(context ,WidgetList.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, position);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        views.setRemoteAdapter(R.id.widget_recycle, intent);
        appWidgetManager.updateAppWidget( appWidgetId, views );

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them

    }



    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            RecipiesWidgetConfigureActivity.deleteTitlePref( context, appWidgetId );
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

