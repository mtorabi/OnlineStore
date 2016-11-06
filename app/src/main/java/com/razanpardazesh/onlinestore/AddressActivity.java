package com.razanpardazesh.onlinestore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.razanpardazesh.mtglibrary.CustomView.DialogBuilder;
import com.razanpardazesh.mtglibrary.tools.AsyncWrapper;
import com.razanpardazesh.mtglibrary.tools.FontApplier;
import com.razanpardazesh.mtglibrary.tools.LocationHelper;
import com.razanpardazesh.mtglibrary.tools.ValidateHelper;
import com.razanpardazesh.onlinestore.Tools.LogWrapper;
import com.razanpardazesh.onlinestore.Tools.SessionManagement;
import com.razanpardazesh.onlinestore.Tools.ToolbarWrapper;
import com.razanpardazesh.onlinestore.data.UserAddress;
import com.razanpardazesh.onlinestore.data.serverWrapper.UserAddressAnswer;
import com.razanpardazesh.onlinestore.repo.IRepo.IUserAddresses;
import com.razanpardazesh.onlinestore.repo.UserAddressFakeRepo;
import com.razanpardazesh.onlinestore.repo.UserAddressesLocalRepo;

public class AddressActivity extends AppCompatActivity {

    private final static String KEY_ADDRESS = "KEY_ADDRESS";


    private UserAddress selectedAddress;

    private IUserAddresses addressRepo;

    ProgressBar vProgress;
    AutoCompleteTextView txtTransfereeName;
    TextInputLayout tilTransfereeName;
    AutoCompleteTextView txtTransfereeEmergencyTel;

    AutoCompleteTextView txtTransfereeTel;
    AutoCompleteTextView txtPostalCode;
    AutoCompleteTextView txtAddress;
    TextView txtAction;
    Spinner sProvince;
    Spinner sCity;
    Button btnSelectLocation;
    AsyncWrapper userAddressAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        parseIntent();
        initToolbar();
        initRepos();
        initView();
        applyFont();
        initActionButton();
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
        tilTransfereeName = (TextInputLayout) findViewById(R.id.tilTransfereeName);
        txtAction.setText("ثبت آدرس");


    }


    private void initRepos() {
        if (SessionManagement.getInstance(getApplicationContext()).getFakeBind()) {
            addressRepo = new UserAddressFakeRepo();
        } else {
            addressRepo = new UserAddressesLocalRepo();
        }
    }


    public void initActionButton() {
        if (txtAction == null)
            txtAction = (TextView) findViewById(R.id.txtAction);

        txtAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final UserAddress address = getUserAddressFromForm();

                userAddressAsync = new AsyncWrapper();
                userAddressAsync.setDoOnBackground(new AsyncWrapper.Callback() {
                    @Override
                    public Object call(Object object) {

                        Object answer = addressRepo.updateUserAddress(getApplicationContext(), address);
                        return answer;
                    }
                }).setDoOnAnswer(new AsyncWrapper.Callback() {
                    @Override
                    public Object call(Object object) {
                        if (object == null || !(object instanceof UserAddressAnswer))
                            return null;

                        UserAddressAnswer answer = (UserAddressAnswer) object;

                        if (answer.getUserAddress() == null)
                            return null;

                        Intent data = generateIntentAnswer(answer.getUserAddress());


                        setResult(RESULT_OK, data);
                        finish();
                        return null;
                    }
                }).run(getApplicationContext());
            }
        });
    }

    private Intent generateIntentAnswer(UserAddress address) {
        Intent data = new Intent();
        data.putExtra(KEY_ADDRESS, address);
        return data;
    }

    /**
     * این متد آدرس وارد شده از طرف کاربر را خوانده و کلاس مربوطه را پر می کند
     *
     * @return
     */
    private UserAddress getUserAddressFromForm() {

        if (txtTransfereeName == null)
            initView();

        UserAddress userAddress = new UserAddress();

        if (!validateForm())
            return null;

        userAddress.setTransfereeName(txtTransfereeName.getText().toString());


        return null;
    }

    private boolean validateForm() {

        if (txtTransfereeName == null)
            initView();

        if (!validateNecessaryString(txtTransfereeName)) {
            LogWrapper.showValidateError(tilTransfereeName, getResources().getString(R.string.transferee_name), TextUtils.isEmpty(txtTransfereeName.getText()));
            return false;
        }



        return true;
    }

    private Boolean validateNecessaryString(EditText view)
    {
        if (view == null)
            return false;

        Editable input =  view.getText();

        return ValidateHelper.validateNecessaryString(input);
    }

    private Boolean validateCellphone(EditText view)
    {
        if (view == null)
            return false;

        Editable input =  view.getText();

        return ValidateHelper.validateIranCellphone(input);
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
