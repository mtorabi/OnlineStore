package com.razanpardazesh.onlinestore.Tools;

import android.content.Context;
import android.content.SharedPreferences;

import com.razanpardazesh.onlinestore.BuildConfig;

/**
 * Created by Torabi on 9/11/2016.
 */

public class SessionManagement {

    private final int listCount = 20;
    private final String KEY_SHOWED_WELCOME = "SHOWED_WELCOME";
    private final String KEY_PREFERENES_NAME = "OnlineStore_";


    private SharedPreferences pref;

    private static SessionManagement instance = null;

    public SessionManagement(Context context) {
        this.pref = context.getSharedPreferences(KEY_PREFERENES_NAME + BuildConfig.FLAVOR, Context.MODE_PRIVATE);
    }

    public static SessionManagement getInstance(Context context) {
        if (instance == null)
            instance = new SessionManagement(context);

        return instance;
    }

    public Boolean getFakeBind() {
        return BuildConfig.HOST != null && BuildConfig.HOST.compareTo("fake") == 0;
    }

    public int getListCount() {
        return listCount;
    }

    public void setWelcomeShowed(Boolean showed) {
        if (this.pref == null)
            return;

        this.pref.edit().putBoolean(KEY_SHOWED_WELCOME,showed).commit();
    }

    public Boolean getWelcomeShowed() {
        if (this.pref == null)
            return false;

        return this.pref.getBoolean(KEY_SHOWED_WELCOME,false);
    }
}
