package com.curso.springboot.teatro.repository;

import com.curso.springboot.teatro.model.Perfil;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    
}