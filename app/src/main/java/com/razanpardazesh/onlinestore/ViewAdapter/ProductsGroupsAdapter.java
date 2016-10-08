package com.razanpardazesh.onlinestore.ViewAdapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.razanpardazesh.mtglibrary.tools.FontApplier;
import com.razanpardazesh.onlinestore.R;
import com.razanpardazesh.onlinestore.data.ProductsGroup;

import java.util.ArrayList;

/**
 * Created by Zikey on 02/10/2016.
 */

public class ProductsGroupsAdapter extends RecyclerView.Adapter<ProductsGroupsAdapter.ViewHolder> {

    private ArrayList<ProductsGroup> item;


    FragmentActivity context;


    public void addItem(ArrayList<ProductsGroup> item) {

        if (this.item==null){
            this.item=item;
        }
        else {
            if (item != null || item.size() != 0) {
                this.item.addAll(item);
                notifyDataSetChanged();
            }
            else return;
        }
    }

    public void removeItem() {
        item.clear();
        notifyDataSetChanged();
    }

    public void setContext(FragmentActivity context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent == null || parent.getContext() == null)
            return null;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_group_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtGroupName.setText(item.get(position).getName());
        holder.imgGroup.setImageResource(Integer.parseInt(item.get(position).getImage(context)));
    }


    @Override
    public int getItemCount() {
        if (item != null) {
            return item.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgGroup;
        public TextView txtGroupName;

        public ViewHolder(View itemView) {
            super(itemView);
            imgGroup = (ImageView) itemView.findViewById(R.id.imgGroup);
            txtGroupName = (TextView) itemView.findViewById(R.id.txtGroupName);

            View view= (View) txtGroupName.getParent();
            FontApplier.applyMainFont(view);

        }
    }
}



