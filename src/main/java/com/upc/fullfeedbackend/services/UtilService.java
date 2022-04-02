package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.User;
import com.upc.fullfeedbackend.util.Encryption;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.stream.Collectors;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import static com.upc.fullfeedbackend.util.Encryption.createSecretKey;

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

        String tzCalendar = calendar.getTimeZone().getID();

        if (!tzCalendar.equals("America/Bogota") && calendar.get(Calendar.HOUR)!= 0){
            calendar.add(Calendar.HOUR_OF_DAY, -5);
        }

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);




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

    public static String randomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

    public static String randomPassword() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxz1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

    public static String  encriptarContrasena (String contrasena){

        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = null;
        try {
            key = createSecretKey("contrasena".toCharArray(), salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        String nueva = contrasena;
        try {
            nueva = Encryption.encrypt(contrasena, key);
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return nueva;
    }
    public static String  desencriptarContrasena (String contrasena){

        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = null;
        try {
            key = createSecretKey("contrasena".toCharArray(), salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        String nueva = contrasena;
        try {
            nueva = Encryption.decrypt(contrasena, key);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        return nueva;
    }


    public static String sendForgetPassword(User user, String newPassword){
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        String myAccountEmail = "fullfeedapp@gmail.com";
        String password = "bryanxd123";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, user, newPassword);

        try {
            Transport.send(message);
            System.out.println("Mensaje enviado correctamente");
            return "Se ha enviado el email";
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Message prepareMessage(Session session, String myAccountEmail, User user, String newPassword) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Recuperación de contraseña - FullFeedApp");
            StringBuffer content = new StringBuffer();
            content.append("Estimado ").append(user.getFirstName()).append(",\n\n")
                    .append("Su contraseña se ha reiniciado a : ").append(newPassword).append("\n")
                    .append("No se olvide de actualizarla antes de seguir usando la aplicación.").append("\n\n")
                    .append("Atentamente la familida de FullFeedApp.");
            message.setText(content.toString());
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }
    


}
