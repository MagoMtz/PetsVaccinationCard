package com.mago.petsvaccinationcard.addeditowner.ui.repository;

import com.mago.petsvaccinationcard.entities.Owner;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public interface AddEditOwnerRepository {
    void saveOwner(Owner owner);
    void updateOwner(Owner owner);
    void getOwner(int ownerId);
}
