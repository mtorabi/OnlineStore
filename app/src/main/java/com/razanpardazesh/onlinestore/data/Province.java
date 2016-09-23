package com.razanpardazesh.onlinestore.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Torabi on 9/17/2016.
 */

public class Province implements Parcelable {

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

    public Province() {
    }

    public Province(Parcel in) {
        id = in.readLong();
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



    public static Creator<Province> CREATOR = new Creator<Province>() {
        @Override
        public Province createFromParcel(Parcel parcel) {
            return new Province(parcel);
        }

        @Override
        public Province[] newArray(int i) {
            return new Province[i];
        }
    };
}
