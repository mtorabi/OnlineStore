package com.razanpardazesh.mtglibrary.services.location.data;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Home on 9/21/2016.
 */

public class Point extends RealmObject{

    private long id;
    private double latitude;
    private double longitude;
    private int type;
    private boolean sent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

}
