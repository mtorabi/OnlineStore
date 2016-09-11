package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.repo.IRepo.IProducts;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductFakeRepo implements IProducts {
    @Override
    public ArrayList<ProductSummary> getProducts(Context context, String key, long lastIndex, int Count) {
        return null;
    }

    @Override
    public ArrayList<ProductSummary> getGroupsProducts(Context context, String key, long lastIndex, int Count, long groupID) {
        return null;
    }

    @Override
    public ArrayList<ProductSummary> getBrandsProducts(Context context, String key, long lastIndex, int Count, long brandID) {
        return null;
    }

    @Override
    public Product getProduct(Context context, long id) {
        return null;
    }
}
