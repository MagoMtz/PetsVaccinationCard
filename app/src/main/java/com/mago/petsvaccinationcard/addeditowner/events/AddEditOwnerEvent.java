package com.mago.petsvaccinationcard.addeditowner.events;

import com.mago.petsvaccinationcard.entities.Owner;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public class AddEditOwnerEvent {
    private int type;
    private Owner owner;

    public static final int READ_EVENT = 0;
    public static final int UPDATE_EVENT = 1;
    public static final int SAVE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
