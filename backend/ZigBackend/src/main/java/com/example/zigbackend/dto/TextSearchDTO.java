package com.example.zigbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TextSearchDTO {
    private String textSearch;
    private boolean casesensitive;
    private boolean searchForNeobradjeni;
}
