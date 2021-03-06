package com.razanpardazesh.mtglibrary.tools;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Torabi on 9/8/2016.
 */

public class Convertor {

    public static int toPixcel(float dp, Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;

    }

    public static float toDp(int px, Context context)
    {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;

    }

    public static Date toDate(String dateStr)
    {
        if (TextUtils.isEmpty(dateStr))
            return null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            return dateFormat.parse(dateStr);
        } catch (Throwable e) {
        }

        return null;
    }

}
