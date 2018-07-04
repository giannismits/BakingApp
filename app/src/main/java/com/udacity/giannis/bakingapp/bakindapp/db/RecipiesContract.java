package com.udacity.giannis.bakingapp.bakindapp.db;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import retrofit2.http.PUT;

public final class RecipiesContract {
    public RecipiesContract(){

    }

    public static final String CONTENT_AUTHORITY="com.udacity.giannis.bakingapp.bakindapp";

    public static final Uri BASE_CONTENT_URI= Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPIES = "Recipies";
    public static final String PATH_INGREDIENTS = "Ingredients";

    public static final class RecipiesEntry implements BaseColumns{

        public static final Uri CONTENT_URI=
        BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPIES).build();

        public static final Uri CONTENT_URI_INGREDIENTS =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENTS).build();
        public static final String TABLE_NAME="RECIPIES";
        public static final String TABLE_NAME_INGREDIENTS = "INGREDIENTS";

        public static final String COLUMN_ID = "Id";
        public static final String COLUMN_Name = "recipie_name";
        public static final String COLUMN_Servings = "Servings";
        public static final String COLUMN_INGREDIENT = "ingedient";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_MEASURE = "measure";



        public static Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

    }


}
