package com.razanpardazesh.onlinestore;

import android.content.Context;
import android.content.Intent;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

/**
 * Created by Home on 10/16/2016.
 */

public class OnlineStoreWelcomeActivity extends WelcomeActivity {
    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.WelcomeScreenTheme_Light)
                .defaultBackgroundColor(R.color.colorAccentBack)
                .titlePage(R.drawable.c_welcome_1, getString(R.string.welcome_page1_title) ,R.color.welcome_color_1)
                .basicPage(R.drawable.c_welcome_2, getString(R.string.welcome_page2_title), getString(R.string.welcome_page2_description), R.color.welcome_color_2)
                .basicPage(R.drawable.c_welcome_3, getString(R.string.welcome_page3_title), getString(R.string.welcome_page3_description),R.color.welcome_color_3)
                .swipeToDismiss(true).theme(R.style.OnlineStoreWelcomeScreenTheme)
                .build();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, OnlineStoreWelcomeActivity.class);
        context.startActivity(starter);
    }
}
