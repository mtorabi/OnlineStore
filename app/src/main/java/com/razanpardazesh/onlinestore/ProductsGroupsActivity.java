package com.razanpardazesh.onlinestore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.BoolRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.razanpardazesh.mtglibrary.tools.AsyncWrapper;
import com.razanpardazesh.mtglibrary.tools.NetworkAsyncWrapper;
import com.razanpardazesh.onlinestore.Tools.FabWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.ViewAdapter.ProductsGroupsAdapter;
import com.razanpardazesh.onlinestore.ViewAdapter.decorations.DividerDecoration;
import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsFakeRepo;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsServerRepo;

import java.util.ArrayList;

public class ProductsGroupsActivity extends AppCompatActivity {

    private static final String EXTRA_KEY_GROUP_ID = "groupID";
    private ArrayList<ProductsGroup> productsGroupArray = null;
    private ProductsGroup productsGroup;
    private IProductsGroups productsGroupsRepo;
    private AsyncWrapper getProducsGroupsAsync;
    private ProductsGroupsAdapter groupsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        initToolbar();
        initFloatActionButton();
        getProductsGroupsData();


    }

    public static void openActivity(FragmentActivity act, Long gruopId) {
        Intent data = new Intent(act, ProductsGroupsActivity.class);
        data.putExtra(EXTRA_KEY_GROUP_ID, gruopId);
        act.startActivity(data);
    }

    private Boolean getProductsGroupsData() {
        if (productsGroup == null || productsGroup.getId() < 0)


            if (SessionManagement.getInstance(getApplicationContext()).getFakeBind()) {
                productsGroupsRepo = new ProductsGroupsFakeRepo();
            } else {
                productsGroupsRepo = new ProductsGroupsServerRepo();
            }

        //TODO
        getProducsGroupsAsync = new NetworkAsyncWrapper().initDefaultProgressDialog("", true).setDoOnBackground(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                return productsGroupsRepo.getGroups(getApplicationContext(), "", 0, 20);
            }
        }).setDoOnAnswer(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                if (object == null && !(object instanceof ProductGroupAnswer))
                    return null;

                ProductGroupAnswer answer = (ProductGroupAnswer) object;
                if (answer.getIsSuccess() == 1 && answer.getGroups() != null)

                    initRecycleView(answer.getGroups());
                return null;
            }

        });
        getProducsGroupsAsync.run(getApplicationContext());
        return true;
    }

    private void initRecycleView(ArrayList<ProductsGroup> productsGroup) {

        productsGroupArray = new ArrayList<>();
        productsGroupArray = productsGroup;

        RecyclerView row_groups = (RecyclerView) findViewById(R.id.row_groups);
        RecyclerView.LayoutManager rowManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        row_groups.setLayoutManager(rowManager);
        DividerDecoration decoration = new DividerDecoration(ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider_basket_item));
        row_groups.addItemDecoration(decoration);
        groupsAdapter = new ProductsGroupsAdapter();
        if (productsGroupArray != null) {
            groupsAdapter.addItem(productsGroup);
        }

        groupsAdapter.setContext(ProductsGroupsActivity.this);
        row_groups.setAdapter(groupsAdapter);

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_forward_black_24dp);

    }

    private void initFloatActionButton() {
        FabWrapper fabWrapper = new FabWrapper(ProductsGroupsActivity.this,false);
        fabWrapper.initFab(R.id.fab,null);

    }


}
