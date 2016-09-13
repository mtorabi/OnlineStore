package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.data.Product;

/**
 * Created by Torabi on 9/13/2016.
 */

public class ProductAnswer extends ServerAnswer {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
