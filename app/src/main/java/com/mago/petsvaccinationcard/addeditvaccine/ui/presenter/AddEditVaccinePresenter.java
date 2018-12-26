package com.mago.petsvaccinationcard.addeditvaccine.ui.presenter;

import com.mago.petsvaccinationcard.addeditvaccine.events.AddEditVaccineEvent;
import com.mago.petsvaccinationcard.addeditvaccine.ui.view.AddEditVaccineView;
import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public interface AddEditVaccinePresenter {
    void onCreate();
    void onDestroy();

    void getVaccine(int vaccineId);
    void saveVaccine(Vaccine vaccine);
    void updateVaccine(Vaccine vaccine);
    void onEventMainThread(AddEditVaccineEvent event);

    AddEditVaccineView getView();
}
