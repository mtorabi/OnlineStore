package com.razanpardazesh.onlinestore.Tools;

import android.content.Context;

import com.razanpardazesh.onlinestore.BuildConfig;

/**
 * Created by Torabi on 9/11/2016.
 */

public class SessionManagement {

    private final int listCount = 20;


    private static SessionManagement instance = null;

    public static SessionManagement getInstance(Context context) {
        if (instance == null)
            instance = new SessionManagement();

        return instance;
    }

    public Boolean getFakeBind() {
        return BuildConfig.HOST != null && BuildConfig.HOST.compareTo("fake") == 0;
    }

    public int getListCount() {
        return listCount;
    }
}
