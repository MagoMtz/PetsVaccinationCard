package com.mago.petsvaccinationcard.addeditowner.di;

import com.mago.petsvaccinationcard.addeditowner.ui.interactor.OwnerInteractor;
import com.mago.petsvaccinationcard.addeditowner.ui.interactor.OwnerInteractorImpl;
import com.mago.petsvaccinationcard.addeditowner.ui.presenter.AddEditOwnerPresenter;
import com.mago.petsvaccinationcard.addeditowner.ui.presenter.AddEditOwnerPresenterImpl;
import com.mago.petsvaccinationcard.addeditowner.ui.repository.AddEditOwnerRepository;
import com.mago.petsvaccinationcard.addeditowner.ui.repository.AddEditOwnerRepositoryImpl;
import com.mago.petsvaccinationcard.addeditowner.ui.view.AddEditOwnerView;
import com.mago.petsvaccinationcard.db.dao.OwnerDAO;
import com.mago.petsvaccinationcard.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgemartinez on 19/12/18.
 */
@Module
public class AddEditOwnerModule {
    private AddEditOwnerView view;

    public AddEditOwnerModule(AddEditOwnerView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    AddEditOwnerView providesAddEditOwnerView() {
        return view;
    }

    @Singleton
    @Provides
    AddEditOwnerPresenter providesAddEditOwnerPresenter(EventBus eventBus, AddEditOwnerView view, OwnerInteractor interactor) {
        return new AddEditOwnerPresenterImpl(eventBus, view, interactor);
    }

    @Singleton
    @Provides
    OwnerInteractor providesOwnerInteractor(AddEditOwnerRepository repository) {
        return new OwnerInteractorImpl(repository);
    }

    @Singleton
    @Provides
    AddEditOwnerRepository providesAddEditOwnerRepository(EventBus eventBus, OwnerDAO ownerDAO) {
        return new AddEditOwnerRepositoryImpl(eventBus, ownerDAO);
    }

}
