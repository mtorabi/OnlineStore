package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.data.ProductsGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/13/2016.
 */

public class ProductGroupAnswer extends ServerAnswer {

    private final String KEY_GROUP = "g";

    private ProductsGroup group;

    public ProductsGroup getGroup() {
        return group;
    }

    public void setGroup(ProductsGroup group) {
        this.group = group;
    }


    @Override
    public void fillByJson(JSONObject jsonObject) {
        super.fillByJson(jsonObject);

        if (jsonObject == null) {
            return;
        }

        if (jsonObject.has(KEY_GROUP)) {
            ProductsGroup group = new ProductsGroup();
            group.fillByJson(jsonObject);
            setGroup(group);
        }

    }
}
