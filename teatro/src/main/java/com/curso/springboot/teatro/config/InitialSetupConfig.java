package com.curso.springboot.teatro.config;

import java.util.Set;

import com.curso.springboot.teatro.config.security.SecurityConstants;
import com.curso.springboot.teatro.model.Perfil;
import com.curso.springboot.teatro.model.Usuario;
import com.curso.springboot.teatro.repository.PerfilRepository;
import com.curso.springboot.teatro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialSetupConfig {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        // PERFIL ADMINISTRADOR
        Perfil perfilAdmin = crearPerfilInicial("PERFIL_ADMIN", "Administrador");

        // PERFIL VENDEDOR
        Perfil perfilVendedor = crearPerfilInicial("PERFIL_VENDEDOR", "Vendedor");

        // USUARIO ADMIN 
        if (perfilAdmin != null) {
            crearUsuarioInicial("Juan Pérez Pérez", "admin", SecurityConstants.ADMIN_PWD, Set.of(perfilAdmin));
        }

        // USUARIO VENDEDOR 
        if (perfilVendedor != null) {
            crearUsuarioInicial("Ramón Rodríguez Rodríguez", "vendedor", SecurityConstants.VENDEDOR_PWD, Set.of(perfilVendedor));
        }
    }

    @Transactional
    public Perfil crearPerfilInicial(String perfilNombre, String perfilText) {
        if (!perfilRepository.findByPerfilNombre(perfilNombre).isPresent()){
                Perfil newPerfil = new Perfil(perfilNombre, perfilText);
                perfilRepository.save(newPerfil);
                return newPerfil;
        }
        return perfilRepository.findByPerfilNombre(perfilNombre).get();
    }

    @Transactional
    public Usuario crearUsuarioInicial(String nombre, String usuario, String contrasenna,
            Set<Perfil> perfiles) {
        if (!usuarioRepository.findByUsuario(usuario).isPresent()) {
            Usuario newUsuario = new Usuario();
            newUsuario.setNombre(nombre);
            newUsuario.setUsuario(usuario);
            newUsuario.setContrasenna(passwordEncoder.encode(contrasenna));
            newUsuario.setPerfiles(perfiles);
            usuarioRepository.save(newUsuario);
            return newUsuario;
        }
        return usuarioRepository.findByUsuario(usuario).get();
    }
}
