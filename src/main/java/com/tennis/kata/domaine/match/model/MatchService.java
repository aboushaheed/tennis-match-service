package com.tennis.kata.domaine.match.model;

import com.tennis.kata.infra.bus.model.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {
	private KafkaMessagePublisher kafkaMessagePublisher;

	@Autowired
	public MatchService(KafkaMessagePublisher kafkaMessagePublisher) {
		this.kafkaMessagePublisher = kafkaMessagePublisher;
	}

	public CreateMatchResponse create(CreateMatchRequest createMatchRequest)	{
		kafkaMessagePublisher.publishMatchConfig(createMatchRequest);
		return CreateMatchResponse.builder()
			.matchName(createMatchRequest.getMatchName())
			.playerOneName(createMatchRequest.getPlayerOneName())
			.playerTwoName(createMatchRequest.getPlayerTwoName())
			.douceMode(createMatchRequest.isDouceMode())
			.build();
	}
}
