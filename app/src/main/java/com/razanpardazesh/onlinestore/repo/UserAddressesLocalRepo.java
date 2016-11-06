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
import io.realm.Sort;

/**
 * Created by Torabi on 9/22/2016.
 */

public class UserAddressesLocalRepo implements IUserAddresses {


    @Override
    public UserAddressesAnswer getUserAddress(Context context) {

        Realm realm = null;
        UserAddressesAnswer answer = new UserAddressesAnswer();

        try  {
            realm = Realm.getDefaultInstance();
            RealmResults<Address> result = realm.where(Address.class).findAll();

            RealmList<Address> list = new RealmList<>();
            list.addAll(result);

            ArrayList<UserAddress> userAddress = new ArrayList<>();

            for (Address address:
                    list) {
                UserAddress addr = new UserAddress();
                addr.fillByRealm(address);
                userAddress.add(addr);
            }
            answer.setAddresses(userAddress);
            answer.setIsSuccess(1);

        }
        catch(Exception ex)
        {
            LogWrapper.loge("UserAddressesLocalRepo_getUserAddress_Exception: ",ex);
            answer.setIsSuccess(0);
            answer.setMessage(ex.toString());
        }
        finally {
            if (realm != null && !realm.isClosed())
                realm.close();
        }

        return answer;
    }

    @Override
    public UserAddressAnswer addNewUserAddress(Context context, UserAddress newAddress) {
       return updateUserAddress(context,newAddress);
    }

    @Override
    public UserAddressAnswer deleteUserAddress(Context context, UserAddress address) {
        Realm realm = null;
        UserAddressAnswer answer = new UserAddressAnswer();
        try  {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            boolean temp=  address.deleteFromRealm(context,realm);
            realm.commitTransaction();
            if (temp)
                answer.setIsSuccess(1);
            else
                answer.setIsSuccess(0);
            return answer;
        }
        catch(Exception ex)
        {
            if (realm != null && realm.isInTransaction())
                realm.cancelTransaction();
            LogWrapper.loge("UserAddressesLocalRepo_deleteUserAddress_Exception:", ex);
            answer.setIsSuccess(0);
            answer.setMessage(ex.toString());
        }
        finally {
            if (realm != null && !realm.isClosed())
                realm.close();
        }

        return answer;
    }

    @Override
    public UserAddressAnswer updateUserAddress( Context context, UserAddress address) {
        Realm realm = null;
        UserAddressAnswer answer = new UserAddressAnswer();
        try  {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            Address adr = address.saveInRealm(context,realm);

            realm.commitTransaction();

            if (adr != null)
                answer.setIsSuccess(1);
            else
                answer.setIsSuccess(0);

            return answer;
        }
        catch(Exception ex)
        {
            if (realm != null && realm.isInTransaction())
                realm.cancelTransaction();
            LogWrapper.loge("UserAddressesLocalRepo_updateUserAddress_Exception: ",ex);
            answer.setIsSuccess(0);
            answer.setMessage(ex.toString());
        }
        finally {
            if (realm != null && !realm.isClosed())
                realm.close();
        }

        return answer;
    }

    @Override
    public UserAddressAnswer getLastUserAddress(Context context) {
        Realm realm = null;
        UserAddressAnswer answer = new UserAddressAnswer();
        try  {
            realm = Realm.getDefaultInstance();
            Address address =realm.where(Address.class).equalTo("id", realm.where(Address.class).max("id").longValue()).findFirst();


            UserAddress userAddress = new UserAddress();

            userAddress.fillByRealm(address);
            answer.setUserAddress(userAddress);
            answer.setIsSuccess(1);


        }
        catch(Exception ex)
        {
            LogWrapper.loge("UserAddressesLocalRepo_getLastUserAddress: ",ex);
            answer.setIsSuccess(0);
            answer.setMessage(ex.toString());
        }
        finally {
            if (realm != null && !realm.isClosed())
                realm.close();
        }

        return answer;
    }
}
