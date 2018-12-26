package com.mago.petsvaccinationcard.addeditpet.ui.interactor;

import com.mago.petsvaccinationcard.addeditpet.ui.repository.AddEditPetRepository;
import com.mago.petsvaccinationcard.entities.Pet;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public class PetInteractorImpl implements PetInteractor {
    private AddEditPetRepository repository;

    public PetInteractorImpl(AddEditPetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeSave(Pet pet) {
        repository.savePet(pet);
    }

    @Override
    public void executeUpdate(Pet pet) {
        repository.updatePet(pet);
    }

    @Override
    public void executeRead(int petId) {
        repository.getPet(petId);
    }
}
