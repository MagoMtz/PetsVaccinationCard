package com.mago.petsvaccinationcard.addeditvaccine.ui.presenter;

import com.mago.petsvaccinationcard.addeditvaccine.events.AddEditVaccineEvent;
import com.mago.petsvaccinationcard.addeditvaccine.ui.interactor.VaccineInteractor;
import com.mago.petsvaccinationcard.addeditvaccine.ui.view.AddEditVaccineView;
import com.mago.petsvaccinationcard.entities.Vaccine;
import com.mago.petsvaccinationcard.libs.base.EventBus;
import com.mago.petsvaccinationcard.util.StringValidator;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public class AddEditVaccinePresenterImpl implements AddEditVaccinePresenter{
    private EventBus eventBus;
    private AddEditVaccineView view;
    private VaccineInteractor interactor;

    public AddEditVaccinePresenterImpl(EventBus eventBus, AddEditVaccineView view, VaccineInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getVaccine(int vaccineId) {
        interactor.executeRead(vaccineId);
    }

    @Override
    public void saveVaccine(Vaccine vaccine) {
        boolean success = true;

        if (StringValidator.isNullOrEmpty(vaccine.getVaccineName()))
            success = false;
        if (!StringValidator.isValidDate(vaccine.getVaccineDate()))
            success = false;
        if (StringValidator.isNullOrEmpty(vaccine.getVaccineNextAppointment()))
            success = false;

        if (!success){
            view.setNotNullError(vaccine);
            return;
        }

        view.setNotNullError(vaccine);
        interactor.executeSave(vaccine);
    }

    @Override
    public void updateVaccine(Vaccine vaccine) {
        interactor.executeUpdate(vaccine);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddEditVaccineEvent event) {
        if (view == null)
            return;

        switch (event.getType()) {
            case AddEditVaccineEvent.READ_EVENT:
                view.setVaccineInfo(event.getVaccine());
                break;
            case AddEditVaccineEvent.UPDATE_EVENT:
                view.onVaccineUpdated();
                break;
            case AddEditVaccineEvent.SAVE_EVENT:
                view.onVaccineSaved();
                break;
        }
    }

    @Override
    public AddEditVaccineView getView() {
        return view;
    }
}
