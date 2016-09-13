package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.data.ProductsGroup;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/13/2016.
 */

public class ProductGroupAnswer extends ServerAnswer {

    private ArrayList<ProductsGroup> groups;

    public ArrayList<ProductsGroup> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<ProductsGroup> groups) {
        this.groups = groups;
    }
}
