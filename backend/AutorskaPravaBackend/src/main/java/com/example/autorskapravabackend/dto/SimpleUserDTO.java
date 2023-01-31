package com.example.autorskapravabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SimpleUserDTO {
    private String email;
    private String name; // concatenated name and surname or business name

    public SimpleUserDTO(){
        this.email = "";
        this.name = "";
    }
}