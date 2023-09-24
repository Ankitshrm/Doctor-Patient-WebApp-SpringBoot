package com.lattice.hostpitalapp.services.impl;

import com.lattice.hostpitalapp.config.ConstantsValue;
import com.lattice.hostpitalapp.exceptions.ResourceNotFoundException;
import com.lattice.hostpitalapp.models.Doctor;
import com.lattice.hostpitalapp.models.Patient;
import com.lattice.hostpitalapp.payloads.DoctorDTO;
import com.lattice.hostpitalapp.payloads.PatientDTO;
import com.lattice.hostpitalapp.repositories.DoctorRepository;
import com.lattice.hostpitalapp.repositories.PatientRepository;
import com.lattice.hostpitalapp.services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static com.lattice.hostpitalapp.utils.HelperClass.findSpecialistViaSymptoms;
import static com.lattice.hostpitalapp.utils.HelperClass.removeSpaceAndSmallCases;

@Service
public class PatientImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) {
        Patient patient = this.modelMapper.map(patientDTO,Patient.class);
        String symptom =removeSpaceAndSmallCases(patient.getSymptom());
        String city =removeSpaceAndSmallCases(patient.getCity());
        patient.setCity(city);
        patient.setSymptom(symptom);
        Patient savedPatient = this.patientRepository.save(patient);
        return this.modelMapper.map(savedPatient, PatientDTO.class);
    }

    @Override
    public PatientDTO getPatientById(Long patientId) {
        Patient patient= this.patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient"," PatientId ",patientId));
        return this.modelMapper.map(patient,PatientDTO.class);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> allPatients = this.patientRepository.findAll();
        return allPatients.stream().map(it -> this.modelMapper.map(it,PatientDTO.class)).toList();
    }


    @Override
    public PatientDTO updatePatient(PatientDTO patientDTO, Long patientId) {
        Patient patient = this.modelMapper.map(patientDTO,Patient.class);
        Patient findPatient= this.patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient"," PatientId ",patientId));
        findPatient.setCity(patient.getCity());
        findPatient.setEmail(patient.getEmail());
        findPatient.setSymptom(patient.getSymptom());
        findPatient.setName(patient.getName());
        findPatient.setPhoneNumber(patient.getPhoneNumber());
        return this.modelMapper.map(findPatient,PatientDTO.class);
    }

    @Override
    public void deletePatient(Long patientId) {
        Patient findPatient= this.patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient"," PatientId ",patientId));
        this.patientRepository.delete(findPatient);
    }
}
