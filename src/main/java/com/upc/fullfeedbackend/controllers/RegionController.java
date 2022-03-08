package com.upc.fullfeedbackend.controllers;

import com.upc.fullfeedbackend.models.Region;
import com.upc.fullfeedbackend.models.dto.ResponseDTO;
import com.upc.fullfeedbackend.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<Region>>> getAllRegions(){
        ResponseDTO<List<Region>> responseDTO = new ResponseDTO<>();
        List<Region> regions;
        try{
            regions = regionService.getAllRegions();
            if (regions == null){
                responseDTO.setHttpCode(HttpStatus.OK.value());
                responseDTO.setErrorCode(1);
                responseDTO.setErrorMessage("No existen regiones registradas");
                responseDTO.setData(null);

                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
            responseDTO.setHttpCode(HttpStatus.OK.value());
            responseDTO.setErrorCode(0);
            responseDTO.setErrorMessage("");
            responseDTO.setData(regions);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        }catch (Exception e){
            e.getMessage();
        }

        responseDTO.setHttpCode(HttpStatus.OK.value());
        responseDTO.setErrorCode(2);
        responseDTO.setErrorMessage("Ocurrio un error inesperado");
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
