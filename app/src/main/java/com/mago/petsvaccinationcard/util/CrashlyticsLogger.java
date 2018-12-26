package com.mago.petsvaccinationcard.util;

import android.util.Log;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import io.fabric.sdk.android.Fabric;

/**
 * Created by jorgemartinez on 26/12/18.
 */
public class CrashlyticsLogger {
    private static final String TAG = CrashlyticsLogger.class.getSimpleName();

    public static void logButton(String btnText, String activityName) {
        if (Fabric.isInitialized()) {
            Answers.getInstance().logCustom(new CustomEvent(Flags.Logger.BUTTON_EVENT)
                .putCustomAttribute(Flags.Logger.ACTIVITY, activityName)
                .putCustomAttribute(Flags.Logger.BUTTON_CLICKED, btnText));
        }

    }

    public static void logActivity(String activityName) {
        if (Fabric.isInitialized()) {
            Answers.getInstance().logCustom(new CustomEvent(Flags.Logger.ACTIVITY_VISITED)
                .putCustomAttribute(Flags.Logger.ACTIVITY, activityName));
        }
    }

    public static void logNewPet(String kind) {
        if (Fabric.isInitialized()) {
            Answers.getInstance().logCustom(new CustomEvent(Flags.Logger.PET_EVENT)
                .putCustomAttribute(Flags.Logger.CREATE, kind));
        }
    }

    public static void logEditPet(String updateField) {
        if (Fabric.isInitialized()) {
            Answers.getInstance().logCustom(new CustomEvent(Flags.Logger.PET_EVENT)
                    .putCustomAttribute(Flags.Logger.UPDATE, updateField));
        }
    }

    public static void logNewUser() {
        if (Fabric.isInitialized()) {
            Answers.getInstance().logCustom(new CustomEvent(Flags.Logger.USER_EVENT)
                    );
        }
    }

    public static void logNewVaccine() {
        if (Fabric.isInitialized()) {
            Answers.getInstance().logCustom(new CustomEvent(Flags.Logger.VACCINE_EVENT)
                );
        }
    }

}
