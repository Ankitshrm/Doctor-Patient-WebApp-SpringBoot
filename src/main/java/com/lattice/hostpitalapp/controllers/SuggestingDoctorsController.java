package com.lattice.hostpitalapp.controllers;

import com.lattice.hostpitalapp.services.SuggestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SuggestingDoctorsController {

    @Autowired
    private SuggestedService suggestedService;

    @GetMapping("/patient/findDoctor")
    public ResponseEntity<Object> suggestedPatientsDTO(@RequestParam("patientId") Long patientId){
        Object listOfDoctors = this.suggestedService.getAllSuggestedDoctors(patientId);
        return new ResponseEntity<>(listOfDoctors, HttpStatus.OK);
    }

}
