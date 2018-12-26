package com.mago.petsvaccinationcard.addeditpet.ui.repository;

import com.mago.petsvaccinationcard.entities.Pet;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public interface AddEditPetRepository {
    void savePet(Pet pet);
    void updatePet(Pet pet);
    void getPet(int petId);
    void getSavedOwners();
    void getVaccinesOfPet(int petId);
}
