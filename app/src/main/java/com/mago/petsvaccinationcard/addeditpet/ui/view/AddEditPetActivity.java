package com.mago.petsvaccinationcard.addeditpet.ui.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.mago.petsvaccinationcard.PetsVaccinationApp;
import com.mago.petsvaccinationcard.R;
import com.mago.petsvaccinationcard.adapter.ListAdapter;
import com.mago.petsvaccinationcard.adapter.OnItemClickListener;
import com.mago.petsvaccinationcard.adapter.VaccineAdapter;
import com.mago.petsvaccinationcard.addeditowner.ui.view.AddEditOwnerActivity;
import com.mago.petsvaccinationcard.addeditpet.di.AddEditPetComponent;
import com.mago.petsvaccinationcard.addeditpet.ui.presenter.AddEditPetPresenter;
import com.mago.petsvaccinationcard.addeditvaccine.ui.view.AddEditVaccineActivity;
import com.mago.petsvaccinationcard.databinding.ActivityAddEditPetBinding;
import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.entities.Vaccine;
import com.mago.petsvaccinationcard.util.CrashlyticsLogger;
import com.mago.petsvaccinationcard.util.Flags;
import com.mago.petsvaccinationcard.util.StringValidator;

import java.util.ArrayList;
import java.util.Collections;

public class AddEditPetActivity extends AppCompatActivity implements OnItemClickListener, AddEditPetView {
    private final static String TAG = AddEditPetActivity.class.getSimpleName();
    private ActivityAddEditPetBinding view;
    private Flags.ActionType actionType;

    private AddEditPetComponent component;
    private ListAdapter ownerAdapter;
    private VaccineAdapter vaccineAdapter;
    private AddEditPetPresenter presenter;

