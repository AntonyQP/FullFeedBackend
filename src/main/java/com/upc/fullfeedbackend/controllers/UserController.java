package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Doctor;
import com.upc.fullfeedbackend.models.Patient;
import com.upc.fullfeedbackend.models.User;
import com.upc.fullfeedbackend.models.dto.*;
import com.upc.fullfeedbackend.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/doctor")
    public ResponseEntity<ResponseDTO<Doctor>> registerDoctor(@RequestBody RegisterDoctorRequestDTO request) {

        ResponseDTO<Doctor> registerResponseDTO = new ResponseDTO<>();

        User existentUser = userService.findByDni(request.getDni());
        if (existentUser != null) {
            registerResponseDTO.setErrorCode(1);
            registerResponseDTO.setErrorMessage("El doctor ya se encuentra registrado");
            registerResponseDTO.setHttpCode(200);
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

        return new ResponseEntity<>(registerResponseDTO, HttpStatus.OK) ;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

        User user = userService.findByEmail(request.getEmail());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        if (user != null) {
            String pass = userService.DesencriptarContrasena(user.getPassword());
            if (pass.equals(request.getPassword())){
                loginResponseDTO.setHttpCode(HttpStatus.OK.value());
                loginResponseDTO.setErrorCode(0);
                loginResponseDTO.setErrorMessage("");
                loginResponseDTO.setData(user);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Token",
                        getJWTToken(request.getEmail()));

                return new ResponseEntity<>(loginResponseDTO, responseHeaders,HttpStatus.OK);
            } else {

                loginResponseDTO.setHttpCode(HttpStatus.OK.value());
                loginResponseDTO.setErrorCode(2);
                loginResponseDTO.setErrorMessage("Password is incorrect");
                loginResponseDTO.setData(null);

                return new ResponseEntity<>(loginResponseDTO,HttpStatus.OK);
            }
        } else{
            loginResponseDTO.setHttpCode(HttpStatus.OK.value());
            loginResponseDTO.setErrorCode(1);
            loginResponseDTO.setErrorMessage("User not registered");
            loginResponseDTO.setData(null);

        }

        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
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
