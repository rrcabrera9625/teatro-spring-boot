package com.curso.springboot.teatro.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name="usuario")
public class Usuario {

    @Id @Column(name = "USUARIO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @Column(name = "nombre")
	private String nombre;

    @Column(name = "usuario")
	private String usuario;

    @Column(name = "contrasenna")
	private String contrasenna;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE} )
    @JoinTable(name = "USUARIO_PERFIL", joinColumns = {
    	@JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")}, inverseJoinColumns = {
    	@JoinColumn(name = "PERFIL_ID", referencedColumnName = "PERFIL_ID")})
     private Set<Perfil> perfiles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(Usuario usuario) {
		this.nombre = usuario.getNombre();
        this.usuario = usuario.getUsuario();
		this.contrasenna = usuario.getContrasenna();
		this.perfiles = usuario.getPerfiles();
	}

    public void adicionarPerfil(Perfil perfil) {
        perfiles.add(perfil);
        perfil.getUsuarios().add(this);
    }

    public void adicionarPerfiles(Set<Perfil> perfiles) {
        perfiles.forEach(this::adicionarPerfil);
    }

    public void removerPerfil(Perfil perfil) {
        perfiles.remove(perfil);
        perfil.getUsuarios().remove(this);
    }

    
}
