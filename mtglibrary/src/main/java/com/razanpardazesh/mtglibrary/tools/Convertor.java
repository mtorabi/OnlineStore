package com.razanpardazesh.mtglibrary.tools;

import android.content.Context;
import android.util.DisplayMetrics;

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

}
