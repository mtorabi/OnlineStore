package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.Interfaces.IImage;

/**
 * Created by Torabi on 9/17/2016.
 */

public class ProductImage implements IImage {

    private int id = -1;
    private ProductSummary product = null;

    public ProductImage(int id) {
        this.id = id;
    }

    @Override
    public String getThumb(Context context) {
        if (SessionManagement.getInstance(context).getFakeBind())
            return String.valueOf(id);

        return null;
    }

    @Override
    public String getImage(Context context) {
        if (SessionManagement.getInstance(context).getFakeBind())
            return String.valueOf(id);

        return null;
    }
}
