package com.tennis.kata.infra.bus.mapper;

import com.tennis.kata.domaine.match.model.CreateMatchRequest;
import com.tennis.kata.infra.bus.model.ConfigMessage;
import com.tennis.kata.infra.bus.model.MatchConfigMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class KafkaMatchConfigMapper {


	public ConfigMessage mapMatchConfig(CreateMatchRequest createMatchRequest) {
		MatchConfigMessage.MatchData matchData = new MatchConfigMessage.MatchData();
		MatchConfigMessage.PlayerDTO playerOne = new MatchConfigMessage.PlayerDTO();
		playerOne.setName(createMatchRequest.getPlayerOneName());
		matchData.setPlayerOne(playerOne);

		MatchConfigMessage.PlayerDTO playerTwo = new MatchConfigMessage.PlayerDTO();
		playerTwo.setName(createMatchRequest.getPlayerTwoName());
		matchData.setPlayerTwo(playerTwo);

		matchData.setMatchName(createMatchRequest.getMatchName());
		matchData.setDouceMode(createMatchRequest.isDouceMode());

		ConfigMessage configMessage = new ConfigMessage();
		configMessage.setId(UUID.randomUUID());
		configMessage.setDate(LocalDateTime.now());
		configMessage.setGame(matchData);

		return configMessage;
	}

}
