package com.lattice.hostpitalapp.services;

import com.lattice.hostpitalapp.models.Patient;
import com.lattice.hostpitalapp.payloads.DoctorDTO;
import com.lattice.hostpitalapp.payloads.PatientDTO;

import java.util.List;

public interface PatientService {

    PatientDTO createPatient(PatientDTO patientDTO);
    PatientDTO getPatientById(Long patientId);
    List<PatientDTO> getAllPatients();
    PatientDTO updatePatient(PatientDTO patient,Long patientId);
    void deletePatient(Long patientId);


}
