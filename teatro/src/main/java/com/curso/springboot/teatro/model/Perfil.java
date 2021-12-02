package com.curso.springboot.teatro.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name = "perfil")
public class Perfil {
    
    @Id
    @Column(name = "PERFIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "perfilNombre") 
    private String perfilNombre;

    @Column(name = "perfilTexto")
    private String perfilTexto;

    @ManyToMany(mappedBy = "perfiles", fetch = FetchType.EAGER)
    private Set<Usuario> usuarios = new HashSet<>(); 
    
    public Perfil() {
    }

    public Perfil(String perfilNombre, String perfilTexto) {
        this.perfilNombre = perfilNombre;
        this.perfilTexto = perfilTexto;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.getPerfiles().add(this);
    }

    public void adicionarUsuarios(Set<Usuario> usuarios) {
        usuarios.forEach(this::adicionarUsuario);
    }

    public void removerUsuarios(Usuario usuario) {
        usuarios.remove(usuario);
        usuario.getPerfiles().remove(this);
    }
}
