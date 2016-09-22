package com.razanpardazesh.onlinestore.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddress implements Parcelable {

    private long id;
    private Province province;
    private City city;
    private String address;
    private String tel;
    private String emergencyTel;
    private String postalCode;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(address);
        dest.writeString(tel);
        dest.writeString(emergencyTel);
        dest.writeString(postalCode);
    }

    public UserAddress(Parcel source) {
        this.id = source.readLong();
        this.address = source.readString();
        this.tel = source.readString();
        this.emergencyTel = source.readString();
        this.postalCode = source.readString();
    }

    public UserAddress() {
    }

    public static Creator<UserAddress> CREATOR = new Creator<UserAddress>() {
        @Override
        public UserAddress createFromParcel(Parcel source) {
            return new UserAddress(source);
        }

        @Override
        public UserAddress[] newArray(int size) {
            return new UserAddress[size];
        }
    };
}
