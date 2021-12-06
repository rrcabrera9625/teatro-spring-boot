package com.curso.springboot.teatro.service;

import java.util.List;
import java.util.Optional;

import com.curso.springboot.teatro.model.Perfil;
import com.curso.springboot.teatro.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    
    @Autowired
    private PerfilRepository perfilRepository;

    // CREATE
    public Perfil crearPerfil(Perfil perfil){
        return perfilRepository.save(perfil);
    }

    // READ
    public List<Perfil> listarPerfiles() {
        return perfilRepository.findAll();
    }

    // READ BY NAME
    public Optional<Perfil> obtenerPorNombre(String perfilNombre){
        return perfilRepository.findByPerfilNombre(perfilNombre); 
    }

    // EXIST BY NAME
    public boolean existe(String perfilNombre) {
        return perfilRepository.existsByPerfilNombre(perfilNombre);
    }





}
