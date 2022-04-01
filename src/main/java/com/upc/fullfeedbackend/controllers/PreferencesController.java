package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Preferences;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/preferences")
public class PreferencesController {


    @Autowired
    PreferencesService preferencesService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<Preferences>>> getAllPreferences(){

        List<Preferences> preferences = preferencesService.getAllPreferences();

        ResponseDTO<List<Preferences>> responseDTO = new ResponseDTO<>();
        try {
            responseDTO.setData(preferences);
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorMessage("");
            responseDTO.setErrorCode(0);
            return new ResponseEntity<>(responseDTO , HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(null , HttpStatus.NO_CONTENT);
    }

//    @PostMapping("")
//    public ResponseEntity<ResponseDTO<List<Preferences>>> saveAllPreferences(){
//        return null;
//    }


}
