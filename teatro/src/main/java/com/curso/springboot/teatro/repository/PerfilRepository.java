package com.curso.springboot.teatro.repository;

import java.util.Optional;

import com.curso.springboot.teatro.model.Perfil;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    Optional<Perfil> findByPerfilNombre(String perfilNombre);

    
}