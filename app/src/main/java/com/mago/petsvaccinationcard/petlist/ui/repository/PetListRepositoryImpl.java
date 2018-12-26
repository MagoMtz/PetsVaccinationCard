package com.mago.petsvaccinationcard.petlist.ui.repository;

import android.app.Activity;

import com.mago.petsvaccinationcard.db.dao.PetDAO;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.libs.base.EventBus;
import com.mago.petsvaccinationcard.petlist.events.PetListEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public class PetListRepositoryImpl implements PetListRepository{
    private EventBus eventBus;
    private PetDAO petDAO;

    public PetListRepositoryImpl(EventBus eventBus, PetDAO petDAO) {
        this.eventBus = eventBus;
        this.petDAO = petDAO;
    }

    @Override
    public void getSavedPets() {
        Pet[] allPets = petDAO.allPets();
        ArrayList<Pet> petList = new ArrayList<>(Arrays.asList(allPets));

        post(PetListEvent.READ_EVENT, petList);
    }

    @Override
    public void updatePet(Pet pet) {
        petDAO.updatePet(pet);
        ArrayList<Pet> petList = new ArrayList<>(Collections.singleton(pet));

        post(PetListEvent.UPDATE_EVENT, petList);
    }

    @Override
    public void removePet(Pet pet) {

    }

    private void post(int type, ArrayList<Pet> petList){
        PetListEvent event = new PetListEvent();
        event.setType(type);
        event.setPetList(petList);
        eventBus.post(event);
    }
}
