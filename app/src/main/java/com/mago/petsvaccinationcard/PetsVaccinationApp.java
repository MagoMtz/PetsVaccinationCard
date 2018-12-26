package com.mago.petsvaccinationcard;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.mago.petsvaccinationcard.adapter.OnItemClickListener;
import com.mago.petsvaccinationcard.addeditowner.di.AddEditOwnerComponent;
import com.mago.petsvaccinationcard.addeditowner.di.AddEditOwnerModule;
import com.mago.petsvaccinationcard.addeditowner.di.DaggerAddEditOwnerComponent;
import com.mago.petsvaccinationcard.addeditowner.ui.view.AddEditOwnerActivity;
import com.mago.petsvaccinationcard.addeditowner.ui.view.AddEditOwnerView;
import com.mago.petsvaccinationcard.addeditpet.di.AddEditPetComponent;
import com.mago.petsvaccinationcard.addeditpet.di.AddEditPetModule;
import com.mago.petsvaccinationcard.addeditpet.di.DaggerAddEditPetComponent;
import com.mago.petsvaccinationcard.addeditpet.ui.view.AddEditPetActivity;
import com.mago.petsvaccinationcard.addeditpet.ui.view.AddEditPetView;
import com.mago.petsvaccinationcard.addeditvaccine.di.AddEditVaccineComponent;
import com.mago.petsvaccinationcard.addeditvaccine.di.AddEditVaccineModule;
import com.mago.petsvaccinationcard.addeditvaccine.di.DaggerAddEditVaccineComponent;
import com.mago.petsvaccinationcard.addeditvaccine.ui.view.AddEditVaccineActivity;
import com.mago.petsvaccinationcard.addeditvaccine.ui.view.AddEditVaccineView;
import com.mago.petsvaccinationcard.db.AppDataBase;
import com.mago.petsvaccinationcard.libs.di.LibsModule;
import com.mago.petsvaccinationcard.petlist.di.DaggerPetListComponent;
import com.mago.petsvaccinationcard.petlist.di.PetListComponent;
import com.mago.petsvaccinationcard.petlist.di.PetListModule;
import com.mago.petsvaccinationcard.petlist.ui.view.PetListActivity;
import com.mago.petsvaccinationcard.petlist.ui.view.PetListView;

import io.fabric.sdk.android.Fabric;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class PetsVaccinationApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initCrashlytics();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppDataBase.destroyINSTANCE();
    }

    private void initCrashlytics() {
        if (BuildConfig.DEBUG){
            Log.w("PetsVaccinationApp", " Build config is debug, fabric will not be tracked");
            return;
        }
        Fabric.with(this, new Crashlytics());
        Fabric.with(this, new Answers());
    }

    public PetListComponent getPetListComponent(PetListActivity activity, PetListView view, OnItemClickListener onItemClickListener) {
        return DaggerPetListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .petListModule(new PetListModule(view, onItemClickListener))
                .build();
    }

    public AddEditPetComponent getAddEditPetComponent(AddEditPetActivity activity, AddEditPetView view, OnItemClickListener onItemClickListener) {
        return DaggerAddEditPetComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .addEditPetModule(new AddEditPetModule(view, onItemClickListener))
                .build();
    }

    public AddEditOwnerComponent getAddEditOwnerComponent(AddEditOwnerActivity activity, AddEditOwnerView view) {
        return DaggerAddEditOwnerComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .addEditOwnerModule(new AddEditOwnerModule(view))
                .build();
    }

    public AddEditVaccineComponent getAddEditVaccineComponent(AddEditVaccineActivity activity, AddEditVaccineView view) {
        return DaggerAddEditVaccineComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .addEditVaccineModule(new AddEditVaccineModule(view))
                .build();
    }
}
