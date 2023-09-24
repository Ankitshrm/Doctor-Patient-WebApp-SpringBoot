package com.lattice.hostpitalapp.services.impl;

import com.lattice.hostpitalapp.config.ConstantsValue;
import com.lattice.hostpitalapp.exceptions.ResourceNotFoundException;
import com.lattice.hostpitalapp.models.Doctor;
import com.lattice.hostpitalapp.models.Patient;
import com.lattice.hostpitalapp.payloads.DoctorDTO;
import com.lattice.hostpitalapp.repositories.DoctorRepository;
import com.lattice.hostpitalapp.repositories.PatientRepository;
import com.lattice.hostpitalapp.services.SuggestedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lattice.hostpitalapp.utils.HelperClass.findSpecialistViaSymptoms;

@Service
public class SuggestionImpl implements SuggestedService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Object getAllSuggestedDoctors(Long patientId) {
        Patient patient = this.patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient","patientID",patientId));
        String location = patient.getCity();
        String symptom = patient.getSymptom();

        if(!CheckDoctorsByLocation(location)){
            return ConstantsValue.DOCTOR_NOT_IN_LOCATION;
        }else if (!CheckDoctorsBySymptoms(symptom)) {
            return ConstantsValue.DOCTOR_NOT_OF_SPECIALITY;
        } else if (CheckDoctorsByLocationButNotSymptoms(location,symptom)) {
            return ConstantsValue.DOCTOR_NOT_OF_SPECIALITY;
        } else if(CheckDoctorsByLocation(location) && CheckDoctorsBySymptoms(symptom)){
            List<Doctor> allDoc = listingAllDoctor(location,symptom);
            return allDoc.stream().map(it -> this.modelMapper.map(it, DoctorDTO.class)).toList();
        }
        return ConstantsValue.DOCTOR_NOT_OF_SPECIALITY;
    }

    public boolean CheckDoctorsByLocation(String location) {
        List<Doctor> list = this.doctorRepository.findByCity(location);
        return list.size() != 0;
    }

    private boolean CheckDoctorsBySymptoms(String symptom) {
        String specialist =findSpecialistViaSymptoms(symptom);
        List<Doctor> list = this.doctorRepository.findBySpeciality(specialist);
        return list.size() != 0;
    }

    private boolean CheckDoctorsByLocationButNotSymptoms(String location,String symptom) {
        List<Doctor> list = this.doctorRepository.findByCity(location);
        String specialist =findSpecialistViaSymptoms(symptom);
        for(Doctor d : list) {
            if (d.getSpeciality().equals(specialist)) {
                return false;
            }
        }
        return true;
    }


    private List<Doctor> listingAllDoctor(String location, String symptom) {
        String specialist =findSpecialistViaSymptoms(symptom);
        return this.doctorRepository.findByCityAndSpeciality(location,specialist);
    }
}
