package com.curso.springboot.teatro.service;

import java.util.List;

import com.curso.springboot.teatro.model.Perfil;
import com.curso.springboot.teatro.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    
    @Autowired
    private PerfilRepository perfilRepository;

    // READ
    public List<Perfil> listarPerfiles() {
        return perfilRepository.findAll();
    }





}
