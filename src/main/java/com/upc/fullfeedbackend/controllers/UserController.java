package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.User;
import com.upc.fullfeedbackend.models.dto.RegisterDoctorRequestDTO;
import com.upc.fullfeedbackend.models.dto.RegisterPatienceRequestDTO;
import com.upc.fullfeedbackend.models.dto.RegisterResponseDTO;
import com.upc.fullfeedbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/doctor")
    public RegisterResponseDTO registerDoctor(@RequestBody RegisterDoctorRequestDTO request){

        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();

        try {
            registerResponseDTO.setErrorCode("0");
            registerResponseDTO.setErrorMessage("");
            registerResponseDTO.setHttpCode("200");
            registerResponseDTO.setData(userService.saveDoctor(request));
        } catch (Exception e){
            e.getMessage();
        }

        return registerResponseDTO;
    }


    @PostMapping("/patience")
    private RegisterResponseDTO registerPatience(@RequestBody RegisterPatienceRequestDTO request){
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();

        try {
            registerResponseDTO.setErrorCode("0");
            registerResponseDTO.setErrorMessage("");
            registerResponseDTO.setHttpCode("200");
            registerResponseDTO.setData(userService.savePatience(request));
        } catch (Exception e){
            e.getMessage();
        }

        return registerResponseDTO;
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
