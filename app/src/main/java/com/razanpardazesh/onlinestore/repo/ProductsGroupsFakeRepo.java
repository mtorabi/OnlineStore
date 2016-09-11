package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductsGroupsFakeRepo implements IProductsGroups {
    @Override
    public ArrayList<ProductsGroup> getGroups(Context context, String key, long startIndex, int count) {
        ArrayList<ProductsGroup> output = new ArrayList<>();

        return output;
    }
}
