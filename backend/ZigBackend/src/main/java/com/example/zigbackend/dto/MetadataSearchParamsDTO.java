package com.example.zigbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MetadataSearchParamsDTO {
    private List<MetadataSearchParams> params;
    private boolean searchForNeobradjeni;
}
