package com.curso.springboot.teatro.service;

import com.curso.springboot.teatro.model.UsuarioDetails;
import com.curso.springboot.teatro.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService{
    
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuarioRequest) throws UsernameNotFoundException {
       return usuarioRepository.findByUsuario(usuarioRequest).map(usuario -> {
           return new UsuarioDetails(usuario);
       }).orElseThrow(()-> new UsernameNotFoundException("Usuario " + usuarioRequest + " no encontrado."));
    }
}