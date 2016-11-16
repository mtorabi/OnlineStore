package com.razanpardazesh.onlinestore;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.HandlerThread;
import android.os.Looper;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
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

    View rightArrow2;
    View rightArrow1;
    View leftArrow2;
    View leftArrow3;

    View root;

    int pagerSize = 4;

    private boolean animateHelpArrow = true;

    private final float default_bg_alpha = 0.4f;

    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (animateHelpArrow && position == 1)
                animateHelpArrow = false;

            if (position >= pagerSize - 2) {
                if (root == null)
                    root = findViewById(R.id.root);

                root.setAlpha(1 - positionOffset);

                if (positionOffset > 0.3) {
                    finishThisActivity();

                }
                return;
            }
            animateLogos(position, positionOffset);
            animateHeaderPic(position, positionOffset);
            animateHeader(position, positionOffset);
            animateFooter(position, positionOffset);
            animateBG(position, positionOffset);
            animateText(position, positionOffset);
            animateArrows(position, positionOffset);


        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void animateArrows(int position, float positionOffset) {
        if (leftArrow2 == null) {
            leftArrow2 = findViewById(R.id.leftArrow2);
        }
        if (leftArrow3 == null) {
            leftArrow3 = findViewById(R.id.leftArrow3);
        }
        if (rightArrow2 == null) {
            rightArrow2 = findViewById(R.id.rightArrow2);
        }
        if (rightArrow1 == null) {
            rightArrow1 = findViewById(R.id.rightArrow1);
        }


        switch (position) {
            case 0:
                if (positionOffset == 0) {
                    leftArrow2.setAlpha(0);
                    leftArrow3.setAlpha(0);
                    rightArrow2.setAlpha(0);
                    rightArrow1.setAlpha(1);
                    return;
                }
                rightArrow1.setAlpha(1 - positionOffset);
                rightArrow2.setAlpha(positionOffset);
                leftArrow3.setAlpha(0);
                leftArrow2.setAlpha(positionOffset);
                return;
            case 1:
                if (positionOffset == 0) {
                    leftArrow2.setAlpha(1);
                    leftArrow3.setAlpha(0);
                    rightArrow2.setAlpha(1);
                    rightArrow1.setAlpha(0);
                    return;
                }
                rightArrow1.setAlpha(0);
                rightArrow2.setAlpha(1 - positionOffset);
                leftArrow3.setAlpha(positionOffset);
                leftArrow2.setAlpha(1 - positionOffset);
            case 2:
                if (positionOffset == 0) {
                    leftArrow2.setAlpha(0);
                    leftArrow3.setAlpha(1);
                    rightArrow2.setAlpha(0);
                    rightArrow1.setAlpha(0);
                    return;
                }

                return;
        }

    }

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

        if (rightArrow1 == null)
            rightArrow1 = findViewById(R.id.rightArrow1);
        animateViewForHelp(rightArrow1);
    }

    private void animateViewForHelp(View view) {
        if (view == null)
            return;

        AnimatorSet setGrow = new AnimatorSet();
        Animator animatorGrowX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f);
        animatorGrowX.setDuration(200);
        animatorGrowX.setInterpolator(new AccelerateInterpolator());

        Animator animatorGrowY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f);
        animatorGrowY.setDuration(200);
        animatorGrowY.setInterpolator(new AccelerateInterpolator());

        setGrow.playTogether(animatorGrowX, animatorGrowY);

        AnimatorSet setShirink = new AnimatorSet();
        Animator animatorShirinkX = ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1f);
        animatorShirinkX.setDuration(200);
        animatorShirinkX.setInterpolator(new DecelerateInterpolator());

        Animator animatorShirinkY = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1f);
        animatorShirinkY.setDuration(200);
        animatorShirinkY.setInterpolator(new DecelerateInterpolator());

        setShirink.playTogether(animatorShirinkX, animatorShirinkY);

        //rotate1
        AnimatorSet setRotate = new AnimatorSet();
        Animator animatorRotate1 = ObjectAnimator.ofFloat(view, "rotation", 0f, 45f);
        animatorRotate1.setDuration(50);
        animatorRotate1.setInterpolator(new AccelerateInterpolator());

        Animator animatorRotate2 = ObjectAnimator.ofFloat(view, "rotation", 45f, 0f);
        animatorRotate2.setDuration(50);
        animatorRotate2.setInterpolator(new DecelerateInterpolator());

        setRotate.playSequentially(animatorRotate1, animatorRotate2);

        //rotate2
        AnimatorSet setRotate2 = new AnimatorSet();
        Animator animatorRotate3 = ObjectAnimator.ofFloat(view, "rotation", 0f, 45f);
        animatorRotate3.setDuration(50);
        animatorRotate3.setInterpolator(new AccelerateInterpolator());

        Animator animatorRotate4 = ObjectAnimator.ofFloat(view, "rotation", 45f, 0f);
        animatorRotate4.setDuration(50);
        animatorRotate4.setInterpolator(new DecelerateInterpolator());

        setRotate2.playSequentially(animatorRotate3, animatorRotate4);

        //rotate3
        AnimatorSet setRotate3 = new AnimatorSet();
        Animator animatorRotate5 = ObjectAnimator.ofFloat(view, "rotation", 0f, 45f);
        animatorRotate5.setDuration(50);
        animatorRotate5.setInterpolator(new AccelerateInterpolator());

        Animator animatorRotate6 = ObjectAnimator.ofFloat(view, "rotation", 45f, 0f);
        animatorRotate6.setDuration(50);
        animatorRotate2.setInterpolator(new DecelerateInterpolator());

        setRotate3.playSequentially(animatorRotate3, animatorRotate4);

        //rotate4
        AnimatorSet setRotate4 = new AnimatorSet();
        Animator animatorRotate7 = ObjectAnimator.ofFloat(view, "rotation", 0f, 45f);
        animatorRotate7.setDuration(50);
        animatorRotate7.setInterpolator(new AccelerateInterpolator());

        Animator animatorRotate8 = ObjectAnimator.ofFloat(view, "rotation", 45f, 0f);
        animatorRotate8.setDuration(50);
        animatorRotate8.setInterpolator(new DecelerateInterpolator());

        setRotate4.playSequentially(animatorRotate3, animatorRotate4);

        AnimatorSet setScales = new AnimatorSet();
        setScales.playSequentially(setGrow, setShirink);

        AnimatorSet setRotations = new AnimatorSet();
        setRotations.playSequentially(setRotate, setRotate2, setRotate3, setRotate4);

        final AnimatorSet setGlobal = new AnimatorSet();
        setGlobal.playTogether(setScales, setRotations);
        setGlobal.setStartDelay(1000);
        setGlobal.start();
        setGlobal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (animateHelpArrow) {
                    setGlobal.setStartDelay(2000);
                    setGlobal.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

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
            return pagerSize;
        }

    }

    private void finishThisActivity() {

        SessionManagement.getInstance(getApplicationContext()).setWelcomeShowed(true);
        overridePendingTransition(0, android.R.anim.fade_out);
        finish();

    }

    @Override
    public void onBackPressed() {
        if (welcomePager != null) {
            welcomePager.setCurrentItem(2, true);
            animateHelpArrow = true;

            if (txtWelcome3 == null)
                txtWelcome3 = (TextView) findViewById(R.id.txtWelcome3);

            animateViewForHelp(txtWelcome3);
        }
    }
}
