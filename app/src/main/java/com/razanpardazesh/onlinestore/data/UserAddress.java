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
 * Created by Torabi on 9/22/2016.
 */

public class UserAddress implements Parcelable,IJson<UserAddress> {

    private final String KEY_ID = "i";
    private final String KEY_PROVINCE = "pr";
    private final String KEY_CITY = "c";
    private final String KEY_ADDRESS = "a";
    private final String KEY_TEL = "t";
    private final String KEY_EMERGENCY_TEL = "e";
    private final String KEY_POSTAL_CODE = "po";
    private final String KEY_TRANSFREE_NAME = "tn";
    private final String KEY_LN = "ln";
    private final String KEY_LT = "lt";

    private long id;
    private Province province;
    private City city;
    private String addressStr;
    private String tel;
    private String emergencyTel;
    private String postalCode;
    private String transfereeName;
    private double ln;
    private double lt;


    public static final Creator<UserAddress> CREATOR = new Creator<UserAddress>() {
        @Override
        public UserAddress createFromParcel(Parcel in) {
            return new UserAddress(in);
        }

        @Override
        public UserAddress[] newArray(int size) {
            return new UserAddress[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmergencyTel() {
        return emergencyTel;
    }

    public void setEmergencyTel(String emergencyTel) {
        this.emergencyTel = emergencyTel;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTransfereeName() {
        return transfereeName;
    }

    public void setTransfereeName(String transfereeName) {
        this.transfereeName = transfereeName;
    }

    public double getLn() {
        return ln;
    }

    public void setLn(double ln) {
        this.ln = ln;
    }

    public double getLt() {
        return lt;
    }

    public void setLt(double lt) {
        this.lt = lt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(addressStr);
        dest.writeString(tel);
        dest.writeString(emergencyTel);
        dest.writeString(postalCode);
        dest.writeString(transfereeName);
        dest.writeDouble(ln);
        dest.writeDouble(lt);
        dest.writeParcelable(province,flags);
        dest.writeParcelable(city,flags);

    }

    public UserAddress(Parcel source) {
        this.id = source.readLong();
        this.addressStr = source.readString();
        this.tel = source.readString();
        this.emergencyTel = source.readString();
        this.postalCode = source.readString();
        this.transfereeName = source.readString();
        this.ln = source.readDouble();
        this.lt = source.readDouble();
        this.province = source.readParcelable(Province.class.getClassLoader());
        this.city = source.readParcelable(City.class.getClassLoader());
    }

    public UserAddress() {
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

        if (jsonObject.has(KEY_ADDRESS)) {
            try {
                setAddressStr(jsonObject.getString(KEY_ADDRESS));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setAddressStr: ", e);
            }
        }

        if (jsonObject.has(KEY_TEL)) {
            try {
                setTel(jsonObject.getString(KEY_TEL));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setTel: ", e);
            }
        }

        if (jsonObject.has(KEY_EMERGENCY_TEL)) {
            try {
                setEmergencyTel(jsonObject.getString(KEY_EMERGENCY_TEL));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setEmergencyTel: ", e);
            }
        }

        if (jsonObject.has(KEY_POSTAL_CODE)) {
            try {
                setPostalCode(jsonObject.getString(KEY_POSTAL_CODE));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setPostalCode: ", e);
            }
        }

        if (jsonObject.has(KEY_TRANSFREE_NAME)) {
            try {
                setTransfereeName(jsonObject.getString(KEY_TRANSFREE_NAME));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setTransfereeName: ", e);
            }
        }

        if (jsonObject.has(KEY_LN)) {
            try {
                setLn(jsonObject.getDouble(KEY_LN));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setLn: ", e);
            }
        }

        if (jsonObject.has(KEY_LT)) {
            try {
                setLt(jsonObject.getDouble(KEY_LT));
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setLt: ", e);
            }
        }

        if (jsonObject.has(KEY_PROVINCE)) {
            try {
                JSONObject obj = jsonObject.getJSONObject(KEY_PROVINCE);
                Province p = new Province();
                p.fillByJson(obj);
                setProvince(p);
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setProvince: ", e);
            }
        }

        if (jsonObject.has(KEY_CITY)) {
            try {
                JSONObject obj = jsonObject.getJSONObject(KEY_CITY);
                City c = new City();
                c.fillByJson(obj);
                setCity(c);
            } catch (JSONException e) {
                LogWrapper.loge("fillByJson: setCity: ", e);
            }
        }
    }

    @Override
    public JSONObject writeJson(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (getCity() != null)
                jsonObject.put(KEY_CITY,getCity().writeJson(context));

            if (getProvince() != null)
                jsonObject.put(KEY_PROVINCE,getProvince().writeJson(context));

            if (getId() != 0)
                jsonObject.put(KEY_ID,getId());

            if (!TextUtils.isEmpty(getAddressStr()))
                jsonObject.put(KEY_ADDRESS,getAddressStr());

            if (!TextUtils.isEmpty(getTel()))
                jsonObject.put(KEY_TEL,getTel());

            if (!TextUtils.isEmpty(getEmergencyTel()))
                jsonObject.put(KEY_EMERGENCY_TEL,getEmergencyTel());

            if (!TextUtils.isEmpty(getPostalCode()))
                jsonObject.put(KEY_POSTAL_CODE,getPostalCode());

            if (!TextUtils.isEmpty(getTransfereeName()))
                jsonObject.put(KEY_TRANSFREE_NAME,getTransfereeName());

            if (getLn() != 0)
                jsonObject.put(KEY_LN,getLn());

            if (getLt() != 0)
                jsonObject.put(KEY_LT,getLt());

        } catch (Exception ex) {
            LogWrapper.loge("UserAddress_writeJson_Exception: ", ex);
        }

        return jsonObject;
    }

    @Override
    public ArrayList<UserAddress> parseList(JSONArray jsonArray) {
        ArrayList<UserAddress> output =  new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                UserAddress add = new UserAddress();
                add.fillByJson(obj);
                output.add(add);

            }
        } catch (Exception ex) {
            LogWrapper.loge("UserAddress_parseList_Exception: ", ex);
        }

        return output;
    }

    @Override
    public JSONArray serializeList(Context context, ArrayList<UserAddress> lstInput) {

        JSONArray output = new JSONArray();

        if (lstInput == null || lstInput.size() ==0)
            return output;


        for (UserAddress address:
        lstInput) {
            output.put(address.writeJson(context));
        }

        return output;
    }

}
