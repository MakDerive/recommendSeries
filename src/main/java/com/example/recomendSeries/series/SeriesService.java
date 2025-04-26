package com.example.recomendSeries.series;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SeriesService {
	
	@Autowired
	SeriesRepository seriesRepository;

	public List<Series> getSeries() {
		List<Series> series = seriesRepository.findAll();
		return series;
	}
	
	

}
