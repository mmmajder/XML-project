package com.example.autorskapravabackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TextSearchDTO {
    private String textSearch;
    private boolean casesensitive;
    private boolean searchForNeobradjeni;
}
