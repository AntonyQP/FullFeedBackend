package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Patience;
import com.upc.fullfeedbackend.models.PatienceLog;
import com.upc.fullfeedbackend.models.PatiencePreferences;
import com.upc.fullfeedbackend.models.dto.PreferencesDTO;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.PatienceLogService;
import com.upc.fullfeedbackend.services.PatiencePreferencesService;
import com.upc.fullfeedbackend.services.PatienceService;
import com.upc.fullfeedbackend.services.PreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/patience")
public class PatienceController {

    @Autowired
    PatienceService patienceService;

    @Autowired
    PatienceLogService patienceLogService;

    @Autowired
    PatiencePreferencesService patiencePreferencesService;

    @Autowired
    PreferencesService preferencesService;

    @GetMapping()
    public List<Patience> getAllPatiences() {
        return patienceService.getAllPatiences();
    }

    @PostMapping("/saveLog")
    public PatienceLog savePatienceLog(@RequestBody PatienceLog patienceLog){
        return patienceLogService.savePatienceLog(patienceLog);
    }

    @PutMapping("/updatePatience")
    public ResponseEntity<ResponseDTO<Patience>> updatePatience(@RequestBody Patience patience){

        PatienceLog patienceLog = new PatienceLog();

        //Cambiar cuando se suba a Amazon
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, -5);

        ResponseDTO<Patience> responseDTO = new ResponseDTO<>();

        try {
                patienceLog.setPatience(patience);
                patienceLog.setArm(patience.getArm());
                patienceLog.setDate(calendar.getTime());
                patienceLog.setHeight(patience.getHeight());
                patienceLog.setImc(patience.getImc());
                patienceLog.setTmb(patience.getTmb());
                patienceLog.setWeight(patience.getWeight());
                patienceLog.setAbdominal(patience.getAbdominal());

                patienceLogService.savePatienceLog(patienceLog);

                responseDTO.setErrorCode(0);
                responseDTO.setErrorMessage("");
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setData(patienceService.updatePatience(patience));

                return new ResponseEntity<>(responseDTO , HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(null , HttpStatus.NO_CONTENT);
    }


    @GetMapping("/historial")
    public ResponseEntity<ResponseDTO<List<PatienceLog>>> getHistorial(@RequestParam Long patienceId){

        ResponseDTO<List<PatienceLog>> responseDTO = new ResponseDTO<>();
        try {
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setData(patienceLogService.getAllPatienceLogs(patienceId));
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/preferences")
    public ResponseEntity<ResponseDTO<List<PatiencePreferences>>> savePreferences(@RequestParam Long pacienteId, @RequestBody List<PreferencesDTO> preferencesDTOList){
        List<PatiencePreferences> patiencePreferencesList = new ArrayList<PatiencePreferences>();

        Patience patience = patienceService.getPatienceById(pacienteId);

        ResponseDTO<List<PatiencePreferences>> responseDTO = new ResponseDTO<>();

        try {
            for ( PreferencesDTO preferences: preferencesDTOList) {
                PatiencePreferences patiencePreferences = new PatiencePreferences();
                patiencePreferences.setPatience(patience);
                patiencePreferences.setPreferences(preferencesService.findByPreferences(preferences.getNombre()));
                patiencePreferences.setType(preferences.getType());
                patiencePreferencesList.add(patiencePreferences);
            }
            responseDTO.setData(patiencePreferencesService.savePatiencePreferences(patiencePreferencesList));
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            e.getMessage();
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
