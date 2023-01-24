package com.example.zigbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootApplication
public class ZigBackendApplication {

	public static void main(String[] args) throws JAXBException, IOException {
		SpringApplication.run(ZigBackendApplication.class, args);
	}
}
