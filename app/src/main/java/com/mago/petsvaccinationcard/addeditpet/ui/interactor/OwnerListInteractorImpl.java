package com.mago.petsvaccinationcard.addeditpet.ui.interactor;

import com.mago.petsvaccinationcard.addeditpet.ui.repository.AddEditPetRepository;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public class OwnerListInteractorImpl implements OwnerListInteractor {
    private AddEditPetRepository repository;

    public OwnerListInteractorImpl(AddEditPetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeRead() {
        repository.getSavedOwners();
    }

}
