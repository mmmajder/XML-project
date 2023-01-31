package com.example.patentbackend;

import com.example.patentbackend.model.*;
import com.example.patentbackend.transformer.PatentTransformer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatentBackendApplication.class, args);
    }
}
