package com.razanpardazesh.mtglibrary.services.location;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.razanpardazesh.mtglibrary.services.location.data.LocationType;
import com.razanpardazesh.mtglibrary.services.location.data.Point;
import com.razanpardazesh.mtglibrary.tools.LocationHelper;



public class TrackingLocationServices extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Location mLastLocation;
    private Point mLastPoint;
    private GoogleApiClient mGoogleApiClient;

    public TrackingLocationServices() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initGoogleClient();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        } else {
            initGoogleClient();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public void sendNotification(String title, String message) {

    }

    public Boolean checkGooglePlayServices() {
        int temp = LocationHelper.checkGooglePlayServices(getApplicationContext());
        String message = "";
        switch (temp) {
            case LocationHelper.PLAY_SERVICE_IS_READY:
                return true;
            case LocationHelper.PLAY_SERVICE_IS_MISSING:
                message = "لطفا گوگل پلی سرویس را نصب نمایید";
                break;
            case LocationHelper.PLAY_SERVICE_NEED_UPDATE:
                message = "لطفا گوگل پلی سرویس خود را به روز نمایید";
                break;
            default:
                break;

        }
        sendNotification("خطا در سیستم موقعیت یاب", message);
        return false;
    }

    public void initGoogleClient() {

        if (!checkGooglePlayServices()) {
            return;
        }

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(com.google.android.gms.location.LocationServices.API)
                    .build();
        }
    }

    public void updateLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        mLastPoint = new Point();
        mLastPoint.setId(mLastLocation.getTime());
        mLastPoint.setLongitude(mLastLocation.getLongitude());
        mLastPoint.setLatitude(mLastLocation.getLatitude());
        mLastPoint.setSent(false);
        mLastPoint.setType(LocationType.TRACKING);

    }

    /**
     * this is GoogleApiClient.ConnectionCallbacks override
     *
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        updateLastKnownLocation();
    }

    /**
     * this is GoogleApiClient.ConnectionCallbacks override
     *
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {
        scheduleNextPing(1);
    }

    /**
     * this is googleApiClient.OnConnectionFailedListener override
     *
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        scheduleNextPing(1);
    }

    public void scheduleNextPing(int minite)
    {

    }
}
