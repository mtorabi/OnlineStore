package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.Interfaces.IImage;

import java.util.Date;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductsGroup implements IImage {
    private Long id;
    private String name;
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
