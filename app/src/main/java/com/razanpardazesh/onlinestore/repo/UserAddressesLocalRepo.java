package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.UserAddress;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressesAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IUserAddresses;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddressesLocalRepo implements IUserAddresses {
    @Override
    public UserAddressesAnswer getUserAddress(Context context) {
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
