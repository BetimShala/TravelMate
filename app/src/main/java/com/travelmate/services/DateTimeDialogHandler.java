package com.travelmate.services;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.TimeUtils;

import java.text.DateFormat;
import java.util.Calendar;
import com.travelmate.R;

/**
 * Created by Betim on 5/12/2018.
 */

public class DateTimeDialogHandler extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstaceState){

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog;
        dialog = new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener)getActivity(),hour,minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
        return dialog;
    }
}
