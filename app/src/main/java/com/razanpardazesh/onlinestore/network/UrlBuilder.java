package com.razanpardazesh.onlinestore.network;

import android.content.Context;

import com.razanpardazesh.onlinestore.BuildConfig;

/**
 * Created by Torabi on 10/8/2016.
 */

public class UrlBuilder {
    Context context;

    public UrlBuilder(Context context) {
        this.context = context;
    }

    public String getSubGroupsUrl(long groupId, long lastIndex, int count, String key) {
        return BuildConfig.HOST + "ProductsGroups/subgroups?groupId=" + groupId + "&lastIndex=" + lastIndex + "&count=" + count;
    }

    public static UrlBuilder builder(Context context)
    {
        UrlBuilder intance = new UrlBuilder(context);
        return intance;
    }
}
