package com.mago.petsvaccinationcard.addeditpet.ui.interactor;

import com.mago.petsvaccinationcard.entities.Pet;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public interface PetInteractor {
    void executeSave(Pet pet);
    void executeUpdate(Pet pet);
    void executeRead(int petId);
}
