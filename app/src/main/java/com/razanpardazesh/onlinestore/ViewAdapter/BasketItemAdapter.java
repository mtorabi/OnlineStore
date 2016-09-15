package com.razanpardazesh.onlinestore.ViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.data.BasketItem;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/15/2016.
 */

public class BasketItemAdapter extends RecyclerView.Adapter<BasketItemAdapter.ViewHolder> {

    ArrayList<BasketItem> items = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent == null || parent.getContext() == null)
            return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_item, parent, false);
        BasketItemAdapter.ViewHolder holder = new BasketItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtTitle;
        public TextView txtPrice;
        public TextView txtCount;
        public ImageView imgUp;
        public ImageView imgDown;
        public ImageView imgProduct;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
