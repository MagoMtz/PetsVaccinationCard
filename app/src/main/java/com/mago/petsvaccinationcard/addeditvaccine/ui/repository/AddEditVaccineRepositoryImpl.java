package com.mago.petsvaccinationcard.addeditvaccine.ui.repository;

import com.mago.petsvaccinationcard.addeditvaccine.events.AddEditVaccineEvent;
import com.mago.petsvaccinationcard.db.dao.VaccineDAO;
import com.mago.petsvaccinationcard.entities.Vaccine;
import com.mago.petsvaccinationcard.libs.base.EventBus;

/**
 * Created by jorgemartinez on 21/12/18.
 */
public class AddEditVaccineRepositoryImpl implements AddEditVaccineRepository{
    private EventBus eventBus;
    private VaccineDAO vaccineDAO;

    public AddEditVaccineRepositoryImpl(EventBus eventBus, VaccineDAO vaccineDAO) {
        this.eventBus = eventBus;
        this.vaccineDAO = vaccineDAO;
    }

    @Override
    public void saveVaccine(Vaccine vaccine) {
        vaccineDAO.insertVaccine(vaccine);

        post(AddEditVaccineEvent.SAVE_EVENT, vaccine);
    }

    @Override
    public void updateVaccine(Vaccine vaccine) {
        vaccineDAO.updateVaccine(vaccine);

        post(AddEditVaccineEvent.UPDATE_EVENT, vaccine);
    }

    @Override
    public void getVaccine(int vaccineId) {
        vaccineDAO.vaccineById(vaccineId);
    }

    private void post(int type, Vaccine vaccine) {
        AddEditVaccineEvent event = new AddEditVaccineEvent();
        event.setType(type);
        event.setVaccine(vaccine);

        eventBus.post(event);
    }
}
