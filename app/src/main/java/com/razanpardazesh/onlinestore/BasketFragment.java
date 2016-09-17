package com.razanpardazesh.onlinestore;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.plus.PlusOneButton;
import com.razanpardazesh.onlinestore.Tools.FontApplier;
import com.razanpardazesh.onlinestore.ViewAdapter.BasketItemAdapter;
import com.razanpardazesh.onlinestore.ViewAdapter.HorizontalSmallProductsAdaper;
import com.razanpardazesh.onlinestore.ViewAdapter.decorations.DividerDecoration;
import com.razanpardazesh.onlinestore.data.BasketStatistics;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.razanpardazesh.onlinestore.R.id.btnRegisterBasket;
import static com.razanpardazesh.onlinestore.R.id.lst_basketItem;

public class BasketFragment extends DialogFragment {


    private ViewGroup rootView;
    private BasketItemAdapter adapter;
    private Button btnRegisterBasket;
    private RecyclerView lstItems;
    private TextView txtEmptyBasketHint;
    private TextView txtBasketTitle;
    private TextView txtTotalPrice;
    private TextView txtTotalCount;

    public BasketFragment() {
    }


    private static BasketFragment newInstance() {
        BasketFragment fragment = new BasketFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NO_FRAME,
                android.R.style.Theme_Black_NoTitleBar);
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_basket, container, false);

        FontApplier.applyMainFont(rootView);
        initBasketItem();
        initDissmissAria();
        initBasketStatistics();
        initRegisterBasketButton();
        return rootView;
    }

    private void initBasketItem() {

        if (lstItems == null)
            lstItems = (RecyclerView) rootView.findViewById(R.id.lst_basketItem);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        lstItems.setLayoutManager(layoutManager);
        DividerDecoration dividerDecoration =
                new DividerDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.divider_basket_item));

        adapter = new BasketItemAdapter(getActivity());
        lstItems.setAdapter(adapter);
        lstItems.addItemDecoration(dividerDecoration);

        adapter.setBasketItemAdapterInterface(new BasketItemAdapter.BasketItemAdapterInterface() {
            @Override
            public void onSomthingChanged() {
                initBasketStatistics();
                initRegisterBasketButton();
            }
        });
    }

    private void initBasketStatistics() {
        BasketStatistics statistics = new BasketStatistics();

        if (adapter != null)
            statistics = adapter.getStatistics();

        if (txtTotalPrice == null) {
            txtTotalPrice = (TextView) rootView.findViewById(R.id.txtTotalPrice);
        }
        if (txtTotalCount == null) {
            txtTotalCount = (TextView) rootView.findViewById(R.id.txtTotalCount);
        }

        NumberFormat format = new DecimalFormat("###,###,###,###");

        txtTotalPrice.setText(format.format(statistics.getTotalPrices()) + " " + getString(R.string.toman));
        txtTotalCount.setText(format.format(statistics.getTotalCounts()) + " " + getString(R.string.product));

        if (lstItems == null)
            lstItems = (RecyclerView) rootView.findViewById(R.id.lst_basketItem);

        if (txtEmptyBasketHint == null)
            txtEmptyBasketHint = (TextView) rootView.findViewById(R.id.txtEmptyBasketHint);

        if (txtBasketTitle == null)
            txtBasketTitle = (TextView) rootView.findViewById(R.id.txtBasketTitle);

        ViewGroup root = (ViewGroup) lstItems.getParent();

        Boolean emptyList = statistics.getTotalCounts() == 0;

        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            switch (child.getId())
            {
                case R.id.btnRegisterBasket:
                    break;
                case R.id.txtEmptyBasketHint:
                    if (emptyList)
                        child.setVisibility(View.VISIBLE);
                    else
                        child.setVisibility(View.GONE);
                    break;
                default:
                    if (emptyList)
                        child.setVisibility(View.GONE);
                    else
                        child.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }

    private void initDissmissAria() {
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        
        rootView.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }

    public static BasketFragment showBasket(FragmentActivity act) {
        FragmentTransaction ft = act.getSupportFragmentManager()
                .beginTransaction();

        BasketFragment basket = BasketFragment.newInstance();

        basket.show(ft, "BASKET");
        return basket;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FragmentActivity act = getActivity();
        dismiss();
        showBasket(act);
    }

    public void initRegisterBasketButton() {
        if (btnRegisterBasket == null)
            btnRegisterBasket = (Button) rootView.findViewById(R.id.btnRegisterBasket);

        if (adapter == null || adapter.getItemCount() == 0) {
            btnRegisterBasket.setAlpha(0.5f);
            btnRegisterBasket.setEnabled(false);

        } else {
            btnRegisterBasket.setAlpha(1);
            btnRegisterBasket.setEnabled(true);
        }

        btnRegisterBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
