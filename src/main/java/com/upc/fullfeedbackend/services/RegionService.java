package com.upc.fullfeedbackend.services;

import com.upc.fullfeedbackend.models.Region;
import com.upc.fullfeedbackend.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    RegionRepository regionRepository;

    public Region getRegionById(Long regionId){
        return regionRepository.findById(regionId).get();
    }

    public List<Region> getAllRegions(){
        return regionRepository.findAll();
    }

}
