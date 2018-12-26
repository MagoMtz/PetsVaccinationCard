package com.mago.petsvaccinationcard.addeditowner.ui.interactor;

import com.mago.petsvaccinationcard.entities.Owner;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public interface OwnerInteractor {
    void executeSave(Owner owner);
    void executeUpdate(Owner owner);
    void executeRead(int ownerId);
}
