package com.razanpardazesh.onlinestore.repo;

import android.content.Context;
import android.content.SharedPreferences;

import com.razanpardazesh.onlinestore.AddressActivity;
import com.razanpardazesh.onlinestore.BuildConfig;
import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.UserAddress;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressesAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IUserAddresses;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddressesLocalRepo implements IUserAddresses {

    private final String KEY_PREFERENES_NAME = "OnlineStore_Address";
    private static final String KEY_USER_ADDRESS = "USER_ADDRESS";

    private SharedPreferences pref;

    private void initPreferences(Context context) {
        this.pref = context.getSharedPreferences(KEY_PREFERENES_NAME + BuildConfig.FLAVOR, Context.MODE_PRIVATE);
    }

    public UserAddressesLocalRepo(Context context) {
        initPreferences(context);
    }

    @Override
    public UserAddressesAnswer getUserAddress(Context context) {
        if (this.pref == null)
            initPreferences(context);

        UserAddressesAnswer addressesAnswer = new UserAddressesAnswer();
        try {
            String temp = this.pref.getString(KEY_USER_ADDRESS, null);
            ArrayList<UserAddress> addresses = new ArrayList<>();
            JSONArray obj = new JSONArray(temp);
            addresses = new UserAddress().parseList(obj);
            addressesAnswer.setAddresses(addresses);
            addressesAnswer.setIsSuccess(1);
        } catch (Exception ex) {
            LogWrapper.loge("UserAddressesLocalRepo_getUserAddress_Exception: ", ex);
            addressesAnswer.setIsSuccess(0);
            addressesAnswer.setMessage(ex.toString());
        }


        return addressesAnswer;
    }

    @Override
    public UserAddressAnswer addNewUserAddress(Context context, UserAddress newAddress) {
        return updateUserAddress(context, newAddress);
    }

    @Override
    public UserAddressAnswer deleteUserAddress(Context context, UserAddress address) {
        if (this.pref == null)
            initPreferences(context);
        UserAddressAnswer answer = new UserAddressAnswer();
        Boolean isSucc = false;
        try {
            isSucc = this.pref.edit().clear().commit();
        } catch (Exception ex) {
            LogWrapper.loge("UserAddressesLocalRepo_deleteUserAddress_Exception: ", ex);
            answer.setMessage(ex.toString());
        }


        answer.setIsSuccess(isSucc ? 1 : 0);
        answer.setUserAddress(address);

        return answer;
    }

    @Override
    public UserAddressAnswer updateUserAddress(Context context, UserAddress address) {
        if (this.pref == null)
            initPreferences(context);

        UserAddressAnswer answer = new UserAddressAnswer();
        answer.setUserAddress(address);

        ArrayList<UserAddress> arr = new ArrayList<>();
        arr.add(address);

        Boolean isSucc = false;

        try {
            JSONArray jsonArray = new UserAddress().serializeList(context, arr);
            if (jsonArray != null)
                isSucc = this.pref.edit().putString(KEY_USER_ADDRESS, jsonArray.toString()).commit();
            else
                answer.setMessage("UserAddressesLocalRepo_updateUserAddress_Exception: cant serialize List");
        } catch (Exception ex) {
            LogWrapper.loge("UserAddressesLocalRepo_updateUserAddress_Exception: ", ex);
            answer.setMessage(ex.toString());
        }

        answer.setIsSuccess(isSucc ? 1 : 0);

        return answer;
    }

    @Override
    public UserAddressAnswer getLastUserAddress(Context context) {
        UserAddressAnswer answer = new UserAddressAnswer();

        UserAddressesAnswer lstAnswer = getUserAddress(context);

        if (lstAnswer == null || lstAnswer.getAddresses() == null || lstAnswer.getAddresses().size() == 0)
        {
            answer.setIsSuccess(1);
            return answer;
        }

        answer.setUserAddress(lstAnswer.getAddresses().get(0));
        answer.setIsSuccess(1);
        return answer;
    }
}
