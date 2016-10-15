package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.ProductSummary;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/13/2016.
 */

public class ProductListAnswer extends ServerAnswer {

    private static final String KEY_PRODUCTS = "p";
    private ArrayList<ProductSummary> products;

    public ArrayList<ProductSummary> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductSummary> products) {
        this.products = products;
    }

    @Override
    public void fillByJson(JSONObject jsonObject) {
        super.fillByJson(jsonObject);

        if (jsonObject == null) {
            return;
        }

        if (jsonObject.has(KEY_PRODUCTS)) {
            try {
                JSONArray a = jsonObject.getJSONArray(KEY_PRODUCTS);

                setProducts(ProductSummary.getProductsSummeries(a));
            } catch (Exception e) {
                LogWrapper.loge("fillByJson: setProducts: ", e);
            }

        }
    }
}
