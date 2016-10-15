package com.razanpardazesh.mtglibrary.CustomView.recycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.razanpardazesh.mtglibrary.R;

import java.util.ArrayList;

/**
 * Created by Home on 10/15/2016.
 */

public class MTGRecyclerView  extends RecyclerView {

    private MTGRecyclerAdpter mAdapter;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    private Boolean hideProgress = false;

    public MTGRecyclerView(Context context) {
        super(context);
    }

    public MTGRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MTGRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init()
    {
        if (isInEditMode())
            return;


        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!(getLayoutManager() instanceof LinearLayoutManager))
                    return;

                if (!hideProgress || mAdapter == null || mAdapter.getRecycleViewListener() == null)
                    return;

                LinearLayoutManager mLayoutManager = (LinearLayoutManager) getLayoutManager();

                totalItemCount = mLayoutManager.getItemCount();
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                mAdapter.getRecycleViewListener().onLoadMore();
            }
        });
    }


    public MTGRecyclerView setDefaultAdapter(OnRecycleViewListener onRecycleViewListener)
    {
        setDefaultLinearLayout();
        mAdapter = new MTGRecyclerAdpter();
        mAdapter.setRecycleViewListener(onRecycleViewListener);
        setAdapter(mAdapter);
        return this;
    }

    public MTGRecyclerView setDefaultDivider(Drawable divider)
    {
        DividerDecoration decoration = new DividerDecoration(divider);
        addItemDecoration(decoration);
        return this;
    }

    public MTGRecyclerView setDefaultDivider() {
        setDefaultDivider(ContextCompat.getDrawable(getContext(), R.drawable.divider_basket_item));
        return this;
    }

    private MTGRecyclerView setDefaultLinearLayout()
    {
        RecyclerView.LayoutManager rowManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setLayoutManager(rowManager);
        return this;
    }

    public void addRows(ArrayList<?> rows,Boolean hideProgress)
    {
        if (mAdapter == null)
            return;


        mAdapter.addRow(rows,hideProgress);
    }

}
