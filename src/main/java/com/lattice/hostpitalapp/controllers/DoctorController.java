package com.lattice.hostpitalapp.controllers;

import com.lattice.hostpitalapp.payloads.ApiResponse;
import com.lattice.hostpitalapp.payloads.DoctorDTO;
import com.lattice.hostpitalapp.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDTO> registerDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        DoctorDTO createDTO = this.doctorService.createDoctor(doctorDTO);
        return new ResponseEntity<>(createDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> updateDoctor(@Valid @RequestBody DoctorDTO doctorDTO,@PathVariable("doctorId") Long doctorId) {
        DoctorDTO updateExistUser = this.doctorService.updateDoctor(doctorDTO,doctorId);
        return new ResponseEntity<>(updateExistUser, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorDTO> getDoctorByID(@PathVariable("doctorId") Long doctorId){
        DoctorDTO user = this.doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllUsers(){
        List<DoctorDTO> allUsers = this.doctorService.getAllDoctor();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("doctorId") Long doctorId){
        this.doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(new ApiResponse("Doctor Deleted Successfully",true),HttpStatus.OK);
    }

}
