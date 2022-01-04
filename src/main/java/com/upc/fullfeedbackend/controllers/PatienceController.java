package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Patience;
import com.upc.fullfeedbackend.services.PatienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patience")
public class PatienceController {

    @Autowired
    PatienceService patienceService;

    @GetMapping()
    public List<Patience> getAllPatiences() {
        return patienceService.getAllPatiences();
    }
}
