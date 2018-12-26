package com.mago.petsvaccinationcard.petlist.ui.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.mago.petsvaccinationcard.PetsVaccinationApp;
import com.mago.petsvaccinationcard.R;
import com.mago.petsvaccinationcard.adapter.ListAdapter;
import com.mago.petsvaccinationcard.adapter.OnItemClickListener;
import com.mago.petsvaccinationcard.addeditpet.ui.view.AddEditPetActivity;
import com.mago.petsvaccinationcard.databinding.ActivityPetListBinding;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.petlist.di.PetListComponent;
import com.mago.petsvaccinationcard.petlist.ui.presenter.PetListPresenter;
import com.mago.petsvaccinationcard.util.CrashlyticsLogger;
import com.mago.petsvaccinationcard.util.Flags;

import java.util.ArrayList;

public class PetListActivity extends AppCompatActivity implements PetListView, OnItemClickListener {
    private final static String TAG = PetListActivity.class.getSimpleName();
    private ActivityPetListBinding view;

    private PetListComponent component;
    private ListAdapter adapter;
    private PetListPresenter presenter;

    private int petId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this, R.layout.activity_pet_list);
        petId = -1;

        setupToolbar();
        setupInjection();
        setupRecyclerView();

        setupClickListeners();

        presenter.onCreate();
        CrashlyticsLogger.logActivity(TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getPet();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //method from the adapter
    @Override
    public void onItemClick(Object object, View view, int position) {
        Pet pet = (Pet)object;

        navigateToAddEditPetScreen(Flags.ActionType.EDIT, pet.getPetId());

        CrashlyticsLogger.logButton("Pet List Adapter Item", TAG);
    }

    @Override
    public void setPets(ArrayList<Pet> data) {
        if (data.size() == 0)
            view.tvNotPetsMsg.setVisibility(View.VISIBLE);
        else
            view.tvNotPetsMsg.setVisibility(View.GONE);
        adapter.setPetList(data);
    }

    @Override
    public void petUpdated() {
        adapter.notifyDataSetChanged();
    }
    
    private void setupClickListeners() {
        view.fabAddPet.setOnClickListener(v -> navigateToAddEditPetScreen(Flags.ActionType.SAVE_NEW, petId));
    }

    private void setupToolbar() {
        setSupportActionBar(view.toolbar);
    }

    private void setupInjection() {
        PetsVaccinationApp app =(PetsVaccinationApp)getApplication();
        component = app.getPetListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    private void setupRecyclerView() {
        adapter.setListType(Flags.ListType.PET);

        view.contentRecyclerView.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        view.contentRecyclerView.recyclerview.setAdapter(adapter);
    }

    private void navigateToAddEditPetScreen(Flags.ActionType actionType, int petId) {
        Intent intent = new Intent(this, AddEditPetActivity.class);
        intent.putExtra(Flags.IntentKeys.ACTION_TYPE, actionType);
        intent.putExtra(Flags.IntentKeys.PET_ID, petId);

        switch (actionType){
            case SAVE_NEW:
                CrashlyticsLogger.logButton("Navigate To AddEditPet Screen", TAG);
                break;
        }

        startActivity(intent);
    }

    private ListAdapter getAdapter() {
        return component.getAdapter();
    }

    private PetListPresenter getPresenter() {
        return component.getPresenter();
    }

    
}
