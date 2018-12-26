package com.mago.petsvaccinationcard.addeditpet.di;

import com.mago.petsvaccinationcard.adapter.OnItemClickListener;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.OwnerListInteractor;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.OwnerListInteractorImpl;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.PetInteractor;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.PetInteractorImpl;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.VaccineListInteractor;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.VaccineListInteractorImpl;
import com.mago.petsvaccinationcard.addeditpet.ui.presenter.AddEditPetPresenter;
import com.mago.petsvaccinationcard.addeditpet.ui.presenter.AddEditPetPresenterImpl;
import com.mago.petsvaccinationcard.addeditpet.ui.repository.AddEditPetRepository;
import com.mago.petsvaccinationcard.addeditpet.ui.repository.AddEditPetRepositoryImpl;
import com.mago.petsvaccinationcard.addeditpet.ui.view.AddEditPetView;
import com.mago.petsvaccinationcard.db.dao.OwnerDAO;
import com.mago.petsvaccinationcard.db.dao.PetDAO;
import com.mago.petsvaccinationcard.db.dao.VaccineDAO;
import com.mago.petsvaccinationcard.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgemartinez on 18/12/18.
 */
@Module
public class AddEditPetModule {
    private AddEditPetView view;
    private OnItemClickListener onItemClickListener;

    public AddEditPetModule(AddEditPetView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    AddEditPetView providesView() {
        return view;
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return onItemClickListener;
    }

    @Provides
    @Singleton
    AddEditPetPresenter providesAddEditPetPresenter(EventBus eventBus, AddEditPetView view, VaccineListInteractor vaccineListInteractor, OwnerListInteractor ownerListInteractor, PetInteractor petInteractor) {
        return new AddEditPetPresenterImpl(eventBus, view, vaccineListInteractor, ownerListInteractor, petInteractor);
    }

    @Provides
    @Singleton
    OwnerListInteractor providesOwnerListInteractor(AddEditPetRepository repository) {
        return new OwnerListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    VaccineListInteractor providesVaccineListInteractor(AddEditPetRepository repository) {
        return new VaccineListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PetInteractor providesPetInteractor(AddEditPetRepository repository) {
        return new PetInteractorImpl(repository);
    }

    @Provides
    @Singleton
    AddEditPetRepository providesAddEditPetRepository(EventBus eventBus, PetDAO petDAO, OwnerDAO ownerDAO, VaccineDAO vaccineDAO) {
        return new AddEditPetRepositoryImpl(eventBus, petDAO, ownerDAO, vaccineDAO);
    }

}
