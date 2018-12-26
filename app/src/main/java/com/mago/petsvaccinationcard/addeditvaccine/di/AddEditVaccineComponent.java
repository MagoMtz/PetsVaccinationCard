package com.mago.petsvaccinationcard.addeditvaccine.di;

import com.mago.petsvaccinationcard.addeditvaccine.ui.presenter.AddEditVaccinePresenter;
import com.mago.petsvaccinationcard.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jorgemartinez on 21/12/18.
 */
@Singleton
@Component(modules = {AddEditVaccineModule.class, LibsModule.class})
public interface AddEditVaccineComponent {
    AddEditVaccinePresenter getPresenter();
}
