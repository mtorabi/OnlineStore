package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.data.ProductsGroup;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/13/2016.
 */

public class ProductGroupAnswer extends ServerAnswer {

    private ProductsGroup group;

    public ProductsGroup getGroup() {
        return group;
    }

    public void setGroup(ProductsGroup group) {
        this.group = group;
    }
}
