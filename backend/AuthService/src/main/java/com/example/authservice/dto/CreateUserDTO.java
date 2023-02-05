package com.example.authservice.dto;

import com.example.authservice.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateUserDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private boolean isDeleted;
    private UserRole userRole;
}
