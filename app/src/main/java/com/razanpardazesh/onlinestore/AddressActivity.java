package com.razanpardazesh.onlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.razanpardazesh.onlinestore.data.UserAddress;

public class AddressActivity extends AppCompatActivity {

    private final static String KEY_ADDRESS = "KEY_ADDRESS";

    private UserAddress selectedAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parseIntent();
        initToolbar();

        if (selectedAddress != null)
            Toast.makeText(this, selectedAddress.getTel(), Toast.LENGTH_SHORT).show();
    }

    public void initToolbar() {
        setContentView(R.layout.activity_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public static void openActivity(FragmentActivity act, UserAddress address) {
        Intent data = new Intent(act, AddressActivity.class);

        data.putExtra(KEY_ADDRESS, address);

        act.startActivity(data);
    }

    private void parseIntent() {
        if (getIntent() == null)
            return;

        if (!getIntent().hasExtra(KEY_ADDRESS))
            return;

        selectedAddress = getIntent().getParcelableExtra(KEY_ADDRESS);
    }
}
