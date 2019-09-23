package com.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 
import java.util.Arrays;
import java.util.List;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org. springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
 
import com.test.dto.MovieDTO;
import com.test.service.MovieService;
 
@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {
	@Autowired
	private MockMvc mockMvc;
 
	@MockBean
	private MovieService movieService;
 
	@Test
	@WithMockUser(username = "admin", password = "password", roles = "USER")
	public void findAll() throws Exception {
		// given
		MovieDTO movie = new MovieDTO();
		movie.setMovieId(1L);
		movie.setName("New Movie");
		movie.setPrice(4.2);
 
		List<MovieDTO> stocks = Arrays.asList(movie);
		Mockito.when(movieService.findAll()).thenReturn(stocks);
 
		// when + then
		this.mockMvc.perform(get("/api/movies")).andExpect(status().isOk());
	}
}
