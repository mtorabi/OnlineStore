package com.razanpardazesh.onlinestore;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.razanpardazesh.mtglibrary.tools.FontApplier;


public class WelcomeFragment extends Fragment {
    private static final String ARG_PAGE_ID = "pageId";

    private int pageId = 0;
    private ViewGroup rootView = null;
    public WelcomeFragment() {
        // Required empty public constructor
    }

    public static WelcomeFragment newInstance(int padeId) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_ID, padeId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageId = getArguments().getInt(ARG_PAGE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_welcome, container, false);
        initViews();
        FontApplier.applyMainFont(rootView);

        return rootView;
    }

    private void initViews() {
        TextView txtWelcome = (TextView) rootView.findViewById(R.id.txtWelcome);
        switch (pageId)
        {
            case 0:
                txtWelcome.setTextColor(ContextCompat.getColor(getContext(),R.color.colorWelcome1_main));
                txtWelcome.setText("فروشگاه آنلاین مواد غذایی");
                break;
            case 1:
                txtWelcome.setTextColor(ContextCompat.getColor(getContext(),R.color.colorWelcome2_main));
                txtWelcome.setText("فروشگاهی در دستان شما");
                break;
            case 2:
                txtWelcome.setTextColor(ContextCompat.getColor(getContext(),R.color.colorWelcome3_main));
                txtWelcome.setText("با تنوعی بی نظیر از محصولات متنوع و قیمت های باور نکردنی");
                break;
        }
    }


}
