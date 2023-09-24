package com.lattice.hostpitalapp.repositories;

import com.lattice.hostpitalapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

