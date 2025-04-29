package com.example.recomendSeries.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recomendSeries.config.UserDetailsImpl;
import com.example.recomendSeries.series.Series;
import com.example.recomendSeries.series.SeriesRepository;
import com.example.recomendSeries.user.User;
import com.example.recomendSeries.user.UserRepository;


@RestController
@RequestMapping("/api/series")
public class SeriesApiController {
	static Logger logger;
	
	SeriesRepository seriesRepository;
	
	UserRepository userRepository;
	
	public SeriesApiController(SeriesRepository seriesRepository, UserRepository userRepository) {
		this.seriesRepository = seriesRepository;
		this.userRepository = userRepository;
		
	}

    @PostMapping("/{seriesId}/like")
    public ResponseEntity<Void> likeSeries(@PathVariable Long seriesId, 
    		@AuthenticationPrincipal UserDetailsImpl userDetails) {
    	Series series = seriesRepository.findById(seriesId).orElseThrow(
    			()->new IllegalArgumentException("Series not found"));
        User user = userRepository.findById(userDetails.getId()).orElseThrow(
        		()->new IllegalArgumentException("User not found"));
        user.getLikedSeries().add(series);
        System.out.println(user.getLikedSeries().get(0).getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{seriesId}/dislike")
    public ResponseEntity<Void> dislikeSeries(@PathVariable Long seriesId,
    		@AuthenticationPrincipal UserDetailsImpl userDetails) {
    	Series series = seriesRepository.findById(seriesId).orElseThrow(
    			()->new IllegalArgumentException("Series not found"));
        User user = userRepository.findById(userDetails.getId()).orElseThrow(
        		()->new IllegalArgumentException("User not found"));
        user.getDislikedSeries().add(series);
        System.out.println(user.getDislikedSeries().get(0).getName());
        return ResponseEntity.ok().build();
    }
}