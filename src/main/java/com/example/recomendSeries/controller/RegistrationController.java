package com.example.recomendSeries.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
	
	
	public String getRegistrationForm() {
		return "registration";
	}
	
}
