package com.tennis.kata.infra.bus;

import com.tennis.kata.domaine.match.model.CreateMatchRequest;

public interface PublisherService {

	void publishMatchConfig(CreateMatchRequest createMatchRequest);
}
