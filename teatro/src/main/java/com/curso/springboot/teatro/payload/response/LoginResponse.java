package com.curso.springboot.teatro.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LoginResponse {
    
    private String Token;

    LoginResponse(){}

}
