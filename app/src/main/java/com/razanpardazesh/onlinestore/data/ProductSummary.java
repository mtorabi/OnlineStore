package com.razanpardazesh.onlinestore.data;

import android.content.Context;

import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.Interfaces.IImage;

import java.util.Date;

/**
 * Created by Torabi on 9/11/2016.
 */

public class ProductSummary implements IImage {

    private long id = -1;
    private String name = null;
    private Float price = -1f;
    private Date createDtae = null;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreateDtae() {
        return createDtae;
    }

    public void setCreateDtae(Date createDtae) {
        this.createDtae = createDtae;
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
