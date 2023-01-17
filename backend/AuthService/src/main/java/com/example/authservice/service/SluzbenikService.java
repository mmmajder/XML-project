package com.example.authservice.service;

import com.example.authservice.model.Sluzbenik;
import com.example.authservice.model.User;
import com.example.authservice.repository.SluzbenikRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SluzbenikService {
    private final SluzbenikRepository sluzbenikRepository;

    public void saveUser(User user) {
        sluzbenikRepository.save((Sluzbenik) user);
    }
}
