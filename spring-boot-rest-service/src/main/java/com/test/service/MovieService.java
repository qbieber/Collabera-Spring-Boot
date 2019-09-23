package com.test.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.test.dto.MovieDTO;
import com.test.mapper.MovieMapper;
import com.test.model.Movie;
import com.test.repository.MovieRepository;

@Service
@Transactional
public class MovieService {

	private final MovieRepository movieRepository;
	private final MovieMapper movieMapper;

	public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
		this.movieRepository = movieRepository;
		this.movieMapper = movieMapper;
	}

	public List<MovieDTO> findAll() {
		return movieRepository.findAll().stream().map(m -> movieMapper.toDto(m)).collect(Collectors.toList());
	}

	public MovieDTO findOne(Long id) {
		Optional<Movie> movieOp = movieRepository.findById(id);
		if (movieOp.isPresent()) {
			return movieMapper.toDto(movieOp.get());
		}
		return null;
	}

	public MovieDTO save(MovieDTO movie) {
		Movie entity = movieMapper.toEntity(movie);
		Movie saved = movieRepository.save(entity);
		return movieMapper.toDto(saved);
	}

	public MovieDTO update(MovieDTO movie) {
		Long movieId = movie.getMovieId();
		Optional<Movie> findById = movieRepository.findById(movieId);
		if (findById.isPresent()) {
			Movie m = findById.get();
			m.setCategory(movie.getCategory());
			Movie saved = movieRepository.save(m);
			return movieMapper.toDto(saved);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void delete(Long id) {
		movieRepository.deleteById(id);
	}

}
