package com.udacity.giannis.bakingapp.bakindapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Spinner;

import com.udacity.giannis.bakingapp.bakindapp.R;
import com.udacity.giannis.bakingapp.bakindapp.ui.BakingMainActivity;

/**
 * The configuration screen for the {@link RecipiesWidget RecipiesWidget} AppWidget.
 */
public class RecipiesWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "com.udacity.giannis.bakingapp.bakindapp.RecipiesWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    Spinner spinner;
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = RecipiesWidgetConfigureActivity.this;
            int pos = spinner.getSelectedItemPosition();

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( context );
            RecipiesWidget.updateAppWidget( context, appWidgetManager, mAppWidgetId,pos );

            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult( RESULT_OK, resultValue );
            finish();
        }
    };

    public RecipiesWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences( PREFS_NAME, 0 ).edit();
        prefs.putString( PREF_PREFIX_KEY + appWidgetId, text );
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences( PREFS_NAME, 0 );
        String titleValue = prefs.getString( PREF_PREFIX_KEY + appWidgetId, null );
        if (titleValue != null) {
            return titleValue;
        } else {
            return context.getString( R.string.appwidget_text );
        }
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences( PREFS_NAME, 0 ).edit();
        prefs.remove( PREF_PREFIX_KEY + appWidgetId );
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        setContentView(R.layout.recipies_widget_configure);

        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);
       spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recipies_name_spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }


    }
}

