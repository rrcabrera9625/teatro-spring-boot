package com.curso.springboot.teatro.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.curso.springboot.teatro.model.Perfil;
import com.curso.springboot.teatro.model.Usuario;
import com.curso.springboot.teatro.payload.request.UsuarioRequest;
import com.curso.springboot.teatro.repository.PerfilRepository;
import com.curso.springboot.teatro.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

    // CREATE
	public Usuario crearUsuario(UsuarioRequest usuarioRequest) {
		Usuario newUsuario = new Usuario();
		newUsuario.setNombre(usuarioRequest.getNombre());
		newUsuario.setUsuario(usuarioRequest.getUsuario());
		newUsuario.setContrasenna(passwordEncoder.encode(usuarioRequest.getContrasenna()));
		newUsuario.setPerfiles(obtenerPerfiles(usuarioRequest.getPerfiles()));
		return usuarioRepository.save(newUsuario);
	}

	// READ
	public Page<Usuario> listarUsuario(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

	// READ BY ID
	public Optional<Usuario> obtenerUsuarioPorId(int id) {
		return usuarioRepository.findById(id);
	}

	// FIND BY USERNAME
	public Optional<Usuario> obtenerUsuarioPorUsurio(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	// UPDATE
	public Usuario editarUsuario(int id, UsuarioRequest usuarioRequest) {
		Usuario newUsuario = usuarioRepository.findById(id).get();
		newUsuario.setNombre(usuarioRequest.getNombre());
		newUsuario.setUsuario(usuarioRequest.getUsuario());
		newUsuario.setPerfiles(obtenerPerfiles(usuarioRequest.getPerfiles()));
		return usuarioRepository.save(newUsuario);
	}

	// DELETE
	public void eliminarUsuario(int id) {
		Usuario usuario = usuarioRepository.findById(id).get();
		usuarioRepository.delete(usuario);
	}

	// GET CURRENT ROLES FROM REQUEST
	public Set<Perfil> obtenerPerfiles(Set<String> perfilesRequest) {
		Set<Perfil> perfiles = new HashSet<>();

		if (perfilesRequest == null || perfilesRequest.isEmpty()) {
			return perfiles;
		} else {
			perfilesRequest.forEach(nombrePerfil -> {
				if (perfilRepository.findByPerfilNombre(nombrePerfil).isPresent()) {
					Perfil perfil = perfilRepository.findByPerfilNombre(nombrePerfil).get();
					perfilRepository.save(perfil);
					perfiles.add(perfil);
				}
			});
		}
		return perfiles;
	}
}
    
