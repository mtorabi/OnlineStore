package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.data.UserAddress;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddressAnswer extends ServerAnswer {

    private UserAddress userAddress;

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
