package com.razanpardazesh.onlinestore;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsFakeRepo;
import com.razanpardazesh.onlinestore.repo.ProductsGroupsServerRepo;

import java.util.ArrayList;

public class ProductsGroupActivity extends AppCompatActivity {

    IProductsGroups groupRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_group);


        if (SessionManagement.getInstance(getApplicationContext()).getFakeBind())
            groupRepo = new ProductsGroupsFakeRepo();
        else
            groupRepo = new ProductsGroupsServerRepo();

    }




}
