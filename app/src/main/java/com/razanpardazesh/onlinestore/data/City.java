package com.razanpardazesh.onlinestore.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Torabi on 9/17/2016.
 */

public class City implements Parcelable {

    private long id;
    private String name;

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

    public City() {
    }

    public City(Parcel in) {
        id= in.readLong();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
    }

    public static Creator<City> CREATOR =new Creator<City>() {
        @Override
        public City createFromParcel(Parcel parcel) {
            return new City(parcel);
        }

        @Override
        public City[] newArray(int i) {
            return new City[i];
        }
    };
}
