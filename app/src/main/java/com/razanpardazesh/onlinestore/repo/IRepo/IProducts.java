package com.razanpardazesh.onlinestore.repo.IRepo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public interface IProducts {

    public ArrayList<ProductSummary> getProducts(Context context, String key, long lastIndex, int Count);
    public ArrayList<ProductSummary> getGroupsProducts(Context context, String key, long lastIndex, int Count, long groupID);
    public ArrayList<ProductSummary> getBrandsProducts(Context context, String key, long lastIndex, int Count, long brandID);
    public Product getProduct(Context context,long id);

}
