package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patience;
import com.upc.fullfeedbackend.models.User;
import com.upc.fullfeedbackend.models.dto.RegisterDoctorRequestDTO;
import com.upc.fullfeedbackend.models.dto.RegisterPatienceRequestDTO;
import com.upc.fullfeedbackend.repositories.DoctorRepository;
import com.upc.fullfeedbackend.repositories.PatienceRepository;
import com.upc.fullfeedbackend.repositories.UserRepository;
import com.upc.fullfeedbackend.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.print.Doc;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import static com.upc.fullfeedbackend.util.Encryption.createSecretKey;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatienceRepository patienceRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public User saveDoctor(RegisterDoctorRequestDTO request){
        User user = new User();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setRegisterDate(request.getRegisterDate());
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
            doctor = doctorRepository.save(doctor);
        }catch (Exception e){
            e.getMessage();
        }

        return user;

    }


    public User savePatience(RegisterPatienceRequestDTO request){

        User user = new User();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setRegisterDate(request.getRegisterDate());
        user.setBirthDate(request.getBirthDate());
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setPhone(request.getPhone());
        user.setRol(request.getRol());
        user.setSex(request.getSex());
        user.setUsername(request.getUsername());
        String newPassword = EncriptarContrasena(request.getPassword());
        user.setPassword(newPassword);

        Patience patience = new Patience();
        patience.setAbdominal(request.getAbdominal());
        patience.setArm(request.getArm());
        patience.setHeight(request.getHeight());
        patience.setImc(request.getImc());
        patience.setWeight(request.getWeight());
        patience.setTmb(request.getTmb());
        patience.setUser(user);

        try {
            user = userRepository.save(user);
            patience = patienceRepository.save(patience);
        }catch (Exception e){
            e.getMessage();
        }

        return user;
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
