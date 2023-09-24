package com.lattice.hostpitalapp.repositories;

import com.lattice.hostpitalapp.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByCityAndSpeciality(String city, String speciality);
    List<Doctor> findBySpeciality(String speciality);
    List<Doctor> findByCity(String city);

}
