package com.razanpardazesh.onlinestore.data;

import android.content.Context;
import android.util.Log;

import com.razanpardazesh.mtglibrary.CustomView.recycler.IRecyclerRow;
import com.razanpardazesh.mtglibrary.tools.Convertor;
import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.Interfaces.IImage;
import com.razanpardazesh.onlinestore.data.Interfaces.IJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductsGroup implements IImage, IJson,IRecyclerRow {

    private final String KEY_ID = "i";
    private final String KEY_NAME = "t";
    private final String KEY_CREATE_DATE = "cd";
    private final String KEY_SUB_GROUPS = "sg";
    private final String KEY_SUB_PRODUCTS = "sp";

    private Long id;
    private String name;
    private Date createDate;
    private ArrayList<ProductsGroup> subGroups;
    private ArrayList<ProductSummary> subProducts;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ArrayList<ProductsGroup> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(ArrayList<ProductsGroup> subGroups) {
        this.subGroups = subGroups;
    }

    public ArrayList<ProductSummary> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(ArrayList<ProductSummary> subProducts) {
        this.subProducts = subProducts;
    }

    @Override
    public String getThumb(Context context) {
        if (SessionManagement.getInstance(context).getFakeBind())
            return String.valueOf(id);

        return null;
    }

    @Override
    public String getImage(Context context) {
        if (SessionManagement.getInstance(context).getFakeBind())
            return String.valueOf(id);

        return null;
    }

    @Override
    public void fillByJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }

        if (jsonObject.has(KEY_ID)) {
            try {
                setId(jsonObject.getLong(KEY_ID));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setId: ", e);
            }
        }
        if (jsonObject.has(KEY_NAME)) {
            try {
                setName(jsonObject.getString(KEY_NAME));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setName: ", e);
            }
        }
        if (jsonObject.has(KEY_CREATE_DATE)) {
            try {
                setCreateDate(Convertor.toDate(jsonObject.getString(KEY_CREATE_DATE)));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setCreateDate: ", e);
            }
        }

        if (jsonObject.has(KEY_SUB_GROUPS)) {
            try {
                JSONArray a =  jsonObject.getJSONArray(KEY_SUB_GROUPS);
                if (a != null)
                 setSubGroups(getProductsGroups(a));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setCreateDate: ", e);
            }
        }

        if (jsonObject.has(KEY_SUB_PRODUCTS)) {
            try {
                JSONArray a =  jsonObject.getJSONArray(KEY_SUB_PRODUCTS);
                if (a != null)
                    setSubProducts(ProductSummary.getProductsSummeries(a));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setCreateDate: ", e);
            }
        }

    }

    @Override
    public JSONObject writeJson(Context context) {
        return null;
    }


    public static ArrayList<ProductsGroup> getProductsGroups(JSONArray jsonArray) {
        if (jsonArray == null)
            return null;

        ArrayList<ProductsGroup> output = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProductsGroup group = new ProductsGroup();
                group.fillByJson(jsonObject);
                output.add(group);
            }
        } catch (JSONException e) {
            LogWrapper.loge("getProductsGroups: ", e);
        }

        return output;
    }

    @Override
    public Object getRowId() {
        return getId();
    }
}
