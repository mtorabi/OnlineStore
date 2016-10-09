package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.Interfaces.IJson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Torabi on 9/11/2016.
 */

public class Product extends ProductSummary implements IJson{

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

    @Override
    public void fillByJson(JSONObject jsonObject) {

    }

    @Override
    public JSONObject writeJson(Context context) {
        return null;
    }

    public static ArrayList<Product> getProducts(JSONArray a)
    {
        return null;
    }
}
