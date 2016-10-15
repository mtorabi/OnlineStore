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
                .titlePage(R.drawable.toolbar_logo, "فروشگاه تیمن شاپ" ,R.color.colorAccent)
                .basicPage(R.drawable.c_banner, "به فروشگاه تیمن خوش آمدید", "رفاه لاهیجان تقدیم می کند", R.color.colorSecondaryTitle)
                .basicPage(R.drawable.c_banner_2, "بهترین با کیفیت ترین محصولات را می توانید از این فروشگاه تهیه نمایید", "در سریعترین زمان ممکن",R.color.colorPrimaryBack)
                .swipeToDismiss(true)
                .build();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, OnlineStoreWelcomeActivity.class);
        context.startActivity(starter);
    }
}
