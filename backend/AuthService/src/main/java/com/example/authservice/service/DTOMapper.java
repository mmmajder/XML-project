package com.example.authservice.service;

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
        userResponse.setBlocked(user.getBlocked());
        return userResponse;
    }
}
