package com.mago.petsvaccinationcard.db;

import android.provider.BaseColumns;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public abstract class VaccinationCardContract {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "vaccination_card_db";

    public static abstract class PetData implements BaseColumns {
        public static final String TABLE_NAME = "pet";
        public static final String PET_ID = "pet_id";
        public static final String PET_KIND = "pet_kind";
        public static final String PET_SEX = "pet_sex";
        public static final String PET_NAME = "pet_name";
        public static final String PET_BIRTHDAY = "pet_birthday";
        public static final String PET_BREED = "pet_breed";
        public static final String PET_COLOR = "pet_color";
        public static final String PET_ID_NUMBER = "pet_id_number";
        public static final String PET_PARTICULAR_SIGNS = "pet_particular_signs";
        public static final String PET_MICRO_CHIP = "pet_micro_chip";
        public static final String PET_TATTOO = "pet_tattoo";
        public static final String PET_PHOTO = "pet_photo";
    }

    public static abstract class OwnerData implements BaseColumns {
        public static final String TABLE_NAME = "owner";
        public static final String OWNER_ID = "owner_id";
        public static final String OWNER_LAST_NAME = "owner_last_name";
        public static final String OWNER_FIRST_NAME = "owner_first_name";
        public static final String OWNER_ADDRESS = "owner_address";
        public static final String OWNER_PHONE = "owner_phone";
    }

    public static abstract class VaccineData implements BaseColumns {
        public static final String TABLE_NAME = "vaccine";
        public static final String VACCINE_ID = "vaccine_id";
        public static final String VACCINE_NAME = "vaccine_name";
        public static final String VACCINE_DATE = "vaccine_date";
        public static final String VACCINE_TICKET = "vaccine_ticket";
        public static final String VACCINE_NEXT_APPOINTMENT = "vaccine_next_appointment";
    }

}
