package com.razanpardazesh.onlinestore.ViewAdapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.razanpardazesh.onlinestore.ProductActivity;
import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.Tools.FontApplier;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.Product;
import com.razanpardazesh.onlinestore.data.ProductSummary;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Torabi on 9/10/2016.
 */

public class HorizontalSmallProductsAdaper extends RecyclerView.Adapter<HorizontalSmallProductsAdaper.ViewHolder> {

    private View.OnClickListener onProductClickListener;
    private ArrayList<ProductSummary> productSummaries = new ArrayList<>();

    private FragmentActivity context;

    public HorizontalSmallProductsAdaper(FragmentActivity context) {
        this.context = context;
    }

    public FragmentActivity getContext() {
        return context;
    }

    public void addProducts(ArrayList<ProductSummary> summaries) {
        this.productSummaries.addAll(summaries);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent == null || parent.getContext() == null)
            return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_small_product, parent, false);
        view.setOnClickListener(onProductClickListener);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        final ProductSummary summary = productSummaries.get(position);
        int imageRes = (int) summary.getId();
        final String imageUrl = (SessionManagement.getInstance(getContext()).getFakeBind()) ?
                String.valueOf(imageRes) : "";

        if (SessionManagement.getInstance(getContext()).getFakeBind()) {
            holder.imgImage.setImageResource(imageRes);
        } else {
            //TODO
        }

        holder.txtTitle.setText(summary.getName());

        NumberFormat formatter = new DecimalFormat("###,###,###,###");

        holder.txtPrice.setText(formatter.format(summary.getPrice()) + " تومان");

        View parent = (View) holder.txtPrice.getParent();

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ProductActivity.openActivity(getContext(), summary.getId(), holder.imgImage, imageUrl);
                } catch (Throwable e) {
                }
            }
        });

        FontApplier.applyMainFont(holder.txtTitle);
        FontApplier.applyMainFont(holder.txtPrice);

    }

    @Override
    public int getItemCount() {
        return productSummaries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgImage;
        public TextView txtTitle;
        public TextView txtPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            imgImage = (ImageView) itemView.findViewById(R.id.imgImage);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
        }
    }

}
