package com.razanpardazesh.onlinestore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Torabi on 9/6/2016.
 */

public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";

    public PageFragment() {
    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout, container, false);

        TextView txt = (TextView) rootView.findViewById(R.id.page_number_label);
        View cardBox = (View) rootView.findViewById(R.id.cardBox);

        cardBox.setVisibility(View.GONE);
        txt.setVisibility(View.VISIBLE);

        int page = getArguments().getInt(ARG_PAGE_NUMBER, -1);

        String temp = "";

        switch (page)
        {
            case 1:
                cardBox.setVisibility(View.VISIBLE);
                txt.setVisibility(View.GONE);
                break;
            case 2:
                temp =  "لیست کالاها";
                break;
            case 3:
                temp =  "لیست خرید";
                break;
        }

        txt.setText(temp);

        return rootView;
    }
}
