package com.mago.petsvaccinationcard.addeditvaccine.di;

import com.mago.petsvaccinationcard.addeditvaccine.ui.interactor.VaccineInteractor;
import com.mago.petsvaccinationcard.addeditvaccine.ui.interactor.VaccineInteractorImpl;
import com.mago.petsvaccinationcard.addeditvaccine.ui.presenter.AddEditVaccinePresenter;
import com.mago.petsvaccinationcard.addeditvaccine.ui.presenter.AddEditVaccinePresenterImpl;
import com.mago.petsvaccinationcard.addeditvaccine.ui.repository.AddEditVaccineRepository;
import com.mago.petsvaccinationcard.addeditvaccine.ui.repository.AddEditVaccineRepositoryImpl;
import com.mago.petsvaccinationcard.addeditvaccine.ui.view.AddEditVaccineView;
import com.mago.petsvaccinationcard.db.dao.VaccineDAO;
import com.mago.petsvaccinationcard.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jorgemartinez on 21/12/18.
 */
@Module
public class AddEditVaccineModule {
    private AddEditVaccineView view;

    public AddEditVaccineModule(AddEditVaccineView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    AddEditVaccineView providesAddEditVaccineView() {
        return view;
    }

    @Singleton
    @Provides
    AddEditVaccinePresenter providesAddEditVaccinePresenter(EventBus eventBus, AddEditVaccineView view, VaccineInteractor interactor) {
        return new AddEditVaccinePresenterImpl(eventBus, view, interactor);
    }

    @Singleton
    @Provides
    VaccineInteractor providesVaccineInteractor(AddEditVaccineRepository repository) {
        return new VaccineInteractorImpl(repository);
    }

    @Singleton
    @Provides
    AddEditVaccineRepository providesAddEditVaccineRepository(EventBus eventBus, VaccineDAO vaccineDAO) {
        return new AddEditVaccineRepositoryImpl(eventBus, vaccineDAO);
    }
}
