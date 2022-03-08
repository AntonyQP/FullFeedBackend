package com.upc.fullfeedbackend.services;

import java.util.Calendar;
import java.util.Date;

public class UtilService {


    public static Date getNowDate(){
        //Cambiar cuando se suba a Amazon

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String  tzCalendar = calendar.getTimeZone().getID();

        if (!tzCalendar.equals("America/Bogota")){
            calendar.add(Calendar.HOUR_OF_DAY, -5);
        }

        return calendar.getTime();
    }
}
