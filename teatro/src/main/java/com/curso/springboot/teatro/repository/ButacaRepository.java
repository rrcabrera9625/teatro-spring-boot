package com.curso.springboot.teatro.repository;

import com.curso.springboot.teatro.model.Butaca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButacaRepository extends JpaRepository<Butaca, Integer> {
    
}
