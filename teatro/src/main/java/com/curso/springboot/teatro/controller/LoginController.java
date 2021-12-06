package com.curso.springboot.teatro.controller;

import javax.servlet.http.HttpServletResponse;

import com.curso.springboot.teatro.config.security.JwtUtils;
import com.curso.springboot.teatro.config.security.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

    @GetMapping(SecurityConstants.LOGIN_URL)
    public String Login() {
        return "login";
    }

    @PostMapping(SecurityConstants.LOGIN_URL)
	public ResponseEntity<?> autenticarUsuario(String usuario, String contrasenna, HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuario, contrasenna));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		return ResponseEntity.ok().header("Authentication", SecurityConstants.TOKEN_PREFIX+jwt).build();	
	}


}
