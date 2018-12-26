package com.mago.petsvaccinationcard.addeditpet.ui.interactor;

import com.mago.petsvaccinationcard.addeditpet.ui.repository.AddEditPetRepository;

/**
 * Created by jorgemartinez on 24/12/18.
 */
public class VaccineListInteractorImpl implements VaccineListInteractor{
    private AddEditPetRepository repository;

    public VaccineListInteractorImpl(AddEditPetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeRead(int petId) {
        repository.getVaccinesOfPet(petId);
    }
}
