package com.razanpardazesh.onlinestore.data.Interfaces;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by Torabi on 10/8/2016.
 */

public interface IJson {
    public void fillByJson(JSONObject jsonObject);
    public JSONObject writeJson(Context context);
}
