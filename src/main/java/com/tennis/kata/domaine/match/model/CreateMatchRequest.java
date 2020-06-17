package com.tennis.kata.domaine.match.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchRequest {

	@NotBlank(message = "the name of the player is required")
	private String playerOneName;
	@NotBlank(message = "the name of the player is required")
	private String playerTwoName;
	@NotBlank(message = "the name of the match is required")
	private String matchName;
	private boolean douceMode;
}
