package com.example.zigbackend;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@SpringBootApplication
public class ZigBackendApplication {

	public static void main(String[] args) throws JAXBException, IOException {
		SpringApplication.run(ZigBackendApplication.class, args);
	}

//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver multipartResolver() {
//		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		multipartResolver.setMaxUploadSize(100000);
//		return multipartResolver;
//	}
}
