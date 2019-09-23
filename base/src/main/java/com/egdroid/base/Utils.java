package com.egdroid.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// A final class is simply a class that can't be extended ... but it can extend another non-final class
public final class Utils {

    public static Boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo activeNetwork  = connectivityManager.getActiveNetworkInfo();
                return activeNetwork  != null && activeNetwork .isConnected();
            } else
                return false;
        } else
            return false;
    }
}
