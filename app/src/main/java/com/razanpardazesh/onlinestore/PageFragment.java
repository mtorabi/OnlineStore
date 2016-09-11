package com.razanpardazesh.onlinestore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Torabi on 9/6/2016.
 */

public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    private static final String ARG_PAGE_HEIGHT = "ARG_PAGE_HEIGHT";

    public PageFragment() {
    }

    public static PageFragment newInstance(int page,int height) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        args.putInt(ARG_PAGE_HEIGHT, height);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_page_layout, container, false);

        ImageView imgAdv = (ImageView) rootView.findViewById(R.id.imgAdv);

        int page = getArguments().getInt(ARG_PAGE_NUMBER, -1);
        int height = getArguments().getInt(ARG_PAGE_HEIGHT, 300);

        String temp = "";

        switch (page)
        {
            case 1:
                imgAdv.setImageResource(R.drawable.c_refah);
                break;
            case 2:
                imgAdv.setImageResource(R.drawable.c_refah_2);
                break;
            case 3:
                imgAdv.setImageResource(R.drawable.c_refah_3);
                break;
        }

        final ViewGroup.LayoutParams params = imgAdv.getLayoutParams();
        params.height = height;
        imgAdv.setLayoutParams(params);

        return rootView;
    }

    public void onClick(View view)
    {
        ProductActivity.openActivity(getActivity(),0);
    }


}
