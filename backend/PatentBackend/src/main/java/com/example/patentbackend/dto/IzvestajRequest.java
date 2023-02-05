package com.example.patentbackend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class IzvestajRequest {
    private String start;
    private String end;

    @Override
    public String toString() {
        return start.replace('.', '_').concat("__").concat(end.replace('.', '_'));
    }
}
