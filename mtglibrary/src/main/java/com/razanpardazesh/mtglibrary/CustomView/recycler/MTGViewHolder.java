package com.razanpardazesh.mtglibrary.CustomView.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Home on 10/15/2016.
 */

public class MTGViewHolder extends RecyclerView.ViewHolder {

    private View rootView = null;

    private Map<Integer,View> childs;

    public MTGViewHolder(View itemView) {
        super(itemView);
        setRootView(itemView);
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }


    public void defineChild(int id)
    {
        if (rootView == null)
            return;

        if (childs == null) {
            childs = new HashMap<>();
        }

        View child = rootView.findViewById(id);

        if (child == null)
            return;

        childs.put(id,child);
    }

    public View getView(int id)
    {
        if (childs == null || childs.size() == 0)
            return null;
        return  childs.get(id);
    }

    public TextView getTextView(int id)
    {
        if (childs == null || childs.size() == 0)
            return null;
        return (TextView) childs.get(id);
    }

    public ImageView getImageView(int id)
    {
        if (childs == null || childs.size() == 0)
            return null;
        return (ImageView) childs.get(id);
    }

}
