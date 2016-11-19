package com.razanpardazesh.onlinestore.ViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.razanpardazesh.mtglibrary.tools.Convertor;
import com.razanpardazesh.onlinestore.Tools.ImageviewWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.ContentImage;

import java.util.ArrayList;

/**
 * Created by Torabi on 9/17/2016.
 */

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ViewHolder> {

    public interface ProductImageAdapterInterface {
        public void onClick(ImageView view, ContentImage image, int position);
    }

    ProductImageAdapterInterface productImageAdapterInterface;
    ArrayList<ContentImage> images = new ArrayList<>();
    Context context;

    public ProductImagesAdapter(ArrayList<ContentImage> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout frame = new FrameLayout(context);
        ImageView imageView = new ImageView(context);
        int size = Convertor.toPixcel(70, context);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
        imageView.setLayoutParams(params);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        frame.addView(imageView);
        ViewHolder holder = new ViewHolder(frame);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (SessionManagement.getInstance(context).getFakeBind())
            holder.imageView.setImageResource(Integer.parseInt(images.get(position).getThumb(context)));
        else {
            new ImageviewWrapper(context).FromUrl(images.get(position).getThumb(context)).into(holder.imageView).load();
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productImageAdapterInterface != null)
                    productImageAdapterInterface.onClick((ImageView) v, images.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) ((ViewGroup) itemView).getChildAt(0);
        }
    }

    public void setProductImageAdapterInterface(ProductImageAdapterInterface productImageAdapterInterface) {
        this.productImageAdapterInterface = productImageAdapterInterface;
    }
}
