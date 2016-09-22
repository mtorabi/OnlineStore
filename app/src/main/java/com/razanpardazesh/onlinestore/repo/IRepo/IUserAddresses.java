package com.razanpardazesh.onlinestore.repo.IRepo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.UserAddress;
import com.razanpardazesh.onlinestore.data.serverWrapper.ServerAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressesAnswer;

/**
 * Created by Torabi on 9/22/2016.
 */

public interface IUserAddresses {

    public UserAddressesAnswer getUserAddress(Context context);
    public UserAddressAnswer addNewUserAddress(Context context, UserAddress newAddress);
    public UserAddressAnswer deleteUserAddress(Context context, UserAddress address);
    public UserAddressAnswer updateUserAddress(Context context,UserAddress address);
    public UserAddressAnswer getLastUserAddress(Context context);

}
