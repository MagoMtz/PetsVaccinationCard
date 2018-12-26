package com.mago.petsvaccinationcard.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mago.petsvaccinationcard.db.VaccinationCardContract.PetData;
import com.mago.petsvaccinationcard.entities.Pet;


/**
 * Created by jorgemartinez on 17/12/18.
 */
@Dao
public interface PetDAO {
    @Insert
    long insertPet(Pet pet);

    @Update
    void updatePet(Pet pet);

    @Delete
    void deletePet(Pet pet);

    @Query("SELECT * FROM " + PetData.TABLE_NAME)
    Pet[] allPets();

    @Query("SELECT * FROM " + PetData.TABLE_NAME + " WHERE " + PetData.PET_ID + " = :petId")
    Pet petById(int petId);
}
