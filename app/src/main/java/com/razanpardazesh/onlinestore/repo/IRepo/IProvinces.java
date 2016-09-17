package com.razanpardazesh.onlinestore.repo.IRepo;

import android.content.Context;

import com.razanpardazesh.onlinestore.data.Province;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/17/2016.
 */

public interface IProvinces {

    public ArrayList<Province> getAvailableProvince(Context context);

}
