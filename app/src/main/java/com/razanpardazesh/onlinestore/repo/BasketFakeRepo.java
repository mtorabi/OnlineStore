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

public class BasketFakeRepo implements IBasketItems {

    private static ArrayList<BasketItem> basket = new ArrayList<>();

    @Override
    public ArrayList<BasketItem> getBasketItems(Context context) {
        return basket;
    }

    @Override
    public Boolean addProduct(Context context, ProductSummary item) {
        if (item == null)
            return false;

        for (int i = 0; i < basket.size(); i++) {
            if (item.getId() == basket.get(i).getProduct().getId()) {
                basket.get(i).setCount(basket.get(i).getCount() + 1);
                return true;
            }
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setCount(1);
        basketItem.setProduct(item);

        return basket.add(basketItem);
    }

    @Override
    public Boolean removeProduct(Context context, ProductSummary item) {
        if (item == null || basket == null || basket.size() == 0)
            return false;

        for (int i = 0; i < basket.size(); i++) {
            if (item.getId() == basket.get(i).getProduct().getId()) {
                if (basket.get(i).getCount() > 1) {
                    basket.get(i).setCount(basket.get(i).getCount() - 1);
                } else {
                    basket.remove(i);
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean clearBasket(Context context) {
        if (basket == null || basket.size() == 0) {
            basket = new ArrayList<>();
            return true;
        }
        basket.clear();
        return true;
    }

    @Override
    public BasketStatistics getStatistics(Context context) {
        if (basket == null)
            return new BasketStatistics();

        int size = basket.size();
        if (size == 0)
            return new BasketStatistics();

        BasketStatistics stattistic = new BasketStatistics();
        for (int i = 0; i < size; i++) {
            stattistic.setTotalCounts(stattistic.getTotalCounts() + basket.get(i).getCount());
            stattistic.setTotalPrices(stattistic.getTotalPrices()
                    + (basket.get(i).getProduct().getPrice() * basket.get(i).getCount()));

        }
        return stattistic;
    }

}
