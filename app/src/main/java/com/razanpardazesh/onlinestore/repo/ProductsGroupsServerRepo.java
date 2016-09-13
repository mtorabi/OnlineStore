package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductsGroupsServerRepo implements IProductsGroups {

    @Override
    public ProductGroupAnswer getGroups(Context context, String key, long startIndex, int count) {
        return null;
    }
}
