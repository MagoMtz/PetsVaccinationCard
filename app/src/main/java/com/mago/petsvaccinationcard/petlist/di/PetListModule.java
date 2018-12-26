package com.mago.petsvaccinationcard.petlist.di;

import android.content.Context;

import com.mago.petsvaccinationcard.adapter.ListAdapter;
import com.mago.petsvaccinationcard.adapter.OnItemClickListener;
import com.mago.petsvaccinationcard.db.dao.PetDAO;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.libs.base.EventBus;
import com.mago.petsvaccinationcard.libs.base.ImageLoader;
import com.mago.petsvaccinationcard.petlist.ui.interactor.PetListInteractor;
import com.mago.petsvaccinationcard.petlist.ui.interactor.PetListInteractorImpl;
import com.mago.petsvaccinationcard.petlist.ui.interactor.StoredPetInteractor;
import com.mago.petsvaccinationcard.petlist.ui.interactor.StoredPetInteractorImpl;
import com.mago.petsvaccinationcard.petlist.ui.presenter.PetListPresenter;
import com.mago.petsvaccinationcard.petlist.ui.presenter.PetListPresenterImpl;
import com.mago.petsvaccinationcard.petlist.ui.repository.PetListRepository;
import com.mago.petsvaccinationcard.petlist.ui.repository.PetListRepositoryImpl;
import com.mago.petsvaccinationcard.petlist.ui.view.PetListView;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Module
public class PetListModule {
    private PetListView view;
    private OnItemClickListener onItemClickListener;

    public PetListModule(PetListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    PetListView providesPetListView() {
        return view;
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return onItemClickListener;
    }

    @Provides
    @Singleton
    PetListPresenter providesPetListPresenter(EventBus eventBus, PetListView view, PetListInteractor interactor, StoredPetInteractor storedInteractor) {
        return new PetListPresenterImpl(eventBus, view, interactor, storedInteractor);
    }

    @Provides
    @Singleton
    PetListInteractor providesPetListInteractor(PetListRepository repository) {
        return new PetListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    StoredPetInteractor providesStoredPetInteractor(PetListRepository repository) {
        return new StoredPetInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PetListRepository providesPetListRepository(EventBus eventBus, PetDAO petDAO) {
        return new PetListRepositoryImpl(eventBus, petDAO);
    }


}
