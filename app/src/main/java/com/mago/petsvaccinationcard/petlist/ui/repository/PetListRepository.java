package com.mago.petsvaccinationcard.petlist.ui.repository;

import com.mago.petsvaccinationcard.entities.Pet;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public interface PetListRepository {
    void getSavedPets();
    void updatePet(Pet pet);
    void removePet(Pet pet);
}
