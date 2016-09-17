package com.razanpardazesh.onlinestore.ViewAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.razanpardazesh.onlinestore.CustomView.DialogBuilder;
import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.BasketItem;
import com.razanpardazesh.onlinestore.data.BasketStatistics;
import com.razanpardazesh.onlinestore.repo.BasketFakeRepo;
import com.razanpardazesh.onlinestore.repo.BasketLocalRepo;
import com.razanpardazesh.onlinestore.repo.IRepo.IBasketItems;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import static com.razanpardazesh.onlinestore.R.string.total;

/**
 * Created by Torabi on 9/15/2016.
 */

public class BasketItemAdapter extends RecyclerView.Adapter<BasketItemAdapter.ViewHolder> {

    public interface BasketItemAdapterInterface {
        public void onSomthingChanged();
    }

    BasketItemAdapterInterface basketItemAdapterInterface;

    IBasketItems basketRepo;
    FragmentActivity context;

    public BasketItemAdapter(FragmentActivity context) {

        if (SessionManagement.getInstance(context).getFakeBind())
            basketRepo = new BasketFakeRepo();
        else
            basketRepo = new BasketLocalRepo();

        this.context = context;
    }

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
        final BasketItem item = basketRepo.getBasketItems(context).get(position);

        if (item == null) {
            return;
        }

        if (item.getProduct() == null || item.getCount() == 0) {
            basketRepo.getBasketItems(context).remove(position);
            notifyDataSetChanged();
            return;
        }

        NumberFormat formatter = new DecimalFormat("###,###,###,###");

        holder.txtCount.setText(formatter.format(item.getCount()));
        holder.txtPrice.setText(formatter.format(item.getProduct().getPrice()));
        holder.txtTitle.setText(item.getProduct().getName());

        if (SessionManagement.getInstance(context).getFakeBind()) {
            holder.imgProductItem.setImageResource(Integer.parseInt(item.getProduct().getThumb(context)));
        } else {
            //TODO MTG
        }

        holder.imgDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getCount() < 2) {
                    new DialogBuilder().showYesNOAlert(context
                            , null
                            , context.getString(R.string.remove_last_product_question)
                            , new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    basketRepo.removeProduct(context, item.getProduct());
                                    callSomthingChanged();
                                    notifyDataSetChanged();
                                }
                            }, null);
                    return;
                }
                basketRepo.removeProduct(context, item.getProduct());
                callSomthingChanged();
                notifyDataSetChanged();
            }
        });
        holder.imgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basketRepo.addProduct(context, item.getProduct());
                callSomthingChanged();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return basketRepo.getBasketItems(context).size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtPrice;
        public TextView txtCount;
        public ImageView imgUp;
        public ImageView imgDown;
        public ImageView imgProductItem;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtCount = (TextView) itemView.findViewById(R.id.txtCount);
            imgUp = (ImageView) itemView.findViewById(R.id.imgUp);
            imgDown = (ImageView) itemView.findViewById(R.id.imgDown);
            imgProductItem = (ImageView) itemView.findViewById(R.id.imgProductItem);
        }
    }

    public void callSomthingChanged()
    {
        if (basketItemAdapterInterface != null)
            basketItemAdapterInterface.onSomthingChanged();
    }

    public BasketStatistics getStatistics()
    {
        if (basketRepo != null)
            return basketRepo.getStatistics(context);

        return new BasketStatistics();
    }

    public void setBasketItemAdapterInterface(BasketItemAdapterInterface basketItemAdapterInterface) {
        this.basketItemAdapterInterface = basketItemAdapterInterface;
    }
}
