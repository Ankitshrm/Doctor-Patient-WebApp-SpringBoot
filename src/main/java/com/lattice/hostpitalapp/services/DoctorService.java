package com.lattice.hostpitalapp.services;

import com.lattice.hostpitalapp.payloads.DoctorDTO;

import java.util.List;

public interface DoctorService {

    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    DoctorDTO getDoctorById(Long doctorId);
    List<DoctorDTO> getAllDoctor();
    DoctorDTO updateDoctor(DoctorDTO doctorDTO, Long doctorId);
    void deleteDoctor(Long doctorId);


}
