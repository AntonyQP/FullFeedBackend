package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.Preferences;
import com.upc.fullfeedbackend.models.User;
import com.upc.fullfeedbackend.models.dto.RegisterDoctorRequestDTO;
import com.upc.fullfeedbackend.models.dto.RegisterPatientRequestDTO;
import com.upc.fullfeedbackend.repositories.DoctorRepository;
import com.upc.fullfeedbackend.repositories.PatientRepository;
import com.upc.fullfeedbackend.repositories.UserRepository;
import com.upc.fullfeedbackend.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.upc.fullfeedbackend.util.Encryption.createSecretKey;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public Doctor saveDoctor(RegisterDoctorRequestDTO request){
        User user = new User();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());

        //Cambiar cuando se suba a Azure
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -5);
        user.setRegisterDate(calendar.getTime());

        //user.setRegisterDate(request.getRegisterDate());

        user.setBirthDate(request.getBirthDate());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPhone(request.getPhone());
        user.setRol(request.getRol());
        user.setSex(request.getSex());
        user.setUsername(request.getUsername());
        String newPassword = EncriptarContrasena(request.getPassword());
        user.setPassword(newPassword);

        Doctor doctor = new Doctor();
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setUser(user);

        try {
            user = userRepository.save(user);
            doctor.setUser(user);
            doctor = doctorRepository.save(doctor);
        }catch (Exception e){
            e.getMessage();
        }

        return doctor;

    }


    public Patient savePatient(RegisterPatientRequestDTO request){

        User user = new User();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());

        //Cambiar cuando se suba a Azure
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -5);
        user.setRegisterDate(calendar.getTime());

        //user.setRegisterDate(request.getRegisterDate());


        user.setBirthDate(request.getBirthDate());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPhone(request.getPhone());
        user.setRol(request.getRol());
        user.setSex(request.getSex());
        user.setUsername(request.getUsername());
        String newPassword = EncriptarContrasena(request.getPassword());
        user.setPassword(newPassword);

        Patient patient = new Patient();
        patient.setAbdominal(request.getAbdominal());
        patient.setArm(request.getArm());
        patient.setHeight(request.getHeight());
        patient.setImc(request.getImc());
        patient.setWeight(request.getWeight());
        patient.setTmb(request.getTmb());



        Preferences preferences = new Preferences();


        try {
            user = userRepository.save(user);
            patient.setUser(user);
            patient = patientRepository.save(patient);
        }catch (Exception e){
            e.getMessage();
        }

        return patient;
    }

    public String  EncriptarContrasena (String contrasena){

        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = null;
        try {
            key = createSecretKey("contrasena".toCharArray(), salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        String nueva = contrasena;
        try {
            nueva = Encryption.encrypt(contrasena, key);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return nueva;
    }

    public User findByDni(String dni){
        return userRepository.findByDni(dni);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }


    public String  DesencriptarContrasena (String contrasena){

        byte[] salt = new String("12345678").getBytes();
        int iterationCount = 40000;
        int keyLength = 128;
        SecretKeySpec key = null;
        try {
            key = createSecretKey("contrasena".toCharArray(), salt, iterationCount, keyLength);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        String nueva = contrasena;
        try {
            nueva = Encryption.decrypt(contrasena, key);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nueva;
    }

}
