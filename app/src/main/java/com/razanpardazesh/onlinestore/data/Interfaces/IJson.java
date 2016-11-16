package com.razanpardazesh.onlinestore.data.Interfaces;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Torabi on 10/8/2016.
 */

public interface IJson<T> {
    public void fillByJson(JSONObject jsonObject);

    public JSONObject writeJson(Context context);

    public ArrayList<T> parseList(JSONArray jsonArray);

    public JSONArray serializeList(Context context, ArrayList<T> lstInput);

}
