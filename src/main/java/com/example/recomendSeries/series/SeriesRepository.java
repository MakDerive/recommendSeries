package com.example.recomendSeries.series;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
	Optional<Series> findByName(String name);
	List<Series> findAll();
	List<Series> findByNameContainingIgnoreCase(String name);
}
