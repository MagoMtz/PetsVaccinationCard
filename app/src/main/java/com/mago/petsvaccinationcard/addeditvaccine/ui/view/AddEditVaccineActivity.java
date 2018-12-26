package com.mago.petsvaccinationcard.addeditvaccine.ui.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import com.mago.petsvaccinationcard.PetsVaccinationApp;
import com.mago.petsvaccinationcard.R;
import com.mago.petsvaccinationcard.addeditpet.ui.view.DatePickerFragment;
import com.mago.petsvaccinationcard.addeditvaccine.di.AddEditVaccineComponent;
import com.mago.petsvaccinationcard.addeditvaccine.ui.presenter.AddEditVaccinePresenter;
import com.mago.petsvaccinationcard.databinding.ActivityAddEditVaccineBinding;
import com.mago.petsvaccinationcard.entities.Vaccine;
import com.mago.petsvaccinationcard.util.CrashlyticsLogger;
import com.mago.petsvaccinationcard.util.Flags;
import com.mago.petsvaccinationcard.util.StringValidator;

public class AddEditVaccineActivity extends AppCompatActivity implements AddEditVaccineView{
    private static final String TAG = AddEditVaccineActivity.class.getSimpleName();
    private ActivityAddEditVaccineBinding view;
    private Flags.ActionType actionType;

    private AddEditVaccineComponent component;
    private AddEditVaccinePresenter presenter;

    private int vaccineId;
    private int petId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_vaccine);

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
    public void setVaccineInfo(Vaccine vaccine) {
        this.vaccineId = vaccine.getVaccineId();

        view.contentVaccine.etVaccineName.setText(vaccine.getVaccineName());
        view.contentVaccine.etImplementationDate.setText(vaccine.getVaccineDate());
        view.contentVaccine.etNextAppointment.setText(vaccine.getVaccineNextAppointment());

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(vaccine.getVaccineName());
    }

    @Override
    public void setNotNullError(Vaccine vaccine) {
        if (StringValidator.isNullOrEmpty(vaccine.getVaccineName()))
            view.contentVaccine.tilyVaccineName.setError(getString(R.string.err_msg_null_field));
        else
            view.contentVaccine.tilyVaccineName.setError(null);

        if (!StringValidator.isValidDate(vaccine.getVaccineDate()))
            view.contentVaccine.lyApplicationDate.setBackgroundColor(getResources().getColor(R.color.colorError));
        else
            view.contentVaccine.lyApplicationDate.setBackgroundColor(getResources().getColor(android.R.color.transparent));

    }

    @Override
    public void onVaccineSaved() {
        CrashlyticsLogger.logNewVaccine();
        finish();
    }

    @Override
    public void onVaccineUpdated() {
        finish();
    }

    private void fetchData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null)
            return;

        actionType = (Flags.ActionType)extras.getSerializable(Flags.IntentKeys.ACTION_TYPE);
        vaccineId = extras.getInt(Flags.IntentKeys.VACCINE_ID);
        petId = extras.getInt(Flags.IntentKeys.PET_ID);
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

        component = app.getAddEditVaccineComponent(this, this);
        presenter = getPresenter();
    }

    private void setupClickListeners() {
        view.fabSaveVaccine.setOnClickListener(
                v -> saveVaccine()
        );
        view.contentVaccine.btnImplementationDate.setOnClickListener(
                v -> showDatePicker(view.contentVaccine.etImplementationDate)
        );
        view.contentVaccine.btnNextAppointmentDate.setOnClickListener(
                v -> showDatePicker(view.contentVaccine.etNextAppointment)
        );
    }

    private void checkForActionType() {
        switch (actionType) {
            case EDIT:
                presenter.getVaccine(vaccineId);
                break;
        }
    }

    private AddEditVaccinePresenter getPresenter() {
        return component.getPresenter();
    }

    private void saveVaccine() {
        Vaccine vaccine = new Vaccine();
        vaccine.setPetId(petId);

        if (view.contentVaccine.etVaccineName.getText() != null)
            vaccine.setVaccineName(view.contentVaccine.etVaccineName.getText().toString());

        if (view.contentVaccine.etImplementationDate.getText() != null)
            vaccine.setVaccineDate(view.contentVaccine.etImplementationDate.getText().toString());

        if (view.contentVaccine.etNextAppointment.getText() != null)
            vaccine.setVaccineNextAppointment(view.contentVaccine.etNextAppointment.getText().toString());

        switch (actionType){
            case SAVE_NEW:
                CrashlyticsLogger.logButton("Save Vaccine", TAG);
                presenter.saveVaccine(vaccine);
                break;
            case EDIT:
                CrashlyticsLogger.logButton("Edit Vaccine", TAG);
                vaccine.setVaccineId(vaccineId);
                presenter.updateVaccine(vaccine);
                break;
        }
    }

    private void showDatePicker(TextInputEditText editText) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();

        String sDate[] = editText.getText().toString().split("/");

        if (sDate[0].contains("d")) {
            datePickerFragment.setDate(new int[]{});
        } else {
            int day =  Integer.parseInt(sDate[0]);
            int month =  Integer.parseInt(sDate[1]);
            int year =  Integer.parseInt(sDate[2]);
            datePickerFragment.setDate(new int[]{day, month-1, year});
        }

        datePickerFragment.setEtDate(editText);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");

        CrashlyticsLogger.logButton("Show Date Picker", TAG);
    }
}
