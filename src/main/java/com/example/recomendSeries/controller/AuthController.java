package com.example.recomendSeries.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.recomendSeries.dto.LoginDto;
import com.example.recomendSeries.dto.UserDto;
import com.example.recomendSeries.user.UserService;

import jakarta.validation.Valid;




@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(Model model,Authentication authentication) {
    	if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model,Authentication authentication) {
    	if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        
        try {
            userService.registerUser(userDto);
            return "redirect:/login?signup=true";
        } catch (RuntimeException e) {
            model.addAttribute("emailError", e.getMessage());
            return "signup";
        }
    }
}