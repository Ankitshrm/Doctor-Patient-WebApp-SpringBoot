package com.lattice.hostpitalapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id;
    @NotEmpty
    @Size(min = 3,message = "Name must be at least 3 characters long.")
    private String name;
    @NotEmpty
    @Size(max =20,message = "City must be at most 20 characters long.")
    private String city;
    @Email
    private String email;
    @NotEmpty
    @NumberFormat
    @Size(min=10,max=10,message = "Phone number must be at 10 digits.")
    private String phoneNumber;
    @NotEmpty
    private String symptom;
}