    private int petId;
    private int ownerId;
    private View previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_pet);
        ownerId = -1;
        petId = -1;

        setupToolbar();
        setupInjection();
        setupOwnerRecyclerView();
        setupVaccineRecyclerView();
        setupClickListeners();

        presenter.onCreate();

        fetchData();
        checkForActionType();
        CrashlyticsLogger.logActivity(TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getOwnerList();
        presenter.getVaccineList(petId);
        shouldShowVaccineList(petId);

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

    //method for the owner list
    @Override
    public void onItemClick(Object object, View adapterElement, int position) {
        if (object instanceof Owner) {
            Owner owner =(Owner)object;
            onOwnerItemClick(owner, adapterElement);
        }
    }

    @Override
    public void setOwnerList(ArrayList<Owner> data) {
        ownerAdapter.setListType(Flags.ListType.OWNER);
        ownerAdapter.setOwnerList(data);
    }

    @Override
    public void setVaccineList(ArrayList<Vaccine> data) {
        vaccineAdapter.setVaccineList(data);
    }

    @Override
    public void setNotNullError(Pet pet) {
        if (StringValidator.isNullOrEmpty(pet.getPetName()))
            view.contentPet.tilyPetName.setError(getString(R.string.err_msg_null_field));
        else
            view.contentPet.tilyPetName.setError(null);

        if (StringValidator.isNullOrEmpty(pet.getPetSex()))
            view.contentPet.lyPetSex.setBackgroundColor(getResources().getColor(R.color.colorError));
        else
            view.contentPet.lyPetSex.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if (!StringValidator.isValidDate(pet.getPetBirthday()))
            view.contentPet.lyPetBirthday.setBackgroundColor(getResources().getColor(R.color.colorError));
        else
            view.contentPet.lyPetBirthday.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if (pet.getOwnerId() == -1)
            view.contentPet.lyOwners.setBackgroundColor(getResources().getColor(R.color.colorError));
        else
            view.contentPet.lyOwners.setBackgroundColor(getResources().getColor(android.R.color.transparent));

    }

    @Override
    public void onPetSaved(Pet pet) {
        this.petId = pet.getPetId();
        shouldShowVaccineList(petId);
        Snackbar.make(view.getRoot(), getString(R.string.add_edit_pet_activity_msg_pet_saved), Snackbar.LENGTH_SHORT).show();
        CrashlyticsLogger.logNewPet(pet.getPetKind());
    }

    @Override
    public void onPetUpdated() {
        Snackbar.make(view.getRoot(), getString(R.string.add_edit_pet_activity_msg_pet_updated), Snackbar.LENGTH_SHORT).show();
        CrashlyticsLogger.logEditPet("");
    }

    @Override
    public void setPetInfo(Pet pet) {
        petId = pet.getPetId();
        ownerId = pet.getOwnerId();

        view.contentPet.etPetName.setText(pet.getPetName());
        view.contentPet.etPetKind.setText(pet.getPetKind());

        if (pet.getPetSex().equals(getString(R.string.add_edit_pet_activity_rb_pet_sex_male)))
            view.contentPet.rbSexMale.setChecked(true);
        else
            view.contentPet.rbSexFemale.setChecked(true);

        view.contentPet.etBirthday.setText(pet.getPetBirthday());
        view.contentPet.etPetBreed.setText(pet.getPetBreed());
        view.contentPet.etPetIdNumber.setText(pet.getPetIdNumber());
        view.contentPet.etPetParticularSigns.setText(pet.getPetParticularSigns());

        if (pet.getPetMicroChip() == 't')
            view.contentPet.cbPetMicroChip.setChecked(true);
        if (pet.getPetTattoo() == 't')
            view.contentPet.cbPetTattoo.setChecked(true);

        ownerAdapter.setPetList(new ArrayList<>(Collections.singletonList(pet)));

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(pet.getPetName());
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
        component = app.getAddEditPetComponent(this, this, this);
        presenter = getPresenter();
        ownerAdapter = getOwnerAdapter();
        vaccineAdapter = getVaccineAdapter();
    }

    private void setupOwnerRecyclerView() {
        view.contentPet.recyclerviewOwner.setLayoutManager(new LinearLayoutManager(this));
        view.contentPet.recyclerviewOwner.setAdapter(ownerAdapter);
    }

    private void setupVaccineRecyclerView() {
        view.contentPet.recyclerviewVaccine.setLayoutManager(new LinearLayoutManager(this));
        view.contentPet.recyclerviewVaccine.setAdapter(vaccineAdapter);
    }

    private void setupClickListeners() {
        view.fabSavePet.setOnClickListener(
                v -> saveEditPet(actionType)
        );
        view.contentPet.btnAddOwner.setOnClickListener(
                v -> navigateToAddEditOwnerScreen()
        );
        view.contentPet.btnBirthday.setOnClickListener(
                v -> showDatePicker()
        );
        view.contentPet.btnAddVaccine.setOnClickListener(
                v -> navigateToAddEditVaccineScreen()
        );

    }
    
    private void onOwnerItemClick(Owner owner, View adapterElement){
        this.ownerId = owner.getOwnerId();

        if (previous != null)
            previous.setSelected(false);

        previous = adapterElement;
        adapterElement.setSelected(true);

        CrashlyticsLogger.logButton("Owner List Adapter Item", TAG);
    }

    private void navigateToAddEditVaccineScreen() {
        Intent intent = new Intent(this, AddEditVaccineActivity.class);
        intent.putExtra(Flags.IntentKeys.ACTION_TYPE, Flags.ActionType.SAVE_NEW);
        intent.putExtra(Flags.IntentKeys.PET_ID, petId);

        CrashlyticsLogger.logButton("Navigate To AddEditVaccine Screen", TAG);


        startActivity(intent);
    }

    private void navigateToAddEditOwnerScreen() {
        Intent intent = new Intent(this, AddEditOwnerActivity.class);
        intent.putExtra(Flags.IntentKeys.ACTION_TYPE, Flags.ActionType.SAVE_NEW);

        CrashlyticsLogger.logButton("Navigate To AddEditOwner Screen", TAG);

        startActivity(intent);
    }

    private void fetchData() {
        Bundle extras = getIntent().getExtras();
        if (extras == null)
            return;

        actionType = (Flags.ActionType)extras.getSerializable(Flags.IntentKeys.ACTION_TYPE);
        petId = extras.getInt(Flags.IntentKeys.PET_ID);
    }

    private ListAdapter getOwnerAdapter() {
        return component.getOwnerAdapter();
    }

    private VaccineAdapter getVaccineAdapter() {
        return component.getVaccineAdapter();
    }

    private AddEditPetPresenter getPresenter() {
        return component.getPresenter();
    }

    private void saveEditPet(Flags.ActionType actionType) {
        Pet pet = new Pet();

        if (view.contentPet.etPetName.getText() != null)
            pet.setPetName(view.contentPet.etPetName.getText().toString());
        if (view.contentPet.etPetKind.getText() != null)
            pet.setPetKind(view.contentPet.etPetKind.getText().toString());
        if (view.contentPet.etPetBreed.getText() != null)
            pet.setPetBreed(view.contentPet.etPetBreed.getText().toString());
        if (view.contentPet.etPetColor.getText() != null)
            pet.setPetColor(view.contentPet.etPetColor.getText().toString());
        if (view.contentPet.etPetIdNumber.getText() != null)
            pet.setPetIdNumber(view.contentPet.etPetIdNumber.getText().toString());
        if (view.contentPet.etPetParticularSigns.getText() != null)
            pet.setPetParticularSigns(view.contentPet.etPetParticularSigns.getText().toString());

        if (view.contentPet.cbPetMicroChip.isChecked())
            pet.setPetMicroChip('t');
        else
            pet.setPetMicroChip('f');

        if (view.contentPet.cbPetTattoo.isChecked())
            pet.setPetTattoo('t');
        else
            pet.setPetTattoo('f');

        if (view.contentPet.rbSexFemale.isChecked() || view.contentPet.rbSexMale.isChecked()){
            if (view.contentPet.rbSexMale.isChecked())
                pet.setPetSex(getString(R.string.add_edit_pet_activity_rb_pet_sex_male));
            else
                pet.setPetSex(getString(R.string.add_edit_pet_activity_rb_pet_sex_female));
        }

        if (view.contentPet.etBirthday.getText() != null)
            pet.setPetBirthday(view.contentPet.etBirthday.getText().toString());

        pet.setOwnerId(ownerId);

        switch (actionType){
            case SAVE_NEW:
                CrashlyticsLogger.logButton("Save Pet", TAG);
                presenter.savePet(pet);
                break;
            case EDIT:
                CrashlyticsLogger.logButton("Edit Pet", TAG);
                pet.setPetId(petId);
                presenter.updatePet(pet);
                break;
        }
    }

    private void showDatePicker() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();

        String sDate[] = view.contentPet.etBirthday.getText().toString().split("/");

        if (sDate[0].contains("d")) {
            datePickerFragment.setDate(new int[]{});
        } else {
            int day =  Integer.parseInt(sDate[0]);
            int month =  Integer.parseInt(sDate[1]);
            int year =  Integer.parseInt(sDate[2]);
            datePickerFragment.setDate(new int[]{day, month-1, year});
        }

        datePickerFragment.setEtDate(view.contentPet.etBirthday);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");

        CrashlyticsLogger.logButton("Show Date Picker", TAG);
    }

    private void checkForActionType() {
        switch (actionType) {
            case EDIT:
                presenter.getOwnerList();
                presenter.getPet(petId);
                break;
            case SAVE_NEW:

                break;
        }
    }

    private void shouldShowVaccineList(int petId) {
        if (petId == -1)
            view.contentPet.lyVaccines.setVisibility(View.GONE);
        else
            view.contentPet.lyVaccines.setVisibility(View.VISIBLE);
    }

}
