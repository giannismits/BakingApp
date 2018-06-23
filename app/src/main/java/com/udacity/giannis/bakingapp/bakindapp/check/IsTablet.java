package com.udacity.giannis.bakingapp.bakindapp.check;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Display;
import android.view.WindowManager;

import com.udacity.giannis.bakingapp.bakindapp.ui.BakingMainActivity;

public class IsTablet {

    public IsTablet() {
    }

    public static boolean tablet (Context con){
        boolean small = ((con.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL);
        return (small);
    }
}
