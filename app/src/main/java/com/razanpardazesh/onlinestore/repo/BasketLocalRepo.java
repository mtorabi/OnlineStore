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
        return new ArrayList<BasketItem>();
    }

    @Override
    public Boolean addProduct(Context context, ProductSummary item) {
        return false;
    }

    @Override
    public Boolean removeProduct(Context context, ProductSummary item) {
        return false;
    }

    @Override
    public Boolean clearBasket(Context context) {
        return false;
    }

    @Override
    public BasketStatistics getStatistics(Context context) {
        return new BasketStatistics();
    }


}
