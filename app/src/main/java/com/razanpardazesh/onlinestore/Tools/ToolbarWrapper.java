package com.razanpardazesh.onlinestore.Tools;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.razanpardazesh.onlinestore.R;

/**
 * Created by Torabi on 10/8/2016.
 */

public class ToolbarWrapper {
    Toolbar toolbar;
    private AppCompatActivity act;

    public ToolbarWrapper(AppCompatActivity act) {
        this.act = act;
    }

    public void initToolbarWithBack(int id,String title,final View.OnClickListener onclick)
    {
        Toolbar toolbar = (Toolbar) act.findViewById(id);
        act.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclick != null)
                    onclick.onClick(v);
                else
                    act.finish();
            }
        });
        act.setTitle(title);
        act.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        act.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_forward_black_24dp);
    }
}
