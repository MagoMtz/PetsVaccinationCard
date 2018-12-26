package com.mago.petsvaccinationcard.petlist.ui.view;

import com.mago.petsvaccinationcard.entities.Pet;

import java.util.ArrayList;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public interface PetListView {
    void setPets(ArrayList<Pet> data);
    void petUpdated();
}
