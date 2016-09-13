package com.razanpardazesh.onlinestore.repo.IRepo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductListAnswer;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public interface IProducts {

    public ProductListAnswer getProducts(Context context, String key, long lastIndex, int Count);
    public ProductListAnswer getGroupsProducts(Context context, String key, long lastIndex, int Count, long groupID);
    public ProductListAnswer getBrandsProducts(Context context, String key, long lastIndex, int Count, long brandID);
    public ProductListAnswer getMostVistedProducts(Context context, String key, long lastIndex, int Count);
    public ProductListAnswer getMostSoldProducts(Context context, String key, long lastIndex, int Count);
    public ProductAnswer getProduct(Context context, long id);


}
