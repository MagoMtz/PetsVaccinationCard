package com.mago.petsvaccinationcard.addeditvaccine.ui.view;


import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public interface AddEditVaccineView {
    void setVaccineInfo(Vaccine vaccine);
    void setNotNullError(Vaccine vaccine);
    void onVaccineSaved();
    void onVaccineUpdated();
}
