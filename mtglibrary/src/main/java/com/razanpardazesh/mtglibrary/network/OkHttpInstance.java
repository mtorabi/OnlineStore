package com.razanpardazesh.mtglibrary.network;

import okhttp3.OkHttpClient;

/**
 * Created by Torabi on 10/8/2016.
 */
public class OkHttpInstance {
    private static OkHttpClient ourInstance;

    public static OkHttpClient getInstance() {

        if (ourInstance == null)
            ourInstance = new OkHttpClient();

        return ourInstance;
    }

    private OkHttpInstance() {
    }
}
