package com.example.zigbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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