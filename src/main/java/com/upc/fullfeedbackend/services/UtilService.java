package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Patient;

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

    public static Double getCaloriesForPatient(Patient patient){
        double calorias = 0;
        if (patient.getUser().getSex().equals("m"))
            calorias = (655 + (9.6 * patient.getWeight())) + ((1.8 * patient.getHeight()) - (4.7 * patient.getAge())) * 1.2;
        else
            calorias = (66 + (13.7 * patient.getWeight())) + ((5 * patient.getHeight()) - (6.8 * patient.getAge())) * 1.2;
        return calorias;
    }
}
