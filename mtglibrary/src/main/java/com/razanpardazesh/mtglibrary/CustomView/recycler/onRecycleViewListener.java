package com.razanpardazesh.mtglibrary.CustomView.recycler;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Home on 10/15/2016.
 */

public interface OnRecycleViewListener {
    public MTGViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    public void onBindViewHolder(MTGViewHolder holder, int position,IRecyclerRow row);
    public void onLoadMore();
    public void onRowClick(IRecyclerRow row, int pos, View v);
    public Boolean onRowLongClick(IRecyclerRow row, int pos, View v);

}
