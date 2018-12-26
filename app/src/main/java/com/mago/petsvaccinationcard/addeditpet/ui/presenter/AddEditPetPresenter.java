package com.mago.petsvaccinationcard.addeditpet.ui.presenter;

import com.mago.petsvaccinationcard.addeditpet.events.AddEditPetEvent;
import com.mago.petsvaccinationcard.addeditpet.ui.view.AddEditPetView;
import com.mago.petsvaccinationcard.entities.Pet;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public interface AddEditPetPresenter {
    void onCreate();
    void onDestroy();

    void getPet(int petId);
    void getOwnerList();
    void getVaccineList(int petId);
    void savePet(Pet pet);
    void updatePet(Pet pet);
    void onEventMainThread(AddEditPetEvent event);

    AddEditPetView getView();
}
