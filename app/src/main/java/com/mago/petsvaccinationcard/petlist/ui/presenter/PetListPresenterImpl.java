package com.mago.petsvaccinationcard.petlist.ui.presenter;

import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.libs.base.EventBus;
import com.mago.petsvaccinationcard.petlist.events.PetListEvent;
import com.mago.petsvaccinationcard.petlist.ui.interactor.PetListInteractor;
import com.mago.petsvaccinationcard.petlist.ui.interactor.StoredPetInteractor;
import com.mago.petsvaccinationcard.petlist.ui.view.PetListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class PetListPresenterImpl implements PetListPresenter{
    private EventBus eventBus;
    private PetListView view;
    private PetListInteractor interactor;
    private StoredPetInteractor storedInteractor;

    public PetListPresenterImpl(EventBus eventBus, PetListView view, PetListInteractor interactor, StoredPetInteractor storedInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
        this.storedInteractor = storedInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getPet() {
        interactor.execute();
    }

    @Override
    public void removePet(Pet pet) {

    }

    @Override
    public void changeImgPet(Pet pet) {
        storedInteractor.executeUpdate(pet);
    }

    @Override
    @Subscribe
    public void onEventMainThread(PetListEvent event) {
        if (view == null)
            return;
        switch (event.getType()){
            case PetListEvent.READ_EVENT:
                view.setPets(event.getPetList());
                break;
            case PetListEvent.UPDATE_EVENT:
                view.petUpdated();
                break;
        }
    }

    @Override
    public PetListView getView() {
        return view;
    }
}
