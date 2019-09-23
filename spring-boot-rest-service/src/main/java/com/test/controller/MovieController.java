package com.test.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.MovieDTO;
import com.test.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {
	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/movies")
	public List<MovieDTO> getAll() {
		return movieService.findAll();
	}

	@GetMapping("/movies/{id}")
	public ResponseEntity<MovieDTO> get(@PathVariable Long id) {
		MovieDTO movie = movieService.findOne(id);
		return ResponseEntity.ok(movie);
	}

	@PostMapping("/movies")
	public ResponseEntity<MovieDTO> create(@RequestBody @Valid MovieDTO movie) throws URISyntaxException {
		MovieDTO result = movieService.save(movie);
		return ResponseEntity.created(new URI("/api/movies/" + result.getMovieId())).body(result);
	}

	@PutMapping("/movies")
	public ResponseEntity<MovieDTO> updateJob(@RequestBody @Valid MovieDTO movie) {
		MovieDTO result = movieService.update(movie);
		return ResponseEntity.ok().body(result);
	}

	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		movieService.delete(id);
		return ResponseEntity.ok().build();
	}
}
