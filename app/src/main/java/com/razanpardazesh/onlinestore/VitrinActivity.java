package com.razanpardazesh.onlinestore;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.razanpardazesh.onlinestore.CustomView.Indicator;
import com.razanpardazesh.onlinestore.Tools.AsyncWraper;
import com.razanpardazesh.onlinestore.Tools.FontApplier;
import com.razanpardazesh.onlinestore.Tools.NetworkAsyncWraper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.ViewAdapter.HorizontalSmallProductsAdaper;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductListAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProducts;
import com.razanpardazesh.onlinestore.repo.ProductFakeRepo;
import com.razanpardazesh.onlinestore.repo.ProductServerRepo;

import java.util.ArrayList;
import java.util.Objects;


public class VitrinActivity extends AppCompatActivity {

    IProducts productsRepo = null;
    HorizontalSmallProductsAdaper mostSoldAdapter;
    HorizontalSmallProductsAdaper mostVisitedAdapter;

    NetworkAsyncWraper getMostSold = new NetworkAsyncWraper();
    NetworkAsyncWraper getMostVisited = new NetworkAsyncWraper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitrin);

        initDrawer_Toolbar();
        initFloatingActionButton();
        initAdvertiseBox();
        initFonts();
        initRepos();
        runGetProducts();
    }

    private void initFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initDrawer_Toolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setLogo(R.drawable.teemanshop_logo);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_camera) {
                    // Handle the camera action
                } else if (id == R.id.nav_gallery) {

                } else if (id == R.id.nav_slideshow) {

                } else if (id == R.id.nav_manage) {

                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void initFonts() {
        View drawer_layout = findViewById(R.id.drawer_layout);
        FontApplier.applyMainFont(drawer_layout);
    }

    private void initRepos() {
        if (SessionManagement.getInstance(getApplicationContext()).getFakeBind())
            productsRepo = new ProductFakeRepo();
        else
            productsRepo = new ProductServerRepo();
    }

    private void initMostSold(ProductListAnswer productListAnswer) {

        if (productListAnswer == null || productListAnswer.getProducts() == null)
            return;

        RecyclerView lst_most_sold = (RecyclerView) findViewById(R.id.lst_most_sold);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        lst_most_sold.setLayoutManager(layoutManager);

        mostSoldAdapter = new HorizontalSmallProductsAdaper(getApplicationContext());
        mostSoldAdapter.addProducts(productListAnswer.getProducts());
        lst_most_sold.setAdapter(mostSoldAdapter);

    }

    private void initMostVisited(ProductListAnswer productListAnswer) {

        if (productListAnswer == null || productListAnswer.getProducts() == null)
            return;

        RecyclerView lst_most_visited = (RecyclerView) findViewById(R.id.lst_most_visited);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        lst_most_visited.setLayoutManager(layoutManager);
        mostVisitedAdapter = new HorizontalSmallProductsAdaper(getApplicationContext());
        mostVisitedAdapter.addProducts(productListAnswer.getProducts());

        lst_most_visited.setAdapter(mostVisitedAdapter);

    }

    private void initAdvertiseBox() {
        View advertiseHeaderBox = findViewById(R.id.advertiseHeaderBox);

        int width = getResources().getDisplayMetrics().widthPixels;

        int height = width / 2;

        final ViewGroup.LayoutParams params = advertiseHeaderBox.getLayoutParams();
        params.height = height;
        advertiseHeaderBox.setLayoutParams(params);

        final TabsPagerAdapter adapter = new TabsPagerAdapter(getSupportFragmentManager(), height);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        final Indicator indexBox = (Indicator) findViewById(R.id.indexBox);
        indexBox.setViewPager(pager);

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initAdvertiseBox();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vitrin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TabsPagerAdapter extends FragmentPagerAdapter {
        int height = 300;

        public TabsPagerAdapter(FragmentManager fm, int height) {
            super(fm);
            this.height = height;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position + 1, height);
        }

        @Override
        public int getCount() {
            return 3;
        }

    }

    public void runGetProducts() {
        //get most sold products
        getMostSold.setDoOnBackground(new AsyncWraper.Callback() {
            @Override
            public Object call(Object object) {
                return productsRepo.getMostSoldProducts(getApplicationContext(), "", 0, 10);
            }
        }).setDoOnAnswer(new AsyncWraper.Callback() {
            @Override
            public Object call(Object object) {
                if (object != null || object instanceof ProductListAnswer)
                    initMostSold((ProductListAnswer) object);
                return null;
            }
        }).setDoOnError(new AsyncWraper.Callback() {
            @Override
            public Object call(Object object) {
                //TODO MTG
                return null;
            }
        }).run(getApplicationContext());

        //get most visited products
        getMostVisited.setDoOnBackground(new AsyncWraper.Callback() {
            @Override
            public Object call(Object object) {
                return productsRepo.getMostVistedProducts(getApplicationContext(),"",0,10);
            }
        }).setDoOnAnswer(new AsyncWraper.Callback() {
            @Override
            public Object call(Object object) {
                if (object != null || object instanceof ProductListAnswer)
                    initMostVisited((ProductListAnswer) object);
                return null;
            }
        }).setDoOnError(new AsyncWraper.Callback() {
            @Override
            public Object call(Object object) {
                //TODO MTG
                return null;
            }
        }).run(getApplicationContext());
    }

}
