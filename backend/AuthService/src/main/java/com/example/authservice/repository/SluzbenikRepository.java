package com.example.authservice.repository;

import com.example.authservice.model.Sluzbenik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SluzbenikRepository extends JpaRepository<Sluzbenik, Long> {
}
