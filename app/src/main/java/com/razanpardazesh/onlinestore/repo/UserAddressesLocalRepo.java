package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.UserAddress;
import com.razanpardazesh.onlinestore.data.realmObject.Address;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressesAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IUserAddresses;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddressesLocalRepo implements IUserAddresses {


    @Override
    public UserAddressesAnswer getUserAddress(Context context) {
        Realm realm = null;
        try  {
            realm = Realm.getDefaultInstance();
            RealmResults<Address> result = realm.where(Address.class).findAll();

            RealmList<Address> list = new RealmList<>();
            list.addAll(result);

            ArrayList<UserAddress> userAddress = new ArrayList<>();

            for (Address address:
                 list) {

            }

        }
        catch(Exception ex)
        {
            LogWrapper.loge("UserAddressesLocalRepo_getUserAddress_Exception: ",ex);
        }
        finally {
            if (realm != null && !realm.isClosed())
                realm.close();
        }






        return null;
    }

    @Override
    public UserAddressAnswer addNewUserAddress(Context context, UserAddress newAddress) {
        return null;
    }

    @Override
    public UserAddressAnswer deleteUserAddress(Context context, UserAddress address) {
        return null;
    }

    @Override
    public UserAddressAnswer updateUserAddress(Context context, UserAddress address) {
        return null;
    }

    @Override
    public UserAddressAnswer getLastUserAddress(Context context) {
        return null;
    }
}
