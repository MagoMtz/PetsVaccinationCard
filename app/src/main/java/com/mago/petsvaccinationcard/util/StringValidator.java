package com.mago.petsvaccinationcard.util;

import android.util.Patterns;

/**
 * Created by jorgemartinez on 20/12/18.
 */
public class StringValidator {

    public static boolean isNullOrEmpty(String s) {
        if (s == null)
            return true;

        return s.isEmpty();
    }

    public static boolean isValidDate(String date) {
        String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        return date.matches(regex);
    }

    public static boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }
}
