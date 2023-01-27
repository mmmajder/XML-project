package com.example.zigbackend.dto;

import com.example.zigbackend.model.EBoja;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NeededPrilogsDTO {
    @XmlElementWrapper(name = "neededPrilogsDTO")
    private List<String> neededPrilogs;
}
