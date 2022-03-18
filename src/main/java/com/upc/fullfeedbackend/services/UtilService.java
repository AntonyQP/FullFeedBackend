package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Patient;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

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

        if (!tzCalendar.equals("America/Bogota") && calendar.get(Calendar.HOUR)!= 0){
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


    public static String RandomString() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 5;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }
}
