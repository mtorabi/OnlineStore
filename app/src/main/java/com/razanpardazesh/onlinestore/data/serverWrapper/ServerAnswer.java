package com.razanpardazesh.onlinestore.data.serverWrapper;

import android.content.Context;
import android.text.TextUtils;

import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.Interfaces.IJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/13/2016.
 */

public abstract class ServerAnswer implements IJson<ServerAnswer> {

    private final String KEY_IS_SUCCESS="issu";
    private final String KEY_HAS_MORE="hasm";
    private final String KEY_LAST_INDEX="lsti";
    private final String KEY_MESSAGE="msg";

    private int isSuccess = 0;
    private int hasMore = 0;
    private long lastIndex = 0;
    private String message = null;


    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public long getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(long lastIndex) {
        this.lastIndex = lastIndex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Boolean isSuccess(Object answer) {
        if (answer == null)
            return false;

        if (!(answer instanceof ServerAnswer))
            return false;

        ServerAnswer obj = (ServerAnswer) answer;

        if (obj.getIsSuccess() != 1)
            return false;

        return true;

    }

    public static Boolean hasMore(Object answer) {
        if (answer == null)
            return false;

        if (!(answer instanceof ServerAnswer))
            return false;

        ServerAnswer obj = (ServerAnswer) answer;

        if (obj.getHasMore() != 1)
            return false;

        return true;

    }

    @Override
    public void fillByJson(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }

        if (jsonObject.has(KEY_IS_SUCCESS)) {
            try {
                setIsSuccess(jsonObject.getInt(KEY_IS_SUCCESS));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setIsSuccess",e);
            }
        }
        if (jsonObject.has(KEY_HAS_MORE)) {
            try {
                setHasMore(jsonObject.getInt(KEY_HAS_MORE));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setHasMore",e);
            }
        }
        if (jsonObject.has(KEY_MESSAGE)) {
            try {
                setMessage(jsonObject.getString(KEY_MESSAGE));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setMessage",e);
            }
        }
        if (jsonObject.has(KEY_LAST_INDEX)) {
            try {
                setLastIndex(jsonObject.getLong(KEY_LAST_INDEX));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setLastIndex",e);
            }
        }

    }

    @Override
    public JSONObject writeJson(Context context) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put(KEY_IS_SUCCESS,getIsSuccess());
            jsonObject.put(KEY_HAS_MORE,getHasMore());
            jsonObject.put(KEY_MESSAGE,getMessage());
            jsonObject.put(KEY_LAST_INDEX,getLastIndex());

        } catch (JSONException e) {
        }

        return jsonObject;
    }

    @Override
    public ArrayList<ServerAnswer> parseList(JSONArray jsonArray) {
        return null;
    }

    @Override
    public JSONArray serializeList(Context context, ArrayList<ServerAnswer> lstInput) {
        return null;
    }
}
