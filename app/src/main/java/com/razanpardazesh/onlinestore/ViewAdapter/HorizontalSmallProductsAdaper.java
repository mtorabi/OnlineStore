package com.razanpardazesh.onlinestore.ViewAdapter;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.Tools.FontApplier;

/**
 * Created by Torabi on 9/10/2016.
 */

public class HorizontalSmallProductsAdaper extends RecyclerView.Adapter<HorizontalSmallProductsAdaper.ViewHolder> {

    private Boolean fakeBindMostVisited = true;


    public void setFakeBindMostVisited(Boolean fakeBindMostVisited) {
        this.fakeBindMostVisited = fakeBindMostVisited;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent == null || parent.getContext() == null)
            return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_small_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int imageRes = 0;
        String title = "";
        long price = 0;

        if (fakeBindMostVisited) {
            switch (position) {
                case 8:
                    imageRes = R.drawable.c_chai_limo;
                    title = "چای لیمویی بسته متوسط";
                    price = 20000;
                    break;

                case 7:
                    imageRes = R.drawable.c_cooki_1;
                    title = "کلوچه ی نارگیلی دوتایی";
                    price = 2000;
                    break;
                case 6:
                    imageRes = R.drawable.c_chai_zaferani;
                    title = "چای زعفرانی بسته متوسط";
                    price = 30000;
                    break;
                case 5:
                    imageRes = R.drawable.c_cooki_2;
                    title = "کلوچه نارگیلی 8 تایی";
                    price = 12000;
                    break;
                case 4:
                    imageRes = R.drawable.c_chai_sonati;
                    title = "چایی سنتی بسته متوسط";
                    price = 20000;
                    break;
                case 3:
                    imageRes = R.drawable.c_cooki_3;
                    title = "کلوچه نارگیلی 20 تایی";
                    price = 2000;
                    break;
                case 2:
                    imageRes = R.drawable.c_cooki_4;
                    title = "کلوچه نارگیلی 12 تایی";
                    price = 30000;
                    break;

                case 1:
                    imageRes = R.drawable.c_chai_vije;
                    title = "چای ویژه";
                    price = 8000;
                    break;
                case 0:
                    imageRes = R.drawable.c_chaie_babone;
                    title = "چای بابونه بسته متوسط";
                    price = 10000;
                    break;
            }
        }
        else
        {
            switch (position) {
                case 0:
                    imageRes = R.drawable.c_chai_limo;
                    title = "چای لیمویی بسته متوسط";
                    price = 20000;
                    break;
                case 1:
                    imageRes = R.drawable.c_chai_zaferani;
                    title = "چای زعفرانی بسته متوسط";
                    price = 30000;
                    break;
                case 2:
                    imageRes = R.drawable.c_chai_sonati;
                    title = "چایی سنتی بسته متوسط";
                    price = 20000;
                    break;
                case 3:
                    imageRes = R.drawable.c_cooki_1;
                    title = "کلوچه ی نارگیلی دوتایی";
                    price = 2000;
                    break;
                case 4:
                    imageRes = R.drawable.c_chai_vije;
                    title = "چای ویژه";
                    price = 8000;
                    break;
                case 5:
                    imageRes = R.drawable.c_cooki_2;
                    title = "کلوچه نارگیلی 8 تایی";
                    price = 12000;
                    break;
                case 6:
                    imageRes = R.drawable.c_chaie_babone;
                    title = "چای بابونه بسته متوسط";
                    price = 10000;
                    break;
                case 7:
                    imageRes = R.drawable.c_cooki_3;
                    title = "کلوچه نارگیلی 20 تایی";
                    price = 2000;
                    break;
                case 8:
                    imageRes = R.drawable.c_cooki_4;
                    title = "کلوچه نارگیلی 12 تایی";
                    price = 30000;
                    break;

            }
        }
        holder.imgImage.setImageResource(imageRes);

        holder.txtTitle.setText(title);
        holder.txtPrice.setText(String.format("%,d",price) + " تومان");

        FontApplier.applyMainFont(holder.txtTitle);
        FontApplier.applyMainFont(holder.txtPrice);

    }

    @Override
    public int getItemCount() {
        return 9;
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
