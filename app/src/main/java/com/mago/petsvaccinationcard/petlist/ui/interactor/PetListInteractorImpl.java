package com.mago.petsvaccinationcard.petlist.ui.interactor;

import com.mago.petsvaccinationcard.petlist.ui.repository.PetListRepository;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class PetListInteractorImpl implements PetListInteractor{
    private PetListRepository repository;

    public PetListInteractorImpl(PetListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getSavedPets();
    }
}
