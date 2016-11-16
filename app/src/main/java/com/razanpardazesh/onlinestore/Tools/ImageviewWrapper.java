package com.razanpardazesh.onlinestore.Tools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by MTorabi on 16/11/2016.
 */

public class ImageviewWrapper {

    private ImageView imgView;
    private String url;
    private Context context;

    public ImageviewWrapper(Context context) {
        this.context = context;
    }

    public ImageviewWrapper into(ImageView view) {
        this.imgView = view;
        return this;
    }

    public ImageviewWrapper FromUrl(String url)
    {
        this.url = url;
        return this;
    }

    public void load()
    {
        Glide.with(context).load(url).fitCenter().into(imgView);
    }

}
