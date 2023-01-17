package com.example.authservice.dto;

import com.example.authservice.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private UserRole role;
    private boolean blocked;
}
