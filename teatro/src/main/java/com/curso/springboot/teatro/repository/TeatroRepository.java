package com.curso.springboot.teatro.repository;

import com.curso.springboot.teatro.model.Teatro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeatroRepository extends JpaRepository<Teatro, Integer> {
    
}
