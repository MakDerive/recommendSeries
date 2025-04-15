package com.example.recomendSeries;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JavaCore {
	
	@Value("${testing.app.secret}")
	private String secret;
	
	@Value("${testing.app.lifetime")
	private int lifetime;
	
	public String generateToken(Authentication authentication) {
		
	}
	
	
}
