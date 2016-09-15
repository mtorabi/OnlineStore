package com.razanpardazesh.onlinestore.data;

/**
 * Created by Torabi on 9/15/2016.
 */

public class BasketItem {
    private ProductSummary product;
    private double count;

    public ProductSummary getProduct() {
        return product;
    }

    public void setProduct(ProductSummary product) {
        this.product = product;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}
