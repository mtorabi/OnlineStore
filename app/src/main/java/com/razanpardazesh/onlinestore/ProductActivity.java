package com.razanpardazesh.onlinestore;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.razanpardazesh.onlinestore.CustomView.Indicator;
import com.razanpardazesh.onlinestore.CustomView.OnlineStoreActivity;
import com.razanpardazesh.onlinestore.Tools.AsyncWrapper;
import com.razanpardazesh.onlinestore.Tools.FabWrapper;
import com.razanpardazesh.onlinestore.Tools.FontApplier;
import com.razanpardazesh.onlinestore.Tools.NetworkAsyncWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProducts;
import com.razanpardazesh.onlinestore.repo.ProductFakeRepo;
import com.razanpardazesh.onlinestore.repo.ProductServerRepo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProductActivity extends OnlineStoreActivity {

    private static final String EXTRA_KEY_PRODUCT_ID = "productId";
    private static final String EXTRA_KEY_PRODUCT_IMAGE = "productImage";
    private static final String EXTRA_KEY_IMAGE_LEFT = "imageLeft";
    private static final String EXTRA_KEY_IMAGE_TOP = "imageTop";
    private static final String EXTRA_KEY_IMAGE_WIDTH = "imageWidth";
    private static final String EXTRA_KEY_IMAGE_HEIGHT = "imageHeight";

    private Product product = null;

    private IProducts productsRepo;

    private AsyncWrapper getProductsAsync;

    private int thumb_left = 0;
    private int thumb_top = 0;
    private int thumb_widtht = 0;
    private int thumb_height = 0;
    private String thumb_Url = "";
    private ImageView imgMainPic;
    private int ANIM_DURATION = 300;
    private ColorDrawable colorDrawable;
    private float mWidthScale;
    private float mHeightScale;
    private float mLeftDelta;
    private float mTopDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        parseExtra(getIntent());
        initPreDraw(savedInstanceState);
        initToolbar();
        getProductData();
        initFloatingActionButton();

    }

    private void initFloatingActionButton() {
        FabWrapper fabWrapper = new FabWrapper(this,true);
        fabWrapper.initFab(R.id.fab);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initLayouts(true);
    }

    private void initLayouts(Boolean showContent) {
        View root = findViewById(R.id.root);
        View content = findViewById(R.id.contentBox);



        if (colorDrawable == null) {
            colorDrawable = new ColorDrawable(Color.WHITE);
        }

        if (showContent) {
            colorDrawable.setAlpha(1);
            content.setAlpha(1);
        }
        else {
            colorDrawable.setAlpha(0);
            content.setAlpha(0);
        }

        if (Build.VERSION.SDK_INT > 15)
            root.setBackground(colorDrawable);
        else
            root.setBackgroundDrawable(colorDrawable);

        initMainPic();
        FontApplier.applyMainFont(root);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!handleBackPress())
                    finish();
            }
        });

        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_forward_black_24dp);
    }

    private void parseExtra(Intent data) {
        if (data == null || !data.hasExtra(EXTRA_KEY_PRODUCT_ID)) {
            return;
        }

        product = new Product();
        product.setId(data.getLongExtra(EXTRA_KEY_PRODUCT_ID, -1));

        if (!data.hasExtra(EXTRA_KEY_IMAGE_LEFT)) {
            return;
        }

        thumb_height = data.getIntExtra(EXTRA_KEY_IMAGE_HEIGHT, 0);
        thumb_widtht = data.getIntExtra(EXTRA_KEY_IMAGE_WIDTH, 0);
        thumb_left = data.getIntExtra(EXTRA_KEY_IMAGE_LEFT, 0);
        thumb_top = data.getIntExtra(EXTRA_KEY_IMAGE_TOP, 0);
        thumb_Url = data.getStringExtra(EXTRA_KEY_PRODUCT_IMAGE);
    }

    public static void openActivity(FragmentActivity act, long id) {
        Intent data = new Intent(act, ProductActivity.class);
        data.putExtra(EXTRA_KEY_PRODUCT_ID, id);
        act.startActivity(data);
    }

    public static void openActivity(FragmentActivity act, long id, ImageView imageView, String ImageUrl) {
        Intent data = new Intent(act, ProductActivity.class);

        int[] screenLocation = new int[2];

        imageView.getLocationOnScreen(screenLocation);

        data.putExtra(EXTRA_KEY_PRODUCT_ID, id);
        data.putExtra(EXTRA_KEY_PRODUCT_IMAGE, ImageUrl);
        data.putExtra(EXTRA_KEY_IMAGE_HEIGHT, imageView.getHeight());
        data.putExtra(EXTRA_KEY_IMAGE_WIDTH, imageView.getWidth());
        data.putExtra(EXTRA_KEY_IMAGE_LEFT, screenLocation[0]);
        data.putExtra(EXTRA_KEY_IMAGE_TOP, screenLocation[1]);
        act.startActivity(data);

    }

    public void initPreDraw(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            initLayouts(true);
            return;
        }
        else
        {
            initLayouts(false);
        }

        if (imgMainPic == null) {
            imgMainPic = (ImageView) findViewById(R.id.imgMainPic);

        }



        ViewTreeObserver observer = imgMainPic.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                imgMainPic.getViewTreeObserver().removeOnPreDrawListener(this);

                int[] screenLocation = new int[2];
                imgMainPic.getLocationOnScreen(screenLocation);
                int mLeftDelta = thumb_left - screenLocation[0];
                int mTopDelta = thumb_top - screenLocation[1];

                // Scale factors to make the large version the same size as the thumbnail
                float mWidthScale = (float) thumb_widtht / imgMainPic.getWidth();
                float mHeightScale = (float) thumb_height / imgMainPic.getHeight();

                enterAnimation(mLeftDelta, mTopDelta, mWidthScale, mHeightScale);
                return true;
            }
        });
    }

    private void enterAnimation(int deltaLeft, int deltaTop, float widthScale, float heightScale) {

        if (imgMainPic == null) {
            imgMainPic = (ImageView) findViewById(R.id.imgMainPic);
        }

        mWidthScale = widthScale;
        mHeightScale = heightScale;
        mLeftDelta = deltaLeft;
        mTopDelta = deltaTop;

        imgMainPic.setPivotX(0);
        imgMainPic.setPivotY(0);
        imgMainPic.setScaleX(widthScale);
        imgMainPic.setScaleY(heightScale);
        imgMainPic.setTranslationX(deltaLeft);
        imgMainPic.setTranslationY(deltaTop);
        View contentBox = findViewById(R.id.contentBox);
        AnimatorSet set = new AnimatorSet();
        AnimatorSet setImage = new AnimatorSet();

        // interpolator where the rate of change starts out quickly and then decelerates.
        TimeInterpolator sDecelerator = new DecelerateInterpolator();


        ObjectAnimator imgXAnim = ObjectAnimator.ofFloat(imgMainPic, "translationX", 0);
        ObjectAnimator imgYAnim = ObjectAnimator.ofFloat(imgMainPic, "translationY", 0);
        ObjectAnimator imgSXAnim = ObjectAnimator.ofFloat(imgMainPic, "scaleX", 1);
        ObjectAnimator imgSYAnim = ObjectAnimator.ofFloat(imgMainPic, "scaleY", 1);

        setImage.playTogether(imgXAnim, imgYAnim, imgSXAnim, imgSYAnim);
        setImage.setInterpolator(sDecelerator);
        setImage.setDuration(ANIM_DURATION);

        AnimatorSet contentSet = new AnimatorSet();

        ObjectAnimator contentBoxAnim = ObjectAnimator.ofFloat(contentBox, "alpha", 255);
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0, 255);

        contentSet.playTogether(contentBoxAnim, bgAnim);
        contentSet.setInterpolator(sDecelerator);
        contentSet.setDuration(ANIM_DURATION / 2);


        set.playSequentially(setImage, contentSet);
        set.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void exitAnimation(final Runnable endAction) {

        if (Build.VERSION.SDK_INT < 16) {
            return;
        }

        if (imgMainPic == null) {
            imgMainPic = (ImageView) findViewById(R.id.imgMainPic);
        }

        int[] screenLocation = new int[2];
        imgMainPic.getLocationOnScreen(screenLocation);
        int mLeftDelta = thumb_left - screenLocation[0];
        int mTopDelta = thumb_top - screenLocation[1];

        TimeInterpolator sInterpolator = new AccelerateInterpolator();
        imgMainPic.animate().setDuration(ANIM_DURATION).scaleX(mWidthScale).scaleY(mHeightScale).
                translationX(mLeftDelta).translationY(mTopDelta)
                .setInterpolator(sInterpolator).withEndAction(endAction);


        View contentBox = findViewById(R.id.contentBox);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.hide();


        // Fade out objects
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0);
        bgAnim.setDuration(ANIM_DURATION);
        bgAnim.start();

        ObjectAnimator contentBoxAnim = ObjectAnimator.ofFloat(contentBox, "alpha", 0);
        contentBoxAnim.setDuration(ANIM_DURATION / 2);
        contentBoxAnim.setInterpolator(sInterpolator);
        contentBoxAnim.start();

    }

    @Override
    public void onBackPressed() {

        if (!handleBackPress())
            super.onBackPressed();
    }

    private Boolean handleBackPress() {
        if (Build.VERSION.SDK_INT >= 16) {
            exitAnimation(new Runnable() {
                public void run() {

                    finish();
                    overridePendingTransition(0, 0);
                }
            });
            return true;
        } else
            return false;
    }

    private void initMainPic() {

        if (imgMainPic == null) {
            imgMainPic = (ImageView) findViewById(R.id.imgMainPic);
        }

        int width = getResources().getDisplayMetrics().widthPixels;

        int height = width;

        final ViewGroup.LayoutParams params = imgMainPic.getLayoutParams();
        params.height = height;
        imgMainPic.setLayoutParams(params);

        if (SessionManagement.getInstance(getApplicationContext()).getFakeBind())
            imgMainPic.setImageResource(Integer.parseInt(thumb_Url));
        else {
            //TODO mtg
        }

    }

    private Boolean getProductData() {
        if (product == null || product.getId() < 1)
            return false;

        if (SessionManagement.getInstance(getApplicationContext()).getFakeBind())
            productsRepo = new ProductFakeRepo();
        else
            productsRepo = new ProductServerRepo();

        //TODO
        getProductsAsync = new NetworkAsyncWrapper().initDefaultProgressDialog("", true).setDoOnBackground(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                return productsRepo.getProduct(getApplicationContext(), product.getId());
            }
        }).setDoOnAnswer(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                if (object == null && !(object instanceof ProductAnswer)) {
                    return null;
                }

                ProductAnswer answer = (ProductAnswer) object;

                if (answer.getIsSuccess() == 1 && answer.getProduct() != null)
                    setProductData(answer.getProduct());
                return null;
            }
        });
        getProductsAsync.run(getApplicationContext());
        return true;
    }

    private void setProductData(Product product) {
        this.product = product;

        TextView txtPrice = (TextView) findViewById(R.id.txtPrice);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) findViewById(R.id.txtDescription);

        NumberFormat formatter = new DecimalFormat("###,###,###,###");

        txtPrice.setText(formatter.format(product.getPrice()));
        txtTitle.setText(product.getName());
        setTitle(product.getName());
        txtDescription.setText(product.getDescription() + "\n" + product.getDescription());
    }
}
