package com.razanpardazesh.onlinestore.data;

import android.content.Context;

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
 * Created by Home on 10/15/2016.
 */

public class ContentImage implements IJson , IImage {

    private final String KEY_ID = "i";
    private final String KEY_CONTENT_ID = "ci";
    private final String KEY_CREATE_DATE = "cd";

    private long id = 0l;
    private long contentId = 0l;
    private Date createDate = null;

    public ContentImage(long id) {
        this.id = id;
    }

    public ContentImage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        if (jsonObject.has(KEY_CONTENT_ID)) {
            try {
                setContentId(jsonObject.getLong(KEY_CONTENT_ID));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setContentId: ", e);
            }
        }
        if (jsonObject.has(KEY_CREATE_DATE)) {
            try {
                setCreateDate(Convertor.toDate(jsonObject.getString(KEY_CREATE_DATE)));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setCreateDate: ", e);
            }
        }
    }

    @Override
    public JSONObject writeJson(Context context) {
        return null;
    }

    public static ArrayList<ContentImage> getContentImages(JSONArray jsonArray)
    {
        if (jsonArray == null)
            return null;

        ArrayList<ContentImage> output = new ArrayList<>();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ContentImage img = new ContentImage();
                img.fillByJson(jsonObject);
                output.add(img);
            }
        } catch (JSONException e) {
            LogWrapper.loge("getProductsGroups: ", e);
        }

        return output;
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
}
