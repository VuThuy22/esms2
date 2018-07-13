package com.example.elcomplus.esms.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String convertLongToddMMyyyy(Long timeSTamp){
        try {
            Date date = new Date(timeSTamp);
            DateFormat formatter = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
            return formatter.format(date);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
