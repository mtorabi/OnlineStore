package com.razanpardazesh.onlinestore.data.serverWrapper;

import com.razanpardazesh.onlinestore.data.UserAddress;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddressesAnswer extends ServerAnswer {

    private ArrayList<UserAddress> addresses;

    public ArrayList<UserAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<UserAddress> addresses) {
        this.addresses = addresses;
    }
}
