package com.mago.petsvaccinationcard.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mago.petsvaccinationcard.db.dao.OwnerDAO;
import com.mago.petsvaccinationcard.db.dao.PetDAO;
import com.mago.petsvaccinationcard.db.dao.VaccineDAO;
import com.mago.petsvaccinationcard.entities.Owner;
import com.mago.petsvaccinationcard.entities.Pet;
import com.mago.petsvaccinationcard.entities.Vaccine;

/**
 * Created by jorgemartinez on 17/12/18.
 */
@Database(version = VaccinationCardContract.DB_VERSION,
            entities = {Pet.class, Owner.class, Vaccine.class},
            exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase INSTANCE;

    abstract public PetDAO petDAO();
    abstract public OwnerDAO ownerDAO();
    abstract public VaccineDAO vaccineDAO();

    public static AppDataBase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDataBase.class, VaccinationCardContract.DB_NAME)
                        .allowMainThreadQueries()
                        .build();
        }
        return INSTANCE;
    }

    public static void destroyINSTANCE() {
        INSTANCE = null;
    }
}
