package com.razanpardazesh.mtglibrary.tools;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Torabi on 9/10/2016.
 */

public class FontApplier {

    private final static String mainFontAddress = "fonts/IRANSansMobile.ttf";
    private final String titleFontAddress = "fonts/IRANSansMobile_Bold.ttf";

    private static Typeface mainFont = null;
    private static Typeface titleFont = null;


    public static Typeface getMainFont(Context context) {

        if (mainFont == null)
        {
            mainFont = Typeface.createFromAsset(context.getAssets(),mainFontAddress);
        }

        return mainFont;
    }

    public static Typeface getTitleFont(Context context) {
        if (titleFont == null)
        {
            titleFont = Typeface.createFromAsset(context.getAssets(),mainFontAddress);
        }
        return titleFont;
    }

    public static void applyMainFont(View view)
    {
        if (view == null || view.getContext() == null)
            return;

        if (view instanceof TextView)
        {
            ((TextView)view).setTypeface(getMainFont(view.getContext()));
            return;
        }

        if (view instanceof ViewGroup)
        {
            for (int i =0; i< ((ViewGroup)view).getChildCount();i++)
            {
                View child = ((ViewGroup)view).getChildAt(i);
                applyMainFont(child);
            }
        }
    }

}
