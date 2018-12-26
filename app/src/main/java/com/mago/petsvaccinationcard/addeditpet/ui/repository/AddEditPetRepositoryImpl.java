package com.mago.petsvaccinationcard.addeditpet.ui.repository;

import com.mago.petsvaccinationcard.addeditpet.events.AddEditPetEvent;
import com.mago.petsvaccinationcard.db.dao.OwnerDAO;
import com.mago.petsvaccinationcard.db.dao.PetDAO;
import com.mago.petsvaccinationcard.db.dao.VaccineDAO;
import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.entities.Vaccine;
import com.mago.petsvaccinationcard.libs.base.EventBus;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jorgemartinez on 18/12/18.
 */
public class AddEditPetRepositoryImpl implements AddEditPetRepository{
    private EventBus eventBus;
    private PetDAO petDAO;
    private OwnerDAO ownerDAO;
    private VaccineDAO vaccineDAO;

    public AddEditPetRepositoryImpl(EventBus eventBus, PetDAO petDAO, OwnerDAO ownerDAO, VaccineDAO vaccineDAO) {
        this.eventBus = eventBus;
        this.petDAO = petDAO;
        this.ownerDAO = ownerDAO;
        this.vaccineDAO = vaccineDAO;
    }

    @Override
    public void savePet(Pet pet) {
        long petId = petDAO.insertPet(pet);

        pet.setPetId((int)petId);
        post(AddEditPetEvent.SAVE_EVENT, pet);
    }

    @Override
    public void updatePet(Pet pet) {
        petDAO.updatePet(pet);

        post(AddEditPetEvent.UPDATE_EVENT, pet);
    }

    @Override
    public void getPet(int petId) {
        Pet pet = petDAO.petById(petId);

        post(AddEditPetEvent.READ_EVENT, pet);
    }

    @Override
    public void getSavedOwners() {
        Owner[] allOwners = ownerDAO.allOwners();
        ArrayList<Owner> ownerList = new ArrayList<>(Arrays.asList(allOwners));

        post(AddEditPetEvent.READ_EVENT, ownerList, null);
    }

    @Override
    public void getVaccinesOfPet(int petId) {
        Vaccine[] vaccinesOfPet = vaccineDAO.allVaccinesOfPet(petId);
        ArrayList<Vaccine> vaccineList = new ArrayList<>(Arrays.asList(vaccinesOfPet));

        post(AddEditPetEvent.READ_EVENT, null, vaccineList);
    }

    private void post(int type, ArrayList<Owner> ownerList, ArrayList<Vaccine> vaccineList) {
        AddEditPetEvent event = new AddEditPetEvent();
        event.setType(type);
        event.setOwnerList(ownerList);
        event.setVaccineList(vaccineList);
        eventBus.post(event);
    }

    private void post(int type, Pet pet) {
        AddEditPetEvent event = new AddEditPetEvent();
        event.setType(type);
        event.setPet(pet);
        eventBus.post(event);
    }

}
