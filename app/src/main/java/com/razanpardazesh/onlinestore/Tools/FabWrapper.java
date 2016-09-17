package com.razanpardazesh.onlinestore.Tools;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.razanpardazesh.onlinestore.BasketFragment;
import com.razanpardazesh.onlinestore.R;

/**
 * Created by Torabi on 9/15/2016.
 */

public class FabWrapper {

    private FloatingActionButton fab;
    private Boolean isAddToBasket;
    private FragmentActivity act;

    public FabWrapper(FragmentActivity act, Boolean isAddToBasketFab) {
        this.act = act;
        this.isAddToBasket = isAddToBasketFab;
    }

    public void initFab(int resID, final View.OnClickListener onClickListener) {
        FloatingActionButton fab = (FloatingActionButton) act.findViewById(resID);

        if (isAddToBasket)
            fab.setImageResource(R.drawable.ic_add_white_24dp);
        else
            fab.setImageResource(R.drawable.ic_basket_white_48);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null)
                    onClickListener.onClick(view);

                BasketFragment.showBasket(act);

            }
        });
    }

}
