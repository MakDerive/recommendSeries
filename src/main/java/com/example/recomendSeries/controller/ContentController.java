package com.example.recomendSeries.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.recomendSeries.config.UserDetailsImpl;


@Controller
public class ContentController {
    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            model.addAttribute("username", userDetails.getRealUsername());
            model.addAttribute("email", userDetails.getEmail());
        }
        return "index";
    }
}
