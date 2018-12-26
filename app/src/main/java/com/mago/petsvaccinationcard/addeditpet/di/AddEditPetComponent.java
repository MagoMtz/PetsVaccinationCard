package com.mago.petsvaccinationcard.addeditpet.di;

import com.mago.petsvaccinationcard.adapter.ListAdapter;
import com.mago.petsvaccinationcard.adapter.VaccineAdapter;
import com.mago.petsvaccinationcard.addeditpet.ui.presenter.AddEditPetPresenter;
import com.mago.petsvaccinationcard.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jorgemartinez on 18/12/18.
 */
@Singleton
@Component(modules = {AddEditPetModule.class, LibsModule.class})
public interface AddEditPetComponent {
    ListAdapter getOwnerAdapter();
    VaccineAdapter getVaccineAdapter();
    AddEditPetPresenter getPresenter();
}
