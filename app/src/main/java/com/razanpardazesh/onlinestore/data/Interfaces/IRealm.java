package com.razanpardazesh.onlinestore.data.Interfaces;

import android.content.Context;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by MTorabi on 11/2/2016.
 */

public interface IRealm<T extends RealmObject> {

    public void fillByRealm(T realmObject);
    public T saveInRealm(Context ctx, Realm realm);
    public Boolean deleteFromRealm(Context ctx,Realm realm);


}
