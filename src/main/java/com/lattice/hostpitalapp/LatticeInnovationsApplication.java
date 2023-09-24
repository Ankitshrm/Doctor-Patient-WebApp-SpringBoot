package com.lattice.hostpitalapp;

import com.lattice.hostpitalapp.config.ConstantsValue;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static com.lattice.hostpitalapp.utils.HelperClass.InitializeAllValues;

@SpringBootApplication
public class LatticeInnovationsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LatticeInnovationsApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	@Override
	public void run(String... args) throws Exception {
		InitializeAllValues();
	}


}
