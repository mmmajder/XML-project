package com.example.autorskapravabackend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class SimpleUser {
    private String name;
    private String email;
    private String surname;
}
