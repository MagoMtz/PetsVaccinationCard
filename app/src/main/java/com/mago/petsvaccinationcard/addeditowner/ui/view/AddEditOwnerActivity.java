package com.mago.petsvaccinationcard.addeditowner.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mago.petsvaccinationcard.PetsVaccinationApp;
import com.mago.petsvaccinationcard.R;
import com.mago.petsvaccinationcard.addeditowner.di.AddEditOwnerComponent;
import com.mago.petsvaccinationcard.addeditowner.ui.presenter.AddEditOwnerPresenter;
import com.mago.petsvaccinationcard.databinding.ActivityAddEditOwnerBinding;
import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.util.CrashlyticsLogger;
import com.mago.petsvaccinationcard.util.Flags;
import com.mago.petsvaccinationcard.util.StringValidator;

public class AddEditOwnerActivity extends AppCompatActivity implements AddEditOwnerView{
    private static final String TAG = AddEditOwnerActivity.class.getSimpleName();
    private ActivityAddEditOwnerBinding view;
    private Flags.ActionType actionType;

    private AddEditOwnerComponent component;
    private AddEditOwnerPresenter presenter;

    private int ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_owner);

        fetchData();

        setupToolbar();
        setupInjection();
        setupClickListeners();

        checkForActionType();

        presenter.onCreate();
        CrashlyticsLogger.logActivity(TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setOwnerInfo(Owner owner) {
        this.ownerId = owner.getOwnerId();

        view.contentOwner.etOwnerFirstName.setText(owner.getOwnerFirstName());
        view.contentOwner.etOwnerLastName.setText(owner.getOwnerLastName());
        view.contentOwner.etOwnerAddress.setText(owner.getOwnerAddress());
        view.contentOwner.etOwnerPhone.setText(owner.getOwnerPhone());

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(owner.getOwnerFirstName());
    }

    @Override
    public void setNotNullError(Owner owner) {
        if (StringValidator.isNullOrEmpty(owner.getOwnerFirstName()))
            view.contentOwner.tilyOwnerFirstName.setError(getString(R.string.err_msg_null_field));
        else
            view.contentOwner.tilyOwnerFirstName.setError(null);

        if (StringValidator.isNullOrEmpty(owner.getOwnerLastName()))
            view.contentOwner.tilyOwnerLastName.setError(getString(R.string.err_msg_null_field));
        else
            view.contentOwner.tilyOwnerLastName.setError(null);

        if (!StringValidator.isNullOrEmpty(owner.getOwnerPhone()))
            if (!StringValidator.isValidPhone(owner.getOwnerPhone()))
                view.contentOwner.tilyOwnerPhone.setError(getString(R.string.err_msg_invalid_phone));
            else
                view.contentOwner.tilyOwnerPhone.setError(null);
    }

    @Override
    public void onOwnerSaved() {
        CrashlyticsLogger.logNewUser();
        finish();
    }

    @Override
    public void onOwnerUpdated() {
        finish();
    }

    private void fetchData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null)
            return;

        actionType = (Flags.ActionType)extras.getSerializable(Flags.IntentKeys.ACTION_TYPE);
        ownerId = extras.getInt(Flags.IntentKeys.OWNER_ID);
    }

    private void setupToolbar() {
        setSupportActionBar(view.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupInjection() {
        PetsVaccinationApp app = (PetsVaccinationApp)getApplication();

        component = app.getAddEditOwnerComponent(this, this);
        presenter = getPresenter();
    }

    private void setupClickListeners() {
        view.fabSaveOwner.setOnClickListener( v -> saveOwner());
    }

    private void checkForActionType() {
        switch (actionType) {
            case EDIT:
                presenter.getOwner(ownerId);
                break;
            case SAVE_NEW:
                break;
        }
    }

    private AddEditOwnerPresenter getPresenter() {
        return component.getPresenter();
    }

    private void saveOwner() {
        Owner owner = new Owner();
        if (view.contentOwner.etOwnerFirstName.getText() != null)
            owner.setOwnerFirstName(view.contentOwner.etOwnerFirstName.getText().toString());
        if (view.contentOwner.etOwnerLastName.getText() != null)
            owner.setOwnerLastName(view.contentOwner.etOwnerLastName.getText().toString());
        if (view.contentOwner.etOwnerAddress.getText() != null)
            owner.setOwnerAddress(view.contentOwner.etOwnerAddress.getText().toString());
        if (view.contentOwner.etOwnerPhone.getText() != null)
            owner.setOwnerPhone(view.contentOwner.etOwnerPhone.getText().toString());

        switch (actionType){
            case SAVE_NEW:
                CrashlyticsLogger.logButton("Save Owner", TAG);
                presenter.saveOwner(owner);
                break;
            case EDIT:
                CrashlyticsLogger.logButton("Edit Owner", TAG);
                owner.setOwnerId(ownerId);
                presenter.updateOwner(owner);
                break;
        }
    }

}
