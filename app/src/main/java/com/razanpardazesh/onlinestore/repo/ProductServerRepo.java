package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductListAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProducts;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductServerRepo implements IProducts {
    @Override
    public ProductListAnswer getProducts(Context context, String key, long lastIndex, int Count) {
        return null;
    }

    @Override
    public ProductListAnswer getGroupsProducts(Context context, String key, long lastIndex, int Count, long groupID) {
        return null;
    }

    @Override
    public ProductListAnswer getBrandsProducts(Context context, String key, long lastIndex, int Count, long brandID) {
        return null;
    }

    @Override
    public ProductListAnswer getMostVistedProducts(Context context, String key, long lastIndex, int Count) {
        return null;
    }

    @Override
    public ProductListAnswer getMostSoldProducts(Context context, String key, long lastIndex, int Count) {
        return null;
    }

    @Override
    public ProductAnswer getProduct(Context context, long id) {
        return null;
    }
}
