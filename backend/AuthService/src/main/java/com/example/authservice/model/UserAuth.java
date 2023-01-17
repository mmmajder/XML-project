package com.example.authservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    private Boolean isEnabled;
    private Boolean deleted = false;
    @ManyToMany
    private List<Role> roles;
}
