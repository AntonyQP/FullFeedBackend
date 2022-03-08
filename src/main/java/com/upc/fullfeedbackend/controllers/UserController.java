package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.*;
import com.upc.fullfeedbackend.models.dto.*;
import com.upc.fullfeedbackend.services.DoctorService;
import com.upc.fullfeedbackend.services.PatientService;
import com.upc.fullfeedbackend.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;


    @PostMapping("/doctor")
    public ResponseEntity<ResponseDTO<Doctor>> registerDoctor(@RequestBody RegisterDoctorRequestDTO request) {

        ResponseDTO<Doctor> registerResponseDTO = new ResponseDTO<>();

        User existentUser = userService.findByDni(request.getDni());
        if (existentUser != null) {
            registerResponseDTO.setErrorCode(1);
            registerResponseDTO.setErrorMessage("El doctor ya se encuentra registrado");
            registerResponseDTO.setHttpCode(HttpStatus.OK.value());
            registerResponseDTO.setData(null);

            return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK) ;
        }

        try {
            registerResponseDTO.setErrorCode(0);
            registerResponseDTO.setErrorMessage("");
            registerResponseDTO.setHttpCode(HttpStatus.CREATED.value());
            registerResponseDTO.setData(userService.saveDoctor(request));

            return new ResponseEntity<>(registerResponseDTO, HttpStatus.CREATED) ;
        } catch (Exception e) {
            e.getMessage();
        }

        return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK) ;
    }


    @PostMapping("/patient")
    private ResponseEntity<ResponseDTO<Patient>> registerPatient(@RequestBody RegisterPatientRequestDTO request) {
        ResponseDTO<Patient> registerResponseDTO = new ResponseDTO<>();


        User existentUser = userService.findByDni(request.getDni());
        if (existentUser != null) {
            registerResponseDTO.setErrorCode(1);
            registerResponseDTO.setErrorMessage("El paciente ya se encuentra registrado");
            registerResponseDTO.setHttpCode(HttpStatus.OK.value());
            registerResponseDTO.setData(null);
            return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK);
        }

        try {
            registerResponseDTO.setErrorCode(0);
            registerResponseDTO.setErrorMessage("");
            registerResponseDTO.setHttpCode(HttpStatus.CREATED.value());
            registerResponseDTO.setData(userService.savePatient(request));

            return new ResponseEntity<>(registerResponseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.getMessage();
        }

        registerResponseDTO.setErrorCode(2);
        registerResponseDTO.setErrorMessage("Ocurrio un problema al registrar al usuario");
        registerResponseDTO.setHttpCode(HttpStatus.OK.value());
        registerResponseDTO.setData(null);

        return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK) ;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO<Patient>();
        User user = userService.findByEmail(request.getEmail());
        ResponseDTO<LoginResponseDTO> responseDTO = new ResponseDTO<>();

        if (user != null) {
            String pass = userService.DesencriptarContrasena(user.getPassword());
            if (pass.equals(request.getPassword())){
                if (user.getRol().equals("p")){
                    Patient patient = patientService.getPatientByUserId(user.getUserId());
                    loginResponseDTO.setProfile(patient);
                }else{
                    Doctor doctor = doctorService.getDoctorByUserId(user.getUserId());
                    loginResponseDTO.setProfile(doctor);
                }

                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(0);
                responseDTO.setErrorMessage("");
                responseDTO.setData(loginResponseDTO);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Token",
                        getJWTToken(request.getEmail()));

                return new ResponseEntity<>(responseDTO, responseHeaders,HttpStatus.OK);
            } else {

                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(2);
                responseDTO.setErrorMessage("Password is incorrect");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO,HttpStatus.OK);
            }
        } else{
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(1);
            responseDTO.setErrorMessage("User not registered");
            responseDTO.setData(null);

        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }


}
