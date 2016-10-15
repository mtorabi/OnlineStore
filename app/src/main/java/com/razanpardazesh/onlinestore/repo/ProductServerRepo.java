package com.razanpardazesh.onlinestore.repo;

import android.content.Context;

import com.razanpardazesh.mtglibrary.network.OkHttpInstance;
import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductGroupAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.ProductListAnswer;
import com.razanpardazesh.onlinestore.network.UrlBuilder;
import com.razanpardazesh.onlinestore.repo.IRepo.IProducts;

import org.json.JSONObject;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductServerRepo implements IProducts {
    @Override
    public ProductListAnswer getProducts(Context context, String key, long lastIndex, int Count) {
        return null;
    }

    @Override
    public ProductListAnswer getGroupsProducts(Context context, String key, long lastIndex, int Count, long groupID) {
        return null;
    }

    @Override
    public ProductListAnswer getBrandsProducts(Context context, String key, long lastIndex, int Count, long brandID) {
        return null;
    }

    @Override
    public ProductListAnswer getMostVistedProducts(Context context, String key, long lastIndex, int Count) {
        ProductListAnswer answer = new ProductListAnswer();
        String serverAddress = UrlBuilder.builder(context).getMostVisitedsPoductsUrl(Count);
        Request request = new Request.Builder()
                .url(serverAddress)
                .build();
        Response response = null;
        try {
            response = OkHttpInstance.getInstance().newCall(request).execute();
            String jsonStr;
            if (response != null) {
                jsonStr = response.body().string();
                answer.fillByJson(new JSONObject(jsonStr));
            }
        } catch (Exception e) {
            LogWrapper.loge("ProductServerRepo.getMostVistedProducts: ", e);
        }
        return answer;
    }

    @Override
    public ProductListAnswer getMostSoldProducts(Context context, String key, long lastIndex, int Count) {
        ProductListAnswer answer = new ProductListAnswer();
        String serverAddress = UrlBuilder.builder(context).getMostSoldsPoductsUrl(Count);
        Request request = new Request.Builder()
                .url(serverAddress)
                .build();
        Response response = null;
        try {
            response = OkHttpInstance.getInstance().newCall(request).execute();
            String jsonStr;
            if (response != null) {
                jsonStr = response.body().string();
                answer.fillByJson(new JSONObject(jsonStr));
            }
        } catch (Exception e) {
            LogWrapper.loge("ProductServerRepo.getMostSoldProducts: ", e);
        }
        return answer;
    }

    @Override
    public ProductAnswer getProduct(Context context, long id) {
        ProductAnswer answer = new ProductAnswer();
        String serverAddress = UrlBuilder.builder(context).getProductUrl(id);
        Request request = new Request.Builder()
                .url(serverAddress)
                .build();
        Response response = null;
        try {
            response = OkHttpInstance.getInstance().newCall(request).execute();
            String jsonStr;
            if (response != null) {
                jsonStr = response.body().string();
                answer.fillByJson(new JSONObject(jsonStr));
            }
        } catch (Exception e) {
            LogWrapper.loge("ProductServerRepo.getProduct: ", e);
        }


        return answer;

    }
}
