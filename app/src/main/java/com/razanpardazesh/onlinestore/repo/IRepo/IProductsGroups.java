package com.razanpardazesh.onlinestore.repo.IRepo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.ProductsGroup;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public interface IProductsGroups {

    public ArrayList<ProductsGroup> getGroups(Context context, String key, long startIndex, int count);

}
