package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.*;
import com.upc.fullfeedbackend.models.dto.RegisterDoctorRequestDTO;
import com.upc.fullfeedbackend.models.dto.RegisterPatientRequestDTO;
import com.upc.fullfeedbackend.repositories.*;
import com.upc.fullfeedbackend.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.upc.fullfeedbackend.util.Encryption.createSecretKey;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientLogRepository patientLogRepository;

    @Autowired
    RegionService regionService;

    @Autowired
    MealService mealService;

    @Autowired
    PersonalTreatmentsService personalTreatmentsService;

    @Autowired
    NutritionalPlanService nutritionalPlanService;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public Doctor registerDoctor(RegisterDoctorRequestDTO request){
        User user = new User();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setRegisterDate(UtilService.getNowDate());
        user.setBirthDate(request.getBirthDate());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPhone(request.getPhone());
        user.setRol("d");
        user.setSex(request.getSex());
        user.setUsername(request.getUsername());
        String newPassword = UtilService.encriptarContrasena(request.getPassword());
        user.setPassword(newPassword);

        Doctor doctor = new Doctor();
        doctor.setLicenseNumber(request.getLicenseNumber());
        doctor.setUser(user);
        doctor.setActivePatients(0);

        try {
            user = userRepository.save(user);
            doctor.setUser(user);
            doctor = doctorService.saveDoctor(doctor);
        }catch (Exception e){
            e.getMessage();
        }

        return doctor;

    }

    public Patient registerPatient(RegisterPatientRequestDTO request){

        User user = new User();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setRegisterDate(UtilService.getNowDate());
        user.setBirthDate(request.getBirthDate());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPhone(request.getPhone());
        user.setRol(request.getRol());
        user.setSex(request.getSex());
        user.setUsername(request.getUsername());
        String newPassword = UtilService.encriptarContrasena(request.getPassword());
        user.setPassword(newPassword);

        Patient patient = new Patient();
        patient.setAbdominal(request.getAbdominal());
        patient.setArm(request.getArm());
        patient.setHeight(request.getHeight());
        patient.setImc(request.getImc());
        patient.setWeight(request.getWeight());
        patient.setTmb(request.getTmb());
        patient.setAge(UtilService.getActualAge(request.getBirthDate()));

        Region region =  regionService.getRegionById(request.getRegionId());
        patient.setRegion(region);

        PatientLog patientLog = new PatientLog();
        patientLog.setAbdominal(request.getAbdominal());
        patientLog.setArm(request.getArm());
        patientLog.setHeight(request.getHeight());
        patientLog.setImc(request.getImc());
        patientLog.setWeight(request.getWeight());
        patientLog.setTmb(request.getTmb());
        patientLog.setDate(UtilService.getNowDate());


        try {
            user = userRepository.save(user);
            patient.setUser(user);
            patient = patientRepository.save(patient);
            patientLog.setPatient(patient);
            patientLog = patientLogRepository.save(patientLog);

        }catch (Exception e){
            e.getMessage();
        }

        return patient;
    }




    public User findByDni(String dni){
        return userRepository.findByDni(dni);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public String restorePassword(String email){

        User user = userRepository.findByEmail(email);

        String result = "";

        if (user!= null){
            String newPassword = UtilService.randomPassword();
            String encryptedPassword = UtilService.encriptarContrasena(newPassword);
            user.setPassword(encryptedPassword);
            user = userRepository.save(user);
            result = UtilService.sendForgetPassword(user, newPassword);
            if (result == null){
                return "Ocurrio un error al enviar el correo";
            }
            return "Se ha enviado un correo a " + user.getEmail();

        } else {
            return "El correo no se encuentra registrado";
        }
    }

    public String updatePassword(String email, String actualPassword, String newPassword){
        User user = findByEmail(email);
        if (user == null){
            return "El usuario no existe";
        }
        String actualPasswordDecrypted = UtilService.desencriptarContrasena(user.getPassword());
        if (actualPasswordDecrypted.equals(actualPassword)){
            String newPasswordEncrypted = UtilService.encriptarContrasena(newPassword);
            user.setPassword(newPasswordEncrypted);
            userRepository.save(user);
            return "La contraseña de actualizo correctamente";
        } else {
            return "La contraseña actual no es válida.";
        }
    }

}
