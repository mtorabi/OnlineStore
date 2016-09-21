package com.razanpardazesh.onlinestore;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.razanpardazesh.mtglibrary.tools.FontApplier;

public class SelectShipmentType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shipment_type);
        initToolbar();
    }

    public void initFonts()
    {
        View root =findViewById(R.id.root);
        FontApplier.applyMainFont(root);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_forward_black_24dp);
    }
    public static void openActivity(FragmentActivity act) {
        Intent data = new Intent(act, SelectShipmentType.class);
        act.startActivity(data);
    }

    public void initChangeAddress()
    {
    }

}
