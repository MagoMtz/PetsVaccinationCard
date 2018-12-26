package com.mago.petsvaccinationcard.petlist.di;

import com.mago.petsvaccinationcard.adapter.ListAdapter;
import com.mago.petsvaccinationcard.libs.di.LibsModule;
import com.mago.petsvaccinationcard.petlist.ui.presenter.PetListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Singleton
@Component(modules = {PetListModule.class, LibsModule.class})
public interface PetListComponent {
    ListAdapter getAdapter();
    PetListPresenter getPresenter();
}
