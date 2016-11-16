package com.razanpardazesh.onlinestore;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.razanpardazesh.mtglibrary.CustomView.DialogBuilder;
import com.razanpardazesh.mtglibrary.tools.AsyncWrapper;
import com.razanpardazesh.mtglibrary.tools.FontApplier;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.data.UserAddress;
import com.razanpardazesh.onlinestore.data.serverWrapper.ServerAnswer;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressAnswer;
import com.razanpardazesh.onlinestore.repo.BasketFakeRepo;
import com.razanpardazesh.onlinestore.repo.BasketLocalRepo;
import com.razanpardazesh.onlinestore.repo.IRepo.IBasketItems;
import com.razanpardazesh.onlinestore.repo.IRepo.IUserAddresses;
import com.razanpardazesh.onlinestore.repo.UserAddressFakeRepo;
import com.razanpardazesh.onlinestore.repo.UserAddressesLocalRepo;

public class SelectShipmentType extends AppCompatActivity {

    IUserAddresses userAdresses;

    IBasketItems basketRepo;

    AsyncWrapper getAdressAysnc;

    UserAddress selectedUserAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shipment_type);

        initToolbar();
        initFonts();
        initRepos();
        getAddressess();
        initChangeAddress();
        initActionButton();
    }



    public void initFonts() {
        View root = findViewById(R.id.root);
        FontApplier.applyMainFont(root);
    }

    public void initActionButton()
    {
        TextView txtAction = (TextView) findViewById(R.id.txtAction);
        txtAction.setText("ارسال سفارش");
        View parent = (View) txtAction.getParent();
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basketRepo.clearBasket(getApplicationContext());
                DialogBuilder builder = new DialogBuilder();
                builder.showAlert(SelectShipmentType.this, "سفارش شما با موفقیت به سرور ارسال شد. شما می توانید وضعیت سفارش خود را در بخش سفارشات من پیگیری نمایید", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        });
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

    public void initRepos() {
        if (SessionManagement.getInstance(getApplicationContext()).getFakeBind()) {
            userAdresses = new UserAddressFakeRepo();
            basketRepo = new BasketFakeRepo();
        } else {
            userAdresses = new UserAddressesLocalRepo(getApplicationContext());
            basketRepo = new BasketLocalRepo();
        }

    }

    public void initChangeAddress() {
        TextView btnChangeAddress = (TextView) findViewById(R.id.btnChangeAddress);
        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddAddressActivity(selectedUserAddress);
            }
        });
    }

    public void getAddressess() {
        getAdressAysnc = new AsyncWrapper().setDoOnBackground(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                return userAdresses.getLastUserAddress(getApplicationContext());
            }
        }).setDoOnAnswer(new AsyncWrapper.Callback() {
            @Override
            public Object call(Object object) {
                if (!ServerAnswer.isSuccess(object)) {
                    //TODO MTG HANDLE ERROR
                    return null;
                }

                UserAddressAnswer answer = (UserAddressAnswer) object;

                if (answer.getUserAddress() == null)
                    openAddAddressActivity(null);

                selectedUserAddress = answer.getUserAddress();
                bindUserAddress(answer.getUserAddress());

                return null;
            }
        });
        getAdressAysnc.setProgressDialog(null);
        getAdressAysnc.run(getApplicationContext());

    }

    public void openAddAddressActivity(UserAddress address) {
        AddressActivity.openActivity(this, address);
    }

    private void bindUserAddress(UserAddress address) {
        TextView txtProvince = (TextView) findViewById(R.id.txtProvince);
        TextView txtCity = (TextView) findViewById(R.id.txtCity);
        TextView txtAddress = (TextView) findViewById(R.id.txtAddress);
        TextView txtPostalCode = (TextView) findViewById(R.id.txtPostalCode);
        TextView txtEmergencyTel = (TextView) findViewById(R.id.txtEmergencyTel);
        TextView txtHomeTel = (TextView) findViewById(R.id.txtHomeTel);

        txtProvince.setText(address.getProvince().getName());
        txtCity.setText(address.getCity().getName());
        txtAddress.setText(address.getAddressStr());
        txtPostalCode.setText(address.getPostalCode());
        txtEmergencyTel.setText(address.getEmergencyTel());
        txtHomeTel.setText(address.getTel());
    }

}
