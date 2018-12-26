package com.mago.petsvaccinationcard.addeditpet.events;

import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.entities.Vaccine;

import java.util.ArrayList;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public class AddEditPetEvent {
    private int type;
    private ArrayList<Owner> ownerList;
    private ArrayList<Vaccine> vaccineList;
    private Pet pet;

    public static final int READ_EVENT = 0;
    public static final int UPDATE_EVENT = 1;
    public static final int SAVE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(ArrayList<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public ArrayList<Vaccine> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(ArrayList<Vaccine> vaccineList) {
        this.vaccineList = vaccineList;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
