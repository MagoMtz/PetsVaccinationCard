package com.mago.petsvaccinationcard.addeditvaccine.ui.interactor;

import com.mago.petsvaccinationcard.addeditvaccine.ui.repository.AddEditVaccineRepository;
import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public class VaccineInteractorImpl implements VaccineInteractor{
    private AddEditVaccineRepository repository;

    public VaccineInteractorImpl(AddEditVaccineRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeSave(Vaccine vaccine) {
        repository.saveVaccine(vaccine);
    }

    @Override
    public void executeUpdate(Vaccine vaccine) {
        repository.updateVaccine(vaccine);
    }

    @Override
    public void executeRead(int vaccineId) {
        repository.getVaccine(vaccineId);
    }
}
