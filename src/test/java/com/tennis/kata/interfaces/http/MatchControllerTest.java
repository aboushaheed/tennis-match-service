package com.tennis.kata.interfaces.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.kata.domaine.match.model.CreateMatchRequest;
import com.tennis.kata.domaine.match.model.CreateMatchResponse;
import com.tennis.kata.domaine.match.model.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MatchController.class)
public class MatchControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MatchService matchService;


	@Test
	public void should_createMatch_and_succeedsWith200() throws Exception {
		CreateMatchRequest createMatchRequest = CreateMatchRequest.builder()
			.douceMode(true)
			.matchName("match name")
			.playerOneName("player one name")
			.playerTwoName("player two name")
			.build();

		CreateMatchResponse createMatchResponse = CreateMatchResponse.builder()
			.douceMode(true)
			.matchName("match name")
			.playerOneName("player one name")
			.playerTwoName("player two name")
			.build();

		when(matchService.create(any())).thenReturn(createMatchResponse);

		mockMvc.perform(post("/matchs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(createMatchRequest)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.douceMode").value(true))
			.andExpect(jsonPath("$.matchName").value("match name"))
			.andExpect(jsonPath("$.playerOneName").value("player one name"))
			.andExpect(jsonPath("$.playerTwoName").value("player two name"))
		;
	}
	@Test
	public void should_validates_and_failsWith400() throws Exception {
		CreateMatchRequest createMatchRequest = new CreateMatchRequest();

		mockMvc.perform(post("/matchs")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(createMatchRequest)))
			.andExpect(status().isBadRequest())
		;
	}

}