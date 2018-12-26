package com.mago.petsvaccinationcard.petlist.ui.presenter;

import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.petlist.events.PetListEvent;
import com.mago.petsvaccinationcard.petlist.ui.view.PetListView;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public interface PetListPresenter {
    void onCreate();
    void onDestroy();

    void getPet();
    void removePet(Pet pet);
    void changeImgPet(Pet pet);
    void onEventMainThread(PetListEvent event);

    PetListView getView();
}
