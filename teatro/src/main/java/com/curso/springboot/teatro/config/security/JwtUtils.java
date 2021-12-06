package com.curso.springboot.teatro.config.security;

import java.util.Date;

import javax.crypto.SecretKey;

import com.curso.springboot.teatro.model.UsuarioDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateJwtToken(Authentication authentication) {

		UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((usuarioDetails.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(key)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
			return true;
		} catch (SecurityException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
    
}
