package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.mtglibrary.network.OkHttpInstance;
import com.razanpardazesh.onlinestore.BuildConfig;
import com.razanpardazesh.onlinestore.data.ProductsGroup;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;
import com.razanpardazesh.onlinestore.network.UrlBuilder;
import com.razanpardazesh.onlinestore.repo.IRepo.IProductsGroups;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductsGroupsServerRepo implements IProductsGroups {


    @Override
    public ProductGroupAnswer getGroups(Context context, long groupId, String key, long lastIndex, int count) {

        String serverAddress = UrlBuilder.builder(context).getSubGroupsUrl(groupId, lastIndex, count, key);
        Request request = new Request.Builder()
                .url(serverAddress)
                .build();
        Response response = null;
        try {
            response = OkHttpInstance.getInstance().newCall(request).execute();
            String jsonStr ;
            if (response != null)
                jsonStr = response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
        }



        return null;
    }
}
