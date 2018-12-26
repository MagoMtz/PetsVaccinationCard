package com.mago.petsvaccinationcard.addeditowner.ui.view;

import com.mago.petsvaccinationcard.entities.Owner;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public interface AddEditOwnerView {
    void setOwnerInfo(Owner owner);
    void setNotNullError(Owner owner);
    void onOwnerSaved();
    void onOwnerUpdated();
}
