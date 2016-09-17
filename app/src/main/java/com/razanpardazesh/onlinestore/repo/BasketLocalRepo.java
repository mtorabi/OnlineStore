package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.BasketItem;
import com.razanpardazesh.onlinestore.data.BasketStatistics;
import com.razanpardazesh.onlinestore.data.ProductSummary;
import com.razanpardazesh.onlinestore.repo.IRepo.IBasketItems;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/17/2016.
 */

public class BasketLocalRepo implements IBasketItems {
    @Override
    public ArrayList<BasketItem> getBasketItems(Context context) {
        return null;
    }

    @Override
    public Boolean addProduct(Context context, ProductSummary item) {
        return null;
    }

    @Override
    public Boolean removeProduct(Context context, ProductSummary item) {
        return null;
    }

    @Override
    public Boolean clearBasket(Context context) {
        return null;
    }

    @Override
    public BasketStatistics getStatistics(Context context) {
        return null;
    }


}
