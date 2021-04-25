package com.example.hotelbooking.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class Utils {
    public static final String IS_ADMIN = "isAdmin";
    public static final String PHONE_NUMBER = "phone";
    public static final String BIRTHDAY = "birthday";
    public static final String TAG = "TAG";
    public static final String DEFAULT = "default";
    public static final String AVATAR = "avatar";
    public static final String USERS = "users";
    public static final String UPLOADS = "uploads";
    public static final String USER_NAME = "username";
    public static final String RENAME_DIALOG_TYPE = "rename type";
    public static final String PASSWORD_DIALOG_TYPE = "password type";
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return false;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Network network = connectivityManager.getActiveNetwork();
                if (network == null) {
                    return false;
                } else {
                    NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
                    return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
                }
            } else {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        }
    }

}
