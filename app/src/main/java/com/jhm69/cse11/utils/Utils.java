package com.jhm69.cse11.utils;

import android.preference.PreferenceManager;
import android.widget.EditText;

import com.jhm69.cse11.R;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Pedro on 15/9/2015.
 */
public class Utils {

    public static String formatDateToString(Date date, String pattern) {
        DateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static boolean isEmptyField(EditText et) {
        return (et.getText() == null || et.getText().toString().isEmpty());
    }


}
