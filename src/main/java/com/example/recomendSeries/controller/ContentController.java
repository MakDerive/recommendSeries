package com.example.recomendSeries.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recomendSeries.series.Series;
import com.example.recomendSeries.config.UserDetailsImpl;
import com.example.recomendSeries.series.SeriesService;


@Controller
public class ContentController {
	
	SeriesService seriesService;
	
	public ContentController(SeriesService seriesService) {
		this.seriesService = seriesService;
	}
	
    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            model.addAttribute("username", userDetails.getRealUsername());
            model.addAttribute("email", userDetails.getEmail());
        }
        List<Series> allSeries = seriesService.getSeries();
        model.addAttribute("allSeries",allSeries);
        return "index";
    }
    
    @GetMapping("/search")
    public String searchSeries(@RequestParam(required = false) String query, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
            model.addAttribute("username", userDetails.getRealUsername());
        }
        
        List<Series> searchResults = Collections.emptyList();
        if (query != null && !query.trim().isEmpty()) {
            searchResults = seriesService.searchSeries(query);
        }
        
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("query", query != null ? query : "");
        return "search";
    }
}
