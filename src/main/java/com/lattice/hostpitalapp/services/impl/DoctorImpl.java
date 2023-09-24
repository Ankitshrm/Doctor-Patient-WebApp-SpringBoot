package com.lattice.hostpitalapp.services.impl;

import com.lattice.hostpitalapp.exceptions.ResourceNotFoundException;
import com.lattice.hostpitalapp.models.Doctor;
import com.lattice.hostpitalapp.payloads.DoctorDTO;
import com.lattice.hostpitalapp.repositories.DoctorRepository;
import com.lattice.hostpitalapp.services.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lattice.hostpitalapp.utils.HelperClass.removeSpaceAndSmallCases;

@Service
public class DoctorImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = this.modelMapper.map(doctorDTO,Doctor.class);
        String speciality =removeSpaceAndSmallCases(doctor.getSpeciality());
        String city =removeSpaceAndSmallCases(doctor.getCity());
        doctor.setCity(city);
        doctor.setSpeciality(speciality);
        Doctor savedDoctor = this.doctorRepository.save(doctor);
        return this.modelMapper.map(savedDoctor,DoctorDTO.class);
    }

    @Override
    public DoctorDTO getDoctorById(Long doctorId) {
        Doctor doctor =this.doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor"," DoctorId ",doctorId));
        return this.modelMapper.map(doctor,DoctorDTO.class);
    }

    @Override
    public List<DoctorDTO> getAllDoctor() {
        List<Doctor> allUsers = this.doctorRepository.findAll();
        return allUsers.stream().map(it ->  this.modelMapper.map(it,DoctorDTO.class)).toList();
    }

    @Override
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO, Long doctorId) {
        Doctor doctor = this.modelMapper.map(doctorDTO,Doctor.class);
        Doctor findDoctor =this.doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor"," DoctorId ",doctorId));
        findDoctor.setName(doctor.getName());
        findDoctor.setCity(doctor.getCity());
        findDoctor.setEmail(doctor.getEmail());
        findDoctor.setSpeciality(doctor.getSpeciality());
        findDoctor.setPassword(doctor.getPassword());
        findDoctor.setPhoneNumber(doctor.getPhoneNumber());
        return this.modelMapper.map(findDoctor,DoctorDTO.class);

    }



    @Override
    public void deleteDoctor(Long doctorId) {
        Doctor doctor =this.doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor"," DoctorId ",doctorId));
        this.doctorRepository.delete(doctor);
    }
}
