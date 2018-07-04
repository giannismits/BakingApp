package com.udacity.giannis.bakingapp.bakindapp.db;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.udacity.giannis.bakingapp.bakindapp.model.Ingredients;

import java.util.ArrayList;

import static org.xmlpull.v1.XmlPullParser.TEXT;

public class RecipiesDbHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION=1;
    static final String DATABASE_NAME="baking.db";


    public RecipiesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_INGREDIENDS_TABLE=
                "CREATE TABLE " + RecipiesContract.RecipiesEntry.TABLE_NAME_INGREDIENTS + " (" +
                        RecipiesContract.RecipiesEntry.COLUMN_ID + " INTEGER NOT NULL, " +
                        RecipiesContract.RecipiesEntry.COLUMN_MEASURE + " TEXT NOT NULL, " +
                        RecipiesContract.RecipiesEntry.COLUMN_INGREDIENT + " TEXT NOT NULL," +
                        RecipiesContract.RecipiesEntry.COLUMN_QUANTITY + " REAL NOT NULL" +
                        "); ";

        final String SQL_RECIPIES_TABLE =

                "CREATE TABLE " + RecipiesContract.RecipiesEntry.TABLE_NAME + " (" +
                       RecipiesContract.RecipiesEntry.COLUMN_ID + " INTEGER NOT NULL, " +
                        RecipiesContract.RecipiesEntry.COLUMN_Name + " TEXT NOT NULL, " +
                        RecipiesContract.RecipiesEntry.COLUMN_Servings + " TEXT NOT NULL " +
                        "); ";

        sqLiteDatabase.execSQL(SQL_RECIPIES_TABLE);
        sqLiteDatabase.execSQL(SQL_INGREDIENDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipiesContract.RecipiesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade( db, oldVersion, newVersion );
    }



    public Cursor getMatchData(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from Recipies where Id="+id+"",null);

        return res;
    }
    public ArrayList<Ingredients> getIngredients(int widgetId) {
        ArrayList<Ingredients> list = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor;
         cursor = sqLiteDatabase.rawQuery("SELECT * FROM Ingredients WHERE Id="+widgetId, null);
        if (cursor!=null && cursor.moveToFirst()){
            while (cursor.moveToNext()) {
                Ingredients ingredient = new Ingredients(cursor.getDouble(cursor.getColumnIndex(RecipiesContract.RecipiesEntry.COLUMN_QUANTITY)),cursor.getString(cursor.getColumnIndex(RecipiesContract.RecipiesEntry.COLUMN_MEASURE)),
                        cursor.getString(cursor.getColumnIndex(RecipiesContract.RecipiesEntry.COLUMN_INGREDIENT)));
                list.add(ingredient);
            }
        }

        return list;
    }
}
