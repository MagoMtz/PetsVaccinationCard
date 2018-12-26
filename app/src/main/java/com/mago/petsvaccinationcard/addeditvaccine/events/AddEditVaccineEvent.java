package com.mago.petsvaccinationcard.addeditvaccine.events;


import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public class AddEditVaccineEvent {
    private int type;
    private Vaccine vaccine;

    public static final int READ_EVENT = 0;
    public static final int UPDATE_EVENT = 1;
    public static final int SAVE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }
}
