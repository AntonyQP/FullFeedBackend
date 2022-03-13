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


    public static Date getNowDateMealsWhitAddDays(Integer days){
        //Cambiar cuando se suba a Amazon

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);


        String tzCalendar = calendar.getTimeZone().getID();

        if (!tzCalendar.equals("America/Bogota")){
            calendar.add(Calendar.HOUR_OF_DAY, -5);
        }

        calendar.add(Calendar.DATE, days);

        return calendar.getTime();
    }
}
