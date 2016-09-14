package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import java.util.Date;

/**
 * Created by Torabi on 9/11/2016.
 */

public class Product extends ProductSummary {

    private String description = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
