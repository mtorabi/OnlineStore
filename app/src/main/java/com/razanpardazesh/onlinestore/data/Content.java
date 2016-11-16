package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.Interfaces.IJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Home on 10/15/2016.
 */

public class Content implements IJson<Content> {

    private final String KEY_ID = "i";
    private final String KEY_OBJECT_ID = "oi";
    private final String KEY_TEXT = "te";
    private final String KEY_IMAGES = "im";

    private long id = 0l;
    private long objectId = 0l;
    private int objectType= 0;
    private String text = null;
    private ArrayList<ContentImage> images = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<ContentImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ContentImage> images) {
        this.images = images;
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
        if (jsonObject.has(KEY_OBJECT_ID)) {
            try {
                setObjectId(jsonObject.getLong(KEY_OBJECT_ID));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setObjectId: ", e);
            }
        }
        if (jsonObject.has(KEY_TEXT)) {
            try {
                setText(jsonObject.getString(KEY_TEXT));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setText: ", e);
            }
        }
        if (jsonObject.has(KEY_IMAGES)) {
            try {
                JSONArray a =  jsonObject.getJSONArray(KEY_IMAGES);
                if (a != null)
                    setImages(ContentImage.getContentImages(a));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setImages: ", e);
            }
        }

    }

    @Override
    public JSONObject writeJson(Context context) {
        return null;
    }

    @Override
    public ArrayList<Content> parseList(JSONArray jsonArray) {
        return null;
    }

    @Override
    public JSONArray serializeList(Context context, ArrayList<Content> lstInput) {
        return null;
    }
}
