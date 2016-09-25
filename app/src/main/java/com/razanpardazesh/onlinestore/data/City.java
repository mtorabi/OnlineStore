package com.razanpardazesh.onlinestore.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Torabi on 9/17/2016.
 */

public class City  implements Parcelable {

    private long id;
    private String name;

    protected City(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

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

    public City() {

    }
}
