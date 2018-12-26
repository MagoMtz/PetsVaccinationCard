package com.mago.petsvaccinationcard.util;

/**
 * Created by jorgemartinez on 17/12/18.
 */
public abstract class Flags {

    public enum ListType {
        PET,
        OWNER,
        VACCINE
    }

    public enum ActionType {
        SAVE_NEW,
        EDIT
    }

    public static abstract class IntentKeys {
        public static final String ACTION_TYPE = "action_type";
        public static final String OWNER_ID = "owner_id";
        public static final String PET_ID = "pet_id";
        public static final String VACCINE_ID = "vaccine_id";
    }

    public static abstract class AppPreferencesFlags {
        public static final String APP_PREF_FILE = "app_pref_file";
        public static final String LAST_PET_ID = "last_pet_id";
    }

    public static abstract class Logger {
        public static final String BUTTON_EVENT = "Button event";
        public static final String ACTIVITY = "Activity";
        public static final String ACTIVITY_VISITED = "Activity visited";
        public static final String BUTTON_CLICKED = "Button clicked";

        public static final String PET_EVENT = "Pet event";

        public static final String UPDATE = "Update";
        public static final String CREATE = "Create";
        public static final String USER_EVENT = "User event";
        public static final String VACCINE_EVENT = "Vaccine event";
    }
}
