package com.mago.petsvaccinationcard.addeditpet.ui.presenter;

import com.mago.petsvaccinationcard.addeditpet.events.AddEditPetEvent;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.OwnerListInteractor;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.PetInteractor;
import com.mago.petsvaccinationcard.addeditpet.ui.interactor.VaccineListInteractor;
import com.mago.petsvaccinationcard.addeditpet.ui.view.AddEditPetView;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.libs.base.EventBus;
import com.mago.petsvaccinationcard.util.StringValidator;

import org.greenrobot.eventbus.Subscribe;

import static com.mago.petsvaccinationcard.addeditpet.events.AddEditPetEvent.READ_EVENT;
import static com.mago.petsvaccinationcard.addeditpet.events.AddEditPetEvent.SAVE_EVENT;
import static com.mago.petsvaccinationcard.addeditpet.events.AddEditPetEvent.UPDATE_EVENT;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public class AddEditPetPresenterImpl implements AddEditPetPresenter {
    private EventBus eventBus;
    private AddEditPetView view;
    private OwnerListInteractor ownerListInteractor;
    private VaccineListInteractor vaccineListInteractor;
    private PetInteractor petInteractor;

    public AddEditPetPresenterImpl(EventBus eventBus, AddEditPetView view, VaccineListInteractor vaccineListInteractor, OwnerListInteractor ownerListInteractor, PetInteractor petInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.ownerListInteractor = ownerListInteractor;
        this.vaccineListInteractor = vaccineListInteractor;
        this.petInteractor = petInteractor;
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
    public void getPet(int petId) {
        petInteractor.executeRead(petId);
    }

    @Override
    public void getOwnerList() {
        ownerListInteractor.executeRead();
    }

    @Override
    public void getVaccineList(int petId) {
        vaccineListInteractor.executeRead(petId);
    }

    @Override
    public void savePet(Pet pet) {
        boolean success = true;

        if (StringValidator.isNullOrEmpty(pet.getPetName()))
            success = false;
        if (StringValidator.isNullOrEmpty(pet.getPetSex()))
            success = false;
        if (!StringValidator.isValidDate(pet.getPetBirthday()))
            success = false;
        if (pet.getOwnerId() == -1)
            success = false;

        if (!success) {
            view.setNotNullError(pet);
            return;
        }

        view.setNotNullError(pet);
        petInteractor.executeSave(pet);
    }

    @Override
    public void updatePet(Pet pet) {
        boolean success = true;

        if (StringValidator.isNullOrEmpty(pet.getPetName()))
            success = false;
        if (StringValidator.isNullOrEmpty(pet.getPetSex()))
            success = false;
        if (!StringValidator.isValidDate(pet.getPetBirthday()))
            success = false;
        if (pet.getOwnerId() == -1)
            success = false;

        if (!success) {
            view.setNotNullError(pet);
            return;
        }

        view.setNotNullError(pet);
        petInteractor.executeUpdate(pet);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddEditPetEvent event) {
        if (view == null)
            return;

        switch (event.getType()) {
            case READ_EVENT:
                if (event.getVaccineList() != null)
                    view.setVaccineList(event.getVaccineList());
                if (event.getOwnerList() != null)
                    view.setOwnerList(event.getOwnerList());
                if (event.getPet() != null)
                    view.setPetInfo(event.getPet());
                break;
            case UPDATE_EVENT:
                view.onPetUpdated();
                break;
            case SAVE_EVENT:
                Pet pet = event.getPet();
                view.onPetSaved(pet);
                break;
        }
    }

    @Override
    public AddEditPetView getView() {
        return view;
    }
}
