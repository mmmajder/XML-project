package com.example.zigbackend.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IzvestajRequest {
    private String start;
    private String end;

    public String toString() {
        return start.replace('.', '_').concat("__").concat(end.replace('.', '_'));
    }
}
