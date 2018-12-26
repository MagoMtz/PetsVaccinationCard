package com.mago.petsvaccinationcard.addeditowner.ui.interactor;

import com.mago.petsvaccinationcard.addeditowner.ui.repository.AddEditOwnerRepository;
import com.mago.petsvaccinationcard.entities.Owner;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public class OwnerInteractorImpl implements OwnerInteractor {
    private AddEditOwnerRepository repository;

    public OwnerInteractorImpl(AddEditOwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeSave(Owner owner) {
        repository.saveOwner(owner);
    }

    @Override
    public void executeUpdate(Owner owner) {
        repository.updateOwner(owner);
    }

    @Override
    public void executeRead(int ownerId) {
        repository.getOwner(ownerId);
    }
}
