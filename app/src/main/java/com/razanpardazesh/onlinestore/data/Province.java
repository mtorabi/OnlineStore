package com.razanpardazesh.onlinestore.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.Interfaces.IJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/17/2016.
 */

public class Province implements Parcelable,IJson<Province> {

    private final String KEY_ID = "i";
    private final String KEY_NAME = "n";

    private long id;
    private String name;

    protected Province(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }

    public static final Creator<Province> CREATOR = new Creator<Province>() {
        @Override
        public Province createFromParcel(Parcel in) {
            return new Province(in);
        }

        @Override
        public Province[] newArray(int size) {
            return new Province[size];
        }
    };

    public Province() {

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
    }

    @Override
    public JSONObject writeJson(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {

            if (getId() != 0)
                jsonObject.put(KEY_ID,getId());

            if (!TextUtils.isEmpty(getName()))
                jsonObject.put(KEY_NAME,getName());

        } catch (Exception ex) {
            LogWrapper.loge("Province_writeJson_Exception: ", ex);
        }

        return jsonObject;
    }

    @Override
    public ArrayList<Province> parseList(JSONArray jsonArray) {
        ArrayList<Province> output =  new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Province add = new Province();
                add.fillByJson(obj);
                output.add(add);

            }
        } catch (Exception ex) {
            LogWrapper.loge("Province_parseList_Exception: ", ex);
        }

        return output;
    }

    @Override
    public JSONArray serializeList(Context context, ArrayList<Province> lstInput) {
        JSONArray output = new JSONArray();

        if (lstInput == null || lstInput.size() ==0)
            return output;


        for (Province add:
                lstInput) {
            output.put(add.writeJson(context));
        }

        return output;
    }
}
