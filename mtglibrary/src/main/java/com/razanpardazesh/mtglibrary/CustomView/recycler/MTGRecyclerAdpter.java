package com.razanpardazesh.mtglibrary.CustomView.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razanpardazesh.mtglibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 10/15/2016.
 */

public class MTGRecyclerAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ROW = 0;
    private final int VIEW_TYPE_LOADER = 1;

    private List<IRecyclerRow> rows;
    private OnRecycleViewListener recycleViewListener;

    private Boolean hideProgress = false;

    @Override
    public int getItemViewType(int position) {
        if (rows == null) {
            return VIEW_TYPE_LOADER;
        }

        try {
            return rows.get(position) == null ? VIEW_TYPE_LOADER : VIEW_TYPE_ROW;
        } catch (Exception ec) {
            return VIEW_TYPE_LOADER;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ROW && recycleViewListener != null) {
            return recycleViewListener.onCreateViewHolder(parent, viewType);
        }

        if (viewType == VIEW_TYPE_LOADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_progress, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MTGViewHolder && recycleViewListener != null) {
            MTGViewHolder viweHolder = (MTGViewHolder) holder;
            recycleViewListener.onBindViewHolder(viweHolder, position,rows.get(position));
            if (viweHolder.getRootView() == null)
                return;

            viweHolder.getRootView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rows == null)
                        return;
                    recycleViewListener.onRowClick(rows.get(position), position, view);
                }
            });

            return;
        }

        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);

            if (hideProgress)
                loadingViewHolder.progressBar.setVisibility(View.GONE);
            else
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);

            return;
        }
    }

    @Override
    public int getItemCount() {
        return rows == null ? 1 : rows.size()+1;
    }

    public void addRow(ArrayList<?> rows, Boolean hideProgress) {
        this.hideProgress = hideProgress;

        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }

        ArrayList<IRecyclerRow> innerRow = new ArrayList<>();
        for (Object obj:
                rows) {
            if (obj instanceof IRecyclerRow)
                innerRow.add((IRecyclerRow) obj);
        }

        this.rows.addAll(innerRow);
        notifyDataSetChanged();
    }

    public void clearRows() {
        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }
        this.rows.clear();
    }

    public List<IRecyclerRow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<IRecyclerRow> rows) {
        this.rows = rows;
    }

    public OnRecycleViewListener getRecycleViewListener() {
        return recycleViewListener;
    }

    public void setRecycleViewListener(OnRecycleViewListener recycleViewListener) {
        this.recycleViewListener = recycleViewListener;
    }

    public Boolean getHideProgress() {
        return hideProgress;
    }

    public void setHideProgress(Boolean hideProgress) {
        this.hideProgress = hideProgress;
    }
}
