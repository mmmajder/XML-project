package com.example.authservice.service;

import com.example.authservice.dto.CreateUserDTO;
import com.example.authservice.dto.UserResponse;
import com.example.authservice.model.User;

public class DTOMapper {
    public static UserResponse getUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setRole(user.getRole());
        userResponse.setSurname(user.getSurname());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        return userResponse;
    }

    public static User getUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setName(createUserDTO.getName());
        user.setSurname(createUserDTO.getSurname());
        user.setPassword(createUserDTO.getPassword());
        user.setPhoneNumber(createUserDTO.getPhoneNumber());
        user.setEmail(createUserDTO.getEmail());
        user.setRole(createUserDTO.getUserRole());
        return user;
    }
}
