package com.udacity.giannis.bakingapp.bakindapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.RemoteViews;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.db.RecipiesContract;
import com.udacity.giannis.bakingapp.bakindapp.db.RecipiesDbHelper;
import com.udacity.giannis.bakingapp.bakindapp.ui.BakingMainActivity;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link RecipiesWidgetConfigureActivity RecipiesWidgetConfigureActivity}
 */
public class RecipiesWidget extends AppWidgetProvider {

    public static String GO_TO_THE_ACTIVITY= "gototheactivity";
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
//        RecipiesDbHelper recipiesDbHelper = new RecipiesDbHelper( context );
//
//        String title = null;
//        Cursor cursor;
//        cursor=recipiesDbHelper.getReadableDatabase().rawQuery( "SELECT * FROM RECIPIES WHERE Id="+2,null );
//
//        if (cursor.moveToFirst()) {
//            do {
//                title = cursor.getString(cursor.getColumnIndex( RecipiesContract.RecipiesEntry.COLUMN_Name));
//            } while (cursor.moveToNext());
//        }
//        CharSequence widgetText = RecipiesWidgetConfigureActivity.loadTitlePref( context, appWidgetId );
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews( context.getPackageName(), R.layout.recipies_widget );
        views.setTextViewText( R.id.appwidget_text, "Make  sweet today!!!" );


        Intent intent = new Intent(context ,BakingMainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);



        views.setOnClickPendingIntent(R.id.recipe_name_widget,pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget( appWidgetId, views );




    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

        RemoteViews views = new RemoteViews( context.getPackageName(), R.xml.recipies_widget_info );

        // Instruct the widget manager to update the widget


        Intent intent = new Intent(context ,BakingMainActivity.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);



        views.setOnClickPendingIntent(R.id.recipe_name_widget,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, views);

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

