package com.mago.petsvaccinationcard.petlist.ui.interactor;

import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.petlist.ui.repository.PetListRepository;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class StoredPetInteractorImpl implements StoredPetInteractor{
    private PetListRepository repository;

    public StoredPetInteractorImpl(PetListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeUpdate(Pet pet) {
        repository.updatePet(pet);
    }
}
