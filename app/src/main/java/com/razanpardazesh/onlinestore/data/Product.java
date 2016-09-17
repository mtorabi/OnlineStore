package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Torabi on 9/11/2016.
 */

public class Product extends ProductSummary {

    private String description = null;
    private ArrayList<ProductImage> images;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ProductImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ProductImage> images) {
        this.images = images;
    }
}
