package com.mago.petsvaccinationcard.addeditvaccine.ui.repository;

import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public interface AddEditVaccineRepository {
    void saveVaccine(Vaccine vaccine);
    void updateVaccine(Vaccine vaccine);
    void getVaccine(int vaccineId);
}
