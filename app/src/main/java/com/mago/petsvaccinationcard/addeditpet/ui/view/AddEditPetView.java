package com.mago.petsvaccinationcard.addeditpet.ui.view;

import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.entities.Vaccine;

import java.util.ArrayList;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public interface AddEditPetView {
    void setOwnerList(ArrayList<Owner> data);
    void setVaccineList(ArrayList<Vaccine> data);
    void setNotNullError(Pet pet);
    void onPetSaved(Pet pet);
    void onPetUpdated();
    void setPetInfo(Pet pet);
}
