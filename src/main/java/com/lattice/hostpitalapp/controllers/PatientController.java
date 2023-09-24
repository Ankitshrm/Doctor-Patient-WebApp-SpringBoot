package com.lattice.hostpitalapp.controllers;

import com.lattice.hostpitalapp.payloads.ApiResponse;
import com.lattice.hostpitalapp.payloads.DoctorDTO;
import com.lattice.hostpitalapp.payloads.PatientDTO;
import com.lattice.hostpitalapp.repositories.DoctorRepository;
import com.lattice.hostpitalapp.repositories.PatientRepository;
import com.lattice.hostpitalapp.services.DoctorService;
import com.lattice.hostpitalapp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<PatientDTO> registerPatient(@Valid @RequestBody PatientDTO patientDTO){
        PatientDTO createDTO = this.patientService.createPatient(patientDTO);
        return new ResponseEntity<>(createDTO, HttpStatus.CREATED);
    }




    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDTO> updateDoctor(@Valid @RequestBody PatientDTO patientDTO,@PathVariable("patientId") Long patientId) {
        PatientDTO updateExistUser = this.patientService.updatePatient(patientDTO,patientId);
        return new ResponseEntity<>(updateExistUser, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getDoctorByID(@PathVariable("patientId") Long patientId){
        PatientDTO patientDTO = this.patientService.getPatientById(patientId);
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatient(){
        List<PatientDTO> allUsers = this.patientService.getAllPatients();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("patientId") Long patientId){
        this.patientService.deletePatient(patientId);
        return new ResponseEntity<>(new ApiResponse("Patient Deleted Successfully",true),HttpStatus.OK);
    }

}
