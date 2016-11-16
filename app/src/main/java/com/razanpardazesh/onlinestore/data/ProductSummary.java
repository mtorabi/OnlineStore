package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import com.razanpardazesh.mtglibrary.CustomView.recycler.IRecyclerRow;
import com.razanpardazesh.mtglibrary.tools.Convertor;
import com.razanpardazesh.onlinestore.R;
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

public class ProductSummary implements IImage, IJson<ProductSummary>,IRecyclerRow {

    private final String KEY_ID = "i";
    private final String KEY_NAME = "t";
    private final String KEY_DESCRIPTION = "de";
    private final String KEY_PRICE = "p";
    private final String KEY_DISCOUNT = "di";
    private final String KEY_CREATE_DATE = "c";
    private final String KEY_GROUP = "g";
    private final String KEY_CONTENT = "con";

    private long id = -1;
    private String name = null;
    private String description = null;
    private Date createDtae = null;
    private double price = -1d;
    private double discount = -1d;
    private ProductsGroup group = null;
    private Content content = null;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDtae() {
        return createDtae;
    }

    public void setCreateDtae(Date createDtae) {
        this.createDtae = createDtae;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public ProductsGroup getGroup() {
        return group;
    }

    public void setGroup(ProductsGroup group) {
        this.group = group;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
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
        if (jsonObject.has(KEY_DESCRIPTION)) {
            try {
                setDescription(jsonObject.getString(KEY_DESCRIPTION));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setDescription: ", e);
            }
        }
        if (jsonObject.has(KEY_CREATE_DATE)) {
            try {
                setCreateDtae(Convertor.toDate(jsonObject.getString(KEY_CREATE_DATE)));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setCreateDate: ", e);
            }
        }

        if (jsonObject.has(KEY_PRICE)) {
            try {
                setPrice(jsonObject.getDouble(KEY_PRICE));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setPrice: ", e);
            }
        }
        if (jsonObject.has(KEY_DISCOUNT)) {
            try {
                setDiscount(jsonObject.getDouble(KEY_DISCOUNT));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setDiscount: ", e);
            }
        }
        if (jsonObject.has(KEY_GROUP)) {
            try {
                ProductsGroup group = new ProductsGroup();
                group.fillByJson(jsonObject.getJSONObject(KEY_GROUP));
                setGroup(group);
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setGroup: ", e);
            }
        }
        if (jsonObject.has(KEY_CONTENT)) {
            try {
                Content content = new Content();
                content.fillByJson(jsonObject.getJSONObject(KEY_CONTENT));
                setContent(content);
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setContent: ", e);
            }
        }
    }

    @Override
    public JSONObject writeJson(Context context) {
        return null;
    }

    @Override
    public ArrayList<ProductSummary> parseList(JSONArray jsonArray) {
        return null;
    }

    @Override
    public JSONArray serializeList(Context context, ArrayList<ProductSummary> lstInput) {
        return null;
    }

    public static ArrayList<ProductSummary> getProductsSummeries(JSONArray jsonArray) {
        if (jsonArray == null)
            return null;

        ArrayList<ProductSummary> output = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ProductSummary p = new ProductSummary();
                p.fillByJson(jsonObject);
                output.add(p);
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
