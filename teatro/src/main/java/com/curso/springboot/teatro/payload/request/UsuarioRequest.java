package com.curso.springboot.teatro.payload.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioRequest {
    
    @NotBlank
    private String nombre;

    @NotBlank
    private String usuario;

    @NotBlank
    private String contrasenna;
    
    private Set<String> perfiles;

}
