package com.curso.springboot.teatro.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {

    @NotBlank
    private String usuario;

    @NotBlank
    private String contrasenna;
    
}
