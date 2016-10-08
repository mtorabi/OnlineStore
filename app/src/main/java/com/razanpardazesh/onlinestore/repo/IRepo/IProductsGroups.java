package com.razanpardazesh.onlinestore.repo.IRepo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/11/2016.
 */

public interface IProductsGroups {

    public ProductGroupAnswer getGroups(Context context,long groupId, String key, long startIndex, int count);

}
