package com.razanpardazesh.onlinestore.repo.IRepo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.BasketItem;
import com.razanpardazesh.onlinestore.data.BasketStatistics;
import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/17/2016.
 */

public interface IBasketItems {

    public ArrayList<BasketItem> getBasketItems(Context context);

    public Boolean addProduct(Context context, ProductSummary item);

    public Boolean removeProduct(Context context, ProductSummary item);

    public Boolean clearBasket(Context context);

    public BasketStatistics getStatistics(Context context);
}
