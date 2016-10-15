package com.razanpardazesh.mtglibrary.CustomView.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.razanpardazesh.mtglibrary.R;

/**
 * Created by Home on 10/15/2016.
 */

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
    }
}
