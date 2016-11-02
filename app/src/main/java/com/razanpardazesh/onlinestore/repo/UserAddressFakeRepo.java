package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.City;
import com.razanpardazesh.onlinestore.data.Province;
import com.razanpardazesh.onlinestore.data.UserAddress;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressesAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IUserAddresses;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddressFakeRepo implements IUserAddresses {

    static UserAddress address;

    static {
        initFakeAddress();
    }

    private static void initFakeAddress()
    {
        address = new UserAddress();
        address.setAddressStr("گیلان، رشت، خیابان قلی پور، ساختمان دی، ط 4");

        Province gilan = new Province();
        gilan.setId(13);
        gilan.setName("گیلان");
        address.setProvince(gilan);

        City rasht = new City();
        rasht.setId(13);
        rasht.setName("رشت");
        address.setCity(rasht);

        address.setId(1);
        address.setTel("09358093588");
        address.setEmergencyTel("01333546473");
        address.setPostalCode("123456789");
    }

    @Override
    public UserAddressesAnswer getUserAddress(Context context) {
        UserAddressesAnswer userAddressesAnswer = new UserAddressesAnswer();
        userAddressesAnswer.setIsSuccess(1);
        userAddressesAnswer.setHasMore(0);
        ArrayList<UserAddress> addresses = new ArrayList<>();
        addresses.add(address);
        userAddressesAnswer.setAddresses(addresses);
        return userAddressesAnswer;
    }

    @Override
    public UserAddressAnswer addNewUserAddress(Context context,final UserAddress newAddress) {
        UserAddressAnswer answer = new UserAddressAnswer();
        answer.setIsSuccess(1);
        answer.setHasMore(0);
        answer.setUserAddress(newAddress);
        address = newAddress;
        return answer;
    }

    @Override
    public UserAddressAnswer deleteUserAddress(Context context, UserAddress address) {
        UserAddressAnswer answer = new UserAddressAnswer();
        answer.setIsSuccess(1);
        answer.setHasMore(0);
        answer.setUserAddress(null);
        address = null;
        return answer;
    }

    @Override
    public UserAddressAnswer updateUserAddress(Context context,final UserAddress updatedAddress) {
        UserAddressAnswer answer = new UserAddressAnswer();
        answer.setIsSuccess(1);
        answer.setHasMore(0);
        answer.setUserAddress(updatedAddress);
        address = updatedAddress;
        return answer;
    }

    @Override
    public UserAddressAnswer getLastUserAddress(Context context) {
        UserAddressAnswer answer = new UserAddressAnswer();
        answer.setIsSuccess(1);
        answer.setHasMore(0);
        answer.setUserAddress(address);
        return answer;
    }
}
