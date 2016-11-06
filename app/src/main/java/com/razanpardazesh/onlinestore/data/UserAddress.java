package com.razanpardazesh.onlinestore.data;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.razanpardazesh.onlinestore.data.Interfaces.IRealm;
import com.razanpardazesh.onlinestore.data.realmObject.Address;

import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddress implements Parcelable,IRealm<Address> {

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
    public void fillByRealm(Address realmObject) {

        if (realmObject == null)
            return;

        if (realmObject.isValid())
            return;

        this.transfereeName = realmObject.getTransfereeName();
        this.addressStr = realmObject.getAddressStr();

        this.city = new City();
        this.city.setId(realmObject.getCityId());
        this.city.setName(realmObject.getCityName());

        this.province = new Province();
        this.province.setId(realmObject.getProvinceId());
        this.province.setName(realmObject.getProvinceName());

        this.emergencyTel = realmObject.getEmergencyTel();
        this.ln = realmObject.getLn();
        this.lt = realmObject.getLt();

        this.id = realmObject.getId();
        this.postalCode = realmObject.getPostalCode();
        this.tel = realmObject.getTel();
    }

    @Override
    public Address saveInRealm(Context ctx, Realm realm) {

        if (ctx == null || realm == null || realm.isClosed() ) {
            return null;
        }

        Address address = realm.where(Address.class).equalTo("id",this.id).findFirst();

        if (address == null)
        {
            address = realm.createObject(Address.class);

            long lastKey= realm.where(Address.class).max("id").longValue();
            address.setId(lastKey+1);
        }

        address.setAddressStr(this.addressStr);
        address.setTel(this.tel);
        address.setEmergencyTel(this.emergencyTel);
        address.setPostalCode(this.getPostalCode());
        address.setTransfereeName(this.getTransfereeName());
        address.setLn(this.ln);
        address.setLt(this.lt);

        if (this.city != null) {
            address.setCityId(this.city.getId());
            address.setCityName(this.city.getName());
        }

        if (this.province != null)
        {
            address.setProvinceId(this.province.getId());
            address.setProvinceName(this.province.getName());
        }
        return address;
    }

    @Override
    public Boolean deleteFromRealm(Context ctx, Realm realm) {
        if (ctx == null || realm == null || realm.isClosed() ) {
            return false;
        }

        Address address = realm.where(Address.class).equalTo("id",this.id).findFirst();
        if (address == null) {
            return false;
        }

        address.deleteFromRealm();
        return true;
    }
}
