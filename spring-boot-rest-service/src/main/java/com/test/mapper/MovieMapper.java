package com.test.mapper;

import org.springframework.stereotype.Component;

import com.test.dto.MovieDTO;
import com.test.model.Movie;

@Component
public class MovieMapper {

	public Movie toEntity(MovieDTO dto) {
		Movie movie = new Movie();
		movie.setName(dto.getName());
		movie.setCategory(dto.getCategory());
		movie.setPrice(dto.getPrice());
		movie.setDescription(dto.getDescription());
		movie.setStudio(dto.getStudioId());
		movie.setImage(dto.getImage());
		return movie;
	}

	public MovieDTO toDto(Movie entity) {
		MovieDTO movie = new MovieDTO();
		movie.setMovieId(entity.getId());
		movie.setName(entity.getName());
		movie.setCategory(entity.getCategory());
		movie.setPrice(entity.getPrice());
		movie.setDescription(entity.getDescription());
		movie.setStudioId(entity.getStudio());
		movie.setImage(entity.getImage());
		return movie;
	}
}
