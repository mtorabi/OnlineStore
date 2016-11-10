package com.razanpardazesh.onlinestore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.razanpardazesh.mtglibrary.tools.Convertor;
import com.razanpardazesh.mtglibrary.tools.FontApplier;
import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WelcomeActivity extends AppCompatActivity {

    ViewPager welcomePager;
    View imgLogo1;
    View imgLogo2;
    View imgLogo3;

    View imgHeaderPic1;
    View imgHeaderPic2;
    View imgHeaderPic3;

    View imgHeader3;
    View imgHeader2;
    View imgHeader1;

    View imgFooter3;
    View imgFooter2;
    View imgFooter1;

    View imgbg3;
    View imgbg2;
    View imgbg1;

    TextView txtWelcome1;
    TextView txtWelcome2;
    TextView txtWelcome3;

    private final float default_bg_alpha = 0.4f;

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            animateLogos(position, positionOffset);
            animateHeaderPic(position, positionOffset);
            animateHeader(position, positionOffset);
            animateFooter(position, positionOffset);
            animateBG(position, positionOffset);
            animateText(position, positionOffset);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void animateHeaderPic(int position, float positionOffset) {
        if (imgHeaderPic1 == null)
            imgHeaderPic1 = findViewById(R.id.imgHeaderPic1);

        if (imgHeaderPic2 == null)
            imgHeaderPic2 = findViewById(R.id.imgHeaderPic2);

        if (imgHeaderPic3 == null)
            imgHeaderPic3 = findViewById(R.id.imgHeaderPic3);

        int headerPicHeight = imgHeaderPic1.getHeight();

        switch (position) {
            case 0:
                if (positionOffset == 0) {
                    imgHeaderPic1.setTranslationY(0);
                    imgHeaderPic2.setTranslationY(-headerPicHeight);
                    imgHeaderPic3.setTranslationY(-headerPicHeight);
                    return;
                }
                imgHeaderPic1.setTranslationY(-headerPicHeight * positionOffset);
                imgHeaderPic2.setTranslationY(-headerPicHeight + (headerPicHeight * positionOffset));
                imgHeaderPic3.setTranslationY(-headerPicHeight);
                return;
            case 1:
                if (positionOffset == 0) {
                    imgHeaderPic1.setTranslationY(-headerPicHeight);
                    imgHeaderPic2.setTranslationY(0);
                    imgHeaderPic3.setTranslationY(-headerPicHeight);
                    return;
                }
                imgHeaderPic2.setTranslationY(-headerPicHeight * positionOffset);
                imgHeaderPic3.setTranslationY(-headerPicHeight + (headerPicHeight * positionOffset));
                imgHeaderPic1.setTranslationY(-headerPicHeight);
                return;
            case 2:
                if (positionOffset == 0) {
                    imgHeaderPic1.setTranslationY(-headerPicHeight);
                    imgHeaderPic2.setTranslationY(-headerPicHeight);
                    imgHeaderPic3.setTranslationY(0);
                    return;
                }
                return;
        }

    }

    private void animateHeader(int position, float positionOffset) {
        if (imgHeader3 == null)
            imgHeader3 = findViewById(R.id.imgHeader3);

        if (imgHeader2 == null)
            imgHeader2 = findViewById(R.id.imgHeader2);

        if (imgHeader1 == null)
            imgHeader1 = findViewById(R.id.imgHeader1);

        switch (position) {
            case 0:
                if (positionOffset == 0) {
                    imgHeader1.setAlpha(1);
                    imgHeader2.setAlpha(0);
                    imgHeader3.setAlpha(0);
                    return;
                }
                imgHeader1.setAlpha(1 - positionOffset);
                imgHeader2.setAlpha(positionOffset);
                imgHeader3.setAlpha(0);
                return;
            case 1:
                if (positionOffset == 0) {
                    imgHeader1.setAlpha(0);
                    imgHeader2.setAlpha(1);
                    imgHeader3.setAlpha(0);
                    return;
                }
                imgHeader2.setAlpha(1 - positionOffset);
                imgHeader3.setAlpha(positionOffset);
                imgHeader1.setAlpha(0);
                return;
            case 2:
                if (positionOffset == 0) {
                    imgHeader1.setAlpha(0);
                    imgHeader2.setAlpha(0);
                    imgHeader3.setAlpha(1);
                    return;
                }
                return;
        }

    }

    private void animateBG(int position, float positionOffset) {
        if (imgbg3 == null) {
            imgbg3 = findViewById(R.id.imgbg3);
        }

        if (imgbg2 == null)
            imgbg2 = findViewById(R.id.imgbg2);

        if (imgbg1 == null)
            imgbg1 = findViewById(R.id.imgbg1);

        float alphaOffset = default_bg_alpha * positionOffset;
        switch (position) {
            case 0:
                if (positionOffset == 0) {
                    imgbg1.setAlpha(default_bg_alpha);
                    imgbg2.setAlpha(0);
                    imgbg3.setAlpha(0);
                    return;
                }
                imgbg1.setAlpha(default_bg_alpha - alphaOffset);
                imgbg2.setAlpha(alphaOffset);
                imgbg3.setAlpha(0);
                return;
            case 1:
                if (positionOffset == 0) {
                    imgbg1.setAlpha(0);
                    imgbg2.setAlpha(default_bg_alpha);
                    imgbg3.setAlpha(0);
                    return;
                }
                imgbg2.setAlpha(default_bg_alpha - alphaOffset);
                imgbg3.setAlpha(alphaOffset);
                imgbg1.setAlpha(0);
            case 2:
                if (positionOffset == 0) {
                    imgbg1.setAlpha(0);
                    imgbg2.setAlpha(0);
                    imgbg3.setAlpha(default_bg_alpha);
                    return;
                }
                return;
        }

    }

    private void animateFooter(int position, float positionOffset) {
        if (imgFooter3 == null)
            imgFooter3 = findViewById(R.id.imgFooter3);

        if (imgFooter2 == null)
            imgFooter2 = findViewById(R.id.imgFooter2);

        if (imgFooter1 == null)
            imgFooter1 = findViewById(R.id.imgFooter1);

        switch (position) {
            case 0:
                if (positionOffset == 0) {
                    imgFooter1.setAlpha(1);
                    imgFooter2.setAlpha(0);
                    imgFooter3.setAlpha(0);
                    return;
                }
                imgFooter1.setAlpha(1 - positionOffset);
                imgFooter2.setAlpha(positionOffset);
                imgFooter3.setAlpha(0);
                return;
            case 1:
                if (positionOffset == 0) {
                    imgFooter1.setAlpha(0);
                    imgFooter2.setAlpha(1);
                    imgFooter3.setAlpha(0);
                    return;
                }
                imgFooter2.setAlpha(1 - positionOffset);
                imgFooter3.setAlpha(positionOffset);
                imgFooter1.setAlpha(0);
                return;
            case 2:
                if (positionOffset == 0) {
                    imgFooter1.setAlpha(0);
                    imgFooter2.setAlpha(0);
                    imgFooter3.setAlpha(1);
                    return;
                }
                return;
        }

    }


    private void animateLogos(int position, float positionOffset) {
        if (imgLogo1 == null)
            imgLogo1 = findViewById(R.id.imgLogo1);

        if (imgLogo2 == null)
            imgLogo2 = findViewById(R.id.imgLogo2);

        if (imgLogo3 == null)
            imgLogo3 = findViewById(R.id.imgLogo3);


        float reversPositionOffset = 1 - positionOffset;
        float reversAcceptableScale = reversPositionOffset < 0.5f ? 0 : reversPositionOffset;
        float acceptableScale = positionOffset < 0.5f ? 0 : positionOffset;

        switch (position) {
            case 0:
                if (positionOffset == 0) {
                    imgLogo1.setScaleX(1);
                    imgLogo2.setScaleX(0);
                    imgLogo3.setScaleX(0);
                    imgLogo1.setScaleY(1);
                    imgLogo2.setScaleY(0);
                    imgLogo3.setScaleY(0);

                    imgLogo1.setAlpha(1);
                    imgLogo2.setAlpha(0);
                    imgLogo3.setAlpha(0);
                    return;
                }
                imgLogo1.setScaleX(reversAcceptableScale);
                imgLogo2.setScaleX(acceptableScale);
                imgLogo3.setScaleX(0);
                imgLogo1.setScaleY(reversAcceptableScale);
                imgLogo2.setScaleY(acceptableScale);
                imgLogo3.setScaleY(0);

                imgLogo1.setAlpha(1 - positionOffset);
                imgLogo2.setAlpha(positionOffset);
                imgLogo3.setAlpha(0);
                return;
            case 1:
                if (positionOffset == 0) {
                    imgLogo1.setScaleX(0);
                    imgLogo2.setScaleX(1);
                    imgLogo3.setScaleX(0);
                    imgLogo1.setScaleY(0);
                    imgLogo2.setScaleY(1);
                    imgLogo3.setScaleY(0);

                    imgLogo1.setAlpha(0);
                    imgLogo2.setAlpha(1);
                    imgLogo3.setAlpha(0);
                    return;
                }
                imgLogo2.setScaleX(reversAcceptableScale);
                imgLogo3.setScaleX(acceptableScale);
                imgLogo1.setScaleX(0);
                imgLogo2.setScaleY(reversAcceptableScale);
                imgLogo3.setScaleY(acceptableScale);
                imgLogo1.setScaleY(0);

                imgLogo2.setAlpha(1 - positionOffset);
                imgLogo3.setAlpha(positionOffset);
                imgLogo1.setAlpha(0);
                return;
            case 2:
                if (positionOffset == 0) {
                    imgLogo1.setScaleX(0);
                    imgLogo2.setScaleX(0);
                    imgLogo3.setScaleX(1);
                    imgLogo1.setScaleY(0);
                    imgLogo2.setScaleY(0);
                    imgLogo3.setScaleY(1);

                    imgLogo1.setAlpha(0);
                    imgLogo2.setAlpha(0);
                    imgLogo3.setAlpha(1);
                    return;
                }
                return;
        }
    }

    private void animateText(int position, float positionOffset) {
        if (txtWelcome1 == null) {
            txtWelcome1 = (TextView) findViewById(R.id.txtWelcome1);
            FontApplier.applyMainFont(txtWelcome1);
        }

        if (txtWelcome2 == null) {
            txtWelcome2 = (TextView) findViewById(R.id.txtWelcome2);
            FontApplier.applyMainFont(txtWelcome2);
        }

        if (txtWelcome3 == null) {
            txtWelcome3 = (TextView) findViewById(R.id.txtWelcome3);
            FontApplier.applyMainFont(txtWelcome3);
        }


        float reversPositionOffset = 1 - positionOffset;
        float reversAcceptableScale = reversPositionOffset < 0.5f ? 0 : reversPositionOffset;
        float acceptableScale = positionOffset < 0.5f ? 0 : positionOffset;

        switch (position) {
            case 0:
                if (positionOffset == 0) {
                    txtWelcome1.setScaleX(1);
                    txtWelcome2.setScaleX(0);
                    txtWelcome3.setScaleX(0);
                    txtWelcome1.setScaleY(1);
                    txtWelcome2.setScaleY(0);
                    txtWelcome3.setScaleY(0);

                    txtWelcome1.setAlpha(1);
                    txtWelcome2.setAlpha(0);
                    txtWelcome3.setAlpha(0);
                    return;
                }
                txtWelcome1.setScaleX(reversAcceptableScale);
                txtWelcome2.setScaleX(acceptableScale);
                txtWelcome3.setScaleX(0);
                txtWelcome1.setScaleY(reversAcceptableScale);
                txtWelcome2.setScaleY(acceptableScale);
                txtWelcome3.setScaleY(0);

                txtWelcome1.setAlpha(1 - positionOffset);
                txtWelcome2.setAlpha(positionOffset);
                txtWelcome3.setAlpha(0);
                return;
            case 1:
                if (positionOffset == 0) {
                    txtWelcome2.setScaleX(1);
                    txtWelcome1.setScaleX(0);
                    txtWelcome3.setScaleX(0);
                    txtWelcome2.setScaleY(1);
                    txtWelcome1.setScaleY(0);
                    txtWelcome3.setScaleY(0);

                    txtWelcome2.setAlpha(1);
                    txtWelcome1.setAlpha(0);
                    txtWelcome3.setAlpha(0);
                    return;
                }
                txtWelcome2.setScaleX(reversAcceptableScale);
                txtWelcome3.setScaleX(acceptableScale);
                txtWelcome1.setScaleX(0);
                txtWelcome2.setScaleY(reversAcceptableScale);
                txtWelcome3.setScaleY(acceptableScale);
                txtWelcome1.setScaleY(0);

                txtWelcome2.setAlpha(1 - positionOffset);
                txtWelcome3.setAlpha(positionOffset);
                txtWelcome1.setAlpha(0);
                return;
            case 2:
                if (positionOffset == 0) {
                    txtWelcome3.setScaleX(1);
                    txtWelcome1.setScaleX(0);
                    txtWelcome2.setScaleX(0);
                    txtWelcome3.setScaleY(1);
                    txtWelcome1.setScaleY(0);
                    txtWelcome2.setScaleY(0);

                    txtWelcome3.setAlpha(1);
                    txtWelcome1.setAlpha(0);
                    txtWelcome2.setAlpha(0);
                    return;
                }
                return;
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        initPager();

        if (imgbg3 == null) {
            imgbg3 = findViewById(R.id.imgbg3);
            imgbg3.getBackground().setDither(true);
        }

        if (imgbg2 == null) {
            imgbg2 = findViewById(R.id.imgbg2);
            imgbg2.getBackground().setDither(true);
        }

        if (imgbg1 == null) {
            imgbg1 = findViewById(R.id.imgbg1);
            imgbg2.getBackground().setDither(true);
        }

        if (txtWelcome3 == null) {
            txtWelcome3 = (TextView) findViewById(R.id.txtWelcome3);
            FontApplier.applyMainFont(txtWelcome3);
            txtWelcome3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finishThisActivity();
                }
            });
        }

    }

    private void initPager() {
        if (welcomePager == null)
            welcomePager = (ViewPager) findViewById(R.id.welcomePager);
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        welcomePager.setAdapter(adapter);
        welcomePager.addOnPageChangeListener(pageChangeListener);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, WelcomeActivity.class);
        context.startActivity(starter);
    }

    private class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            WelcomeFragment f = WelcomeFragment.newInstance(position);
            f.setWelcomeFragmentListener(new WelcomeFragment.WelcomeFragmentListener() {
                @Override
                public void onStartClick() {
                    finishThisActivity();
                }
            });
            return f;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private void finishThisActivity() {

        SessionManagement.getInstance(getApplicationContext()).setWelcomeShowed(true);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
