package com.mago.petsvaccinationcard.addeditvaccine.ui.interactor;

import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public interface VaccineInteractor {
    void executeSave(Vaccine vaccine);
    void executeUpdate(Vaccine vaccine);
    void executeRead(int vaccineId);
}
