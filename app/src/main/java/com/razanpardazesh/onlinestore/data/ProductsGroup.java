package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import com.razanpardazesh.mtglibrary.tools.Convertor;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.Interfaces.IImage;
import com.razanpardazesh.onlinestore.data.Interfaces.IJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductsGroup implements IImage, IJson {

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
            }
        }
        if (jsonObject.has(KEY_NAME)) {
            try {
                setName(jsonObject.getString(KEY_NAME));
            } catch (JSONException e) {
            }
        }if (jsonObject.has(KEY_CREATE_DATE)) {
            try {
                setCreateDate(Convertor.toDate(jsonObject.getString(KEY_CREATE_DATE)));
            } catch (JSONException e) {
            }
        }
    }

    @Override
    public JSONObject writeJson(Context context) {
        return null;
    }
}
