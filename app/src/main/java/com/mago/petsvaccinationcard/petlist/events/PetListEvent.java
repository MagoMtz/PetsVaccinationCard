package com.mago.petsvaccinationcard.petlist.events;

import com.mago.petsvaccinationcard.entities.Pet;

import java.util.ArrayList;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class PetListEvent {
    private int type;

    private ArrayList<Pet> petList;

    public static final int READ_EVENT = 0;
    public static final int UPDATE_EVENT = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Pet> getPetList() {
        return petList;
    }

    public void setPetList(ArrayList<Pet> petList) {
        this.petList = petList;
    }
}
