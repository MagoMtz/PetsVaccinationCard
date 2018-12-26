package com.mago.petsvaccinationcard.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mago.petsvaccinationcard.db.VaccinationCardContract.OwnerData;
import com.mago.petsvaccinationcard.entities.Owner;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Dao
public interface OwnerDAO {
    @Insert
    long insetOwner(Owner owner);

    @Update
    void updateOwner(Owner owner);

    @Delete
    void deleteOwner(Owner owner);

    @Query("SELECT * FROM " + OwnerData.TABLE_NAME)
    Owner[] allOwners();

    @Query("SELECT * FROM " + OwnerData.TABLE_NAME + " WHERE " + OwnerData.OWNER_ID + " = :ownerId")
    Owner ownerById(int ownerId);

}
