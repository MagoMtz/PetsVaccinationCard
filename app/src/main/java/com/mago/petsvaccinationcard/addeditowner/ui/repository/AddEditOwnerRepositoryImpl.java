package com.mago.petsvaccinationcard.addeditowner.ui.repository;

import com.mago.petsvaccinationcard.addeditowner.events.AddEditOwnerEvent;
import com.mago.petsvaccinationcard.db.dao.OwnerDAO;
import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.libs.base.EventBus;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public class AddEditOwnerRepositoryImpl implements AddEditOwnerRepository{
    private EventBus eventBus;
    private OwnerDAO ownerDAO;

    public AddEditOwnerRepositoryImpl(EventBus eventBus, OwnerDAO ownerDAO) {
        this.eventBus = eventBus;
        this.ownerDAO = ownerDAO;
    }

    @Override
    public void saveOwner(Owner owner) {
        ownerDAO.insetOwner(owner);

        post(AddEditOwnerEvent.SAVE_EVENT, owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerDAO.updateOwner(owner);

        post(AddEditOwnerEvent.UPDATE_EVENT, owner);
    }

    @Override
    public void getOwner(int ownerId) {
        Owner owner = ownerDAO.ownerById(ownerId);

        post(AddEditOwnerEvent.READ_EVENT, owner);
    }

    private void post(int type, Owner owner) {
        AddEditOwnerEvent event = new AddEditOwnerEvent();
        event.setType(type);
        event.setOwner(owner);

        eventBus.post(event);
    }
}
