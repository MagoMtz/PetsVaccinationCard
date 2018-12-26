package com.mago.petsvaccinationcard.addeditowner.ui.presenter;

import com.mago.petsvaccinationcard.addeditowner.events.AddEditOwnerEvent;
import com.mago.petsvaccinationcard.addeditowner.ui.interactor.OwnerInteractor;
import com.mago.petsvaccinationcard.addeditowner.ui.view.AddEditOwnerView;
import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.libs.base.EventBus;
import com.mago.petsvaccinationcard.util.StringValidator;


import org.greenrobot.eventbus.Subscribe;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public class AddEditOwnerPresenterImpl implements AddEditOwnerPresenter {
    private EventBus eventBus;
    private AddEditOwnerView view;
    private OwnerInteractor interactor;

    public AddEditOwnerPresenterImpl(EventBus eventBus, AddEditOwnerView view, OwnerInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
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
    public void getOwner(int ownerId) {
        interactor.executeRead(ownerId);
    }

    @Override
    public void saveOwner(Owner owner) {
        boolean success = true;

        if (StringValidator.isNullOrEmpty(owner.getOwnerFirstName()))
            success = false;
        if (StringValidator.isNullOrEmpty(owner.getOwnerLastName()))
            success = false;
        if (!StringValidator.isNullOrEmpty(owner.getOwnerPhone()))
            if (!StringValidator.isValidPhone(owner.getOwnerPhone()))
                success = false;

        if (!success){
            view.setNotNullError(owner);
            return;
        }

        view.setNotNullError(owner);
        interactor.executeSave(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        interactor.executeUpdate(owner);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddEditOwnerEvent event) {
        if (view == null)
            return;

        switch (event.getType()) {
            case AddEditOwnerEvent.READ_EVENT :
                view.setOwnerInfo(event.getOwner());
                break;
            case AddEditOwnerEvent.UPDATE_EVENT :
                view.onOwnerUpdated();
                break;
            case AddEditOwnerEvent.SAVE_EVENT :
                view.onOwnerSaved();
                break;
        }
    }

    @Override
    public AddEditOwnerView getView() {
        return view;
    }
}
