package com.razanpardazesh.onlinestore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.razanpardazesh.mtglibrary.CustomView.DialogBuilder;
import com.razanpardazesh.mtglibrary.tools.FontApplier;
import com.razanpardazesh.mtglibrary.tools.LocationHelper;
import com.razanpardazesh.onlinestore.Tools.ToolbarWrapper;
import com.razanpardazesh.onlinestore.data.UserAddress;

public class AddressActivity extends AppCompatActivity {

    private final static String KEY_ADDRESS = "KEY_ADDRESS";

    private UserAddress selectedAddress;

    ProgressBar vProgress;
    AutoCompleteTextView txtTransfereeName;
    AutoCompleteTextView txtTransfereeEmergencyTel;
    AutoCompleteTextView txtTransfereeTel;
    AutoCompleteTextView txtPostalCode;
    AutoCompleteTextView txtAddress;
    TextView txtAction;
    Spinner sProvince;
    Spinner sCity;
    Button btnSelectLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        parseIntent();
        initToolbar();
        initView();
        applyFont();
        initSelectLocationBuuton();

    }

    public void initView() {
        vProgress = (ProgressBar) findViewById(R.id.vProgress);
        txtTransfereeName = (AutoCompleteTextView) findViewById(R.id.txtTransfereeName);
        txtTransfereeEmergencyTel = (AutoCompleteTextView) findViewById(R.id.txtTransfereeEmergencyTel);
        txtTransfereeTel = (AutoCompleteTextView) findViewById(R.id.txtTransfereeTel);
        txtPostalCode = (AutoCompleteTextView) findViewById(R.id.txtPostalCode);
        txtAddress = (AutoCompleteTextView) findViewById(R.id.txtAddress);
        sProvince = (Spinner) findViewById(R.id.sProvince);
        sCity = (Spinner) findViewById(R.id.sCity);
        btnSelectLocation = (Button) findViewById(R.id.btnSelectLocation);
        txtAction = (TextView) findViewById(R.id.txtAction);

        txtAction.setText("ثبت آدرس");


    }


    public void initActionButton() {
        if (txtAction == null)
            txtAction = (TextView) findViewById(R.id.txtAction);

        txtAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void bindInputAddress() {

    }

    public void applyFont() {
        View view = findViewById(R.id.root);
        FontApplier.applyMainFont(view);
    }

    public void initToolbar() {
        ToolbarWrapper toolbarWrapper = new ToolbarWrapper(this);
        toolbarWrapper.initToolbarWithBack(R.id.toolbar, getTitle().toString(), null);
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

    public void initSelectLocationBuuton() {
        Button btnSelectLocation = (Button) findViewById(R.id.btnSelectLocation);
        btnSelectLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Boolean answer = LocationHelper.showLocationPicker(AddressActivity.this);

                    if (answer == false) {
                        new DialogBuilder().showAlert(AddressActivity.this, getString(R.string.google_play_service_alert));
                    }
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                    new DialogBuilder().showAlert(AddressActivity.this, getString(R.string.google_play_service_alert));
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                    new DialogBuilder().showAlert(AddressActivity.this, getString(R.string.google_play_service_alert));
                } catch (Exception e) {
                    e.printStackTrace();
                    new DialogBuilder().showAlert(AddressActivity.this, getString(R.string.google_play_service_alert));
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Place place = LocationHelper.parseIntentOfLocationPicker_gatPlace(this, data, requestCode, resultCode);

        if (place == null)
            return;

        Toast.makeText(this, String.valueOf(place.getLatLng().latitude), Toast.LENGTH_SHORT).show();

    }
}
