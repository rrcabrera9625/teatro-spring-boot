package com.curso.springboot.teatro.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UsuarioDetails extends Usuario implements UserDetails {

    public UsuarioDetails(final Usuario usuario) {
        super(usuario);
    }
    
    private List<String> getPrivileges(Collection<Perfil> perfiles) {

        List<String> privileges = new ArrayList<>();
        for (Perfil perfil : perfiles) {
            privileges.add(perfil.getPerfilNombre());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges(getPerfiles().stream()
        .collect(Collectors.toList())));       
    }

    @Override
    public String getPassword() {
        return super.getContrasenna();
    }

    @Override
    public String getUsername() {
        return super.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UsuarioDetails that = (UsuarioDetails) obj;
        return Objects.equals(getId(), that.getId());
    }
}
