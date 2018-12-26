package com.mago.petsvaccinationcard.addeditowner.ui.presenter;

import com.mago.petsvaccinationcard.addeditowner.events.AddEditOwnerEvent;
import com.mago.petsvaccinationcard.addeditowner.ui.view.AddEditOwnerView;
import com.mago.petsvaccinationcard.entities.Owner;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public interface AddEditOwnerPresenter {
    void onCreate();
    void onDestroy();

    void getOwner(int ownerId);
    void saveOwner(Owner owner);
    void updateOwner(Owner owner);
    void onEventMainThread(AddEditOwnerEvent event);

    AddEditOwnerView getView();
}
