package com.mago.petsvaccinationcard.addeditpet.ui.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mago.petsvaccinationcard.R;

import java.util.Calendar;

/**
 * Created by jorgemartinez on 19/12/18.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private EditText etDate;
    private int date[];

    public void setEtDate(EditText etDate) {
        this.etDate = etDate;
    }

    public void setDate(int[] date) {
        this.date = date;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();

        int year;
        int mont;
        int day;


        if (date.length == 0){
            year = calendar.get(Calendar.YEAR);
            mont = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            day = date[0];
            mont = date[1];
            year = date[2];
        }

        return new DatePickerDialog(getActivity(), this, year, mont, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month++;
        String day = (dayOfMonth < 10) ? "0" + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
        String fMonth = (month < 10) ? "0" + String.valueOf(month) : String.valueOf(month);
        String fYear = String.valueOf(year);

        String date = String.format(getActivity().getString(R.string.date_format_value), day, fMonth, fYear);

        etDate.setText(date);
    }


}
