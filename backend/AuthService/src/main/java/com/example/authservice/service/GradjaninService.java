package com.example.authservice.service;

import com.example.authservice.model.Gradjanin;
import com.example.authservice.model.User;
import com.example.authservice.repository.GradjaninRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GradjaninService {

    private final GradjaninRepository gradjaninRepository;

    public void saveUser(User user) {
        gradjaninRepository.save((Gradjanin) user);
    }
}
