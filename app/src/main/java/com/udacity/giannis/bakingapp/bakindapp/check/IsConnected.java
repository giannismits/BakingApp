package com.udacity.giannis.bakingapp.bakindapp.check;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class IsConnected {
public static boolean isNetworkOn(Context context){
    ConnectivityManager checkConnectivity = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
    NetworkInfo info = checkConnectivity.getActiveNetworkInfo();
    return info !=null && info.isConnectedOrConnecting();
}

}
