package com.razanpardazesh.mtglibrary.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

/**
 * Created by Torabi on 9/25/2016.
 */

public class LocationHelper {

    public static final int PLAY_SERVICE_IS_READY = 1;
    public static final int PLAY_SERVICE_IS_MISSING = 0;
    public static final int PLAY_SERVICE_NEED_UPDATE = 2;
    private static final int REQ_CODE_LOCATION_PICKER = 1201;


    /**
     * @param context
     * @return see LocationHelper.PLAY_SERVICE_IS_READY, PLAY_SERVICE_IS_MISSING, PLAY_SERVICE_NEED_UPDATE
     */
    public static int checkGooglePlayServices(Context context) {
        switch (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)) {
            case ConnectionResult.SUCCESS:
                return PLAY_SERVICE_IS_READY;
            case ConnectionResult.SERVICE_MISSING:
                return PLAY_SERVICE_IS_MISSING;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                return PLAY_SERVICE_NEED_UPDATE;
            default:
                return -1;

        }
    }

    /**
     * this method use PlacePicker of google play service
     * @param act
     * @return
     * @throws GooglePlayServicesNotAvailableException
     * @throws GooglePlayServicesRepairableException
     */
    public static Boolean showLocationPicker(FragmentActivity act) throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        if (checkGooglePlayServices(act) != PLAY_SERVICE_IS_READY)
            return false;

        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();



        act.startActivityForResult(intentBuilder.build(act), REQ_CODE_LOCATION_PICKER);
        return true;
    }

    public static Place parseIntentOfLocationPicker_gatPlace(Context context, Intent intent,int requestCode,int resualtCode)
    {
        if (requestCode != REQ_CODE_LOCATION_PICKER || resualtCode != Activity.RESULT_OK)
            return null;

        return PlacePicker.getPlace(context,intent);

    }


}
