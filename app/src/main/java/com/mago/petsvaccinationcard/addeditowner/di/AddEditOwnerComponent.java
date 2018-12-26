package com.mago.petsvaccinationcard.addeditowner.di;

import com.mago.petsvaccinationcard.addeditowner.ui.presenter.AddEditOwnerPresenter;
import com.mago.petsvaccinationcard.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jorgemartinez on 19/12/18.
 */
@Singleton
@Component(modules = {AddEditOwnerModule.class, LibsModule.class})
public interface AddEditOwnerComponent {
    AddEditOwnerPresenter getPresenter();
}
