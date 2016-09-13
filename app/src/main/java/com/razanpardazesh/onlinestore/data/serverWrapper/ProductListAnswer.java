package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/13/2016.
 */

public class ProductListAnswer extends ServerAnswer {

    private ArrayList<ProductSummary> products;

    public ArrayList<ProductSummary> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductSummary> products) {
        this.products = products;
    }
}
