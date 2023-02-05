package com.example.authservice.service;

import com.example.authservice.model.UserAuth;
import com.example.authservice.repository.UserAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserAuthService {
    private final UserAuthRepository userAuthRepository;

    public UserAuth save(UserAuth userAuth){
        return userAuthRepository.save(userAuth);
    }
}
