package com.udacity.giannis.bakingapp.bakindapp.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

public class RecipiesProvider extends ContentProvider {

    private RecipiesDbHelper recipiesDbHelper;
    private Context mContext;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    static final int RECIPIES=100;
    static final int INGREDIENTS = 200;

    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher= new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RecipiesContract.CONTENT_AUTHORITY;

        matcher.addURI(authority,RecipiesContract.PATH_RECIPIES,RECIPIES);
        matcher.addURI(authority,RecipiesContract.PATH_INGREDIENTS,INGREDIENTS);
        return matcher;
    }
    @Override
    public boolean onCreate() {
        recipiesDbHelper = new RecipiesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase sqLiteDatabase = recipiesDbHelper.getReadableDatabase();
        Cursor mCursor;
        try {
            switch (sUriMatcher.match(uri)){
                case (RECIPIES): {
                    mCursor= sqLiteDatabase.query(
                            RecipiesContract.RecipiesEntry.TABLE_NAME,
                            strings,
                            s,
                            strings1,
                            null,
                            null,
                            s1
                    );
                    return mCursor;
                }

                default: throw new UnsupportedOperationException("unknown Uri" + uri);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            recipiesDbHelper.close();
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase sqLiteDatabase = recipiesDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        Uri uriToReturn;

        try {
            switch (match){
                case   RECIPIES:{
                    long id =sqLiteDatabase.insert( RecipiesContract.RecipiesEntry.TABLE_NAME,null,contentValues);
                    if (id>0){
                        uriToReturn = RecipiesContract.RecipiesEntry.buildUri(id);
                    }else {
                        throw new android.database.SQLException("Error while inserting a row in : " +uri);
                    }break;

                }case INGREDIENTS:{
                    long id =sqLiteDatabase.insert( RecipiesContract.RecipiesEntry.TABLE_NAME_INGREDIENTS,null,contentValues);
                    if (id>0){
                        uriToReturn = RecipiesContract.RecipiesEntry.buildUri(id);
                    }else {
                        throw new android.database.SQLException("Error while inserting a row in : " +uri);
                    }break;
                }
                default: throw  new UnsupportedOperationException("unknown uri" + uri);
            }mContext.getContentResolver().notifyChange(uri,null);
            return uriToReturn;
        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.close();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        final int matxh=sUriMatcher.match(uri);
        int moviesDeleted;

        try (SQLiteDatabase sqLiteDatabase = recipiesDbHelper.getWritableDatabase()) {
            switch (matxh) {
                case RECIPIES: {
                    moviesDeleted = sqLiteDatabase.delete( RecipiesContract.RecipiesEntry.TABLE_NAME, s, strings);
                    break;
                }
                default:
                    throw new UnsupportedOperationException("unknown uri" + uri);
            }
            if (moviesDeleted != 0) {
                mContext.getContentResolver().notifyChange(uri, null);
            }
            return moviesDeleted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}

