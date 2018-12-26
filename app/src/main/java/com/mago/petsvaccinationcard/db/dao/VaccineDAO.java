package com.mago.petsvaccinationcard.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mago.petsvaccinationcard.db.VaccinationCardContract.VaccineData;
import com.mago.petsvaccinationcard.db.VaccinationCardContract.PetData;
import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Dao
public interface VaccineDAO {
    @Insert
    long insertVaccine(Vaccine vaccine);
    @Update
    void updateVaccine(Vaccine vaccine);
    @Delete
    void deleteVaccine(Vaccine vaccine);

    @Query("SELECT * FROM " + VaccineData.TABLE_NAME + " WHERE " + PetData.PET_ID + " = :petId")
    Vaccine[] allVaccinesOfPet(int petId);

    @Query("SELECT * FROM " + VaccineData.TABLE_NAME + " WHERE " + VaccineData.VACCINE_ID + " = :vaccineId")
    Vaccine vaccineById(int vaccineId);
}
