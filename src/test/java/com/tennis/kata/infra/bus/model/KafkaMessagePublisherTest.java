package com.tennis.kata.infra.bus.model;

import com.tennis.kata.domaine.match.model.CreateMatchRequest;
import com.tennis.kata.infra.bus.mapper.KafkaMatchConfigMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KafkaMessagePublisherTest {
	private KafkaMessagePublisher kafkaMessagePublisher;
	private MessageChannel messageChannel;
	private KafkaMatchConfigMapper kafkaMatchConfigMapper;
	@BeforeEach
	public void init() {
		Source source = mock(Source.class);
		messageChannel = mock(MessageChannel.class);
		when(source.output()).thenReturn(messageChannel);
		kafkaMatchConfigMapper = mock(KafkaMatchConfigMapper.class);
		kafkaMessagePublisher = new KafkaMessagePublisher(source,kafkaMatchConfigMapper);
	}
	@Test
	public void should_call_source_when_publishing_match_config() {
		//when
		CreateMatchRequest createMatchRequest = CreateMatchRequest.builder()
			.douceMode(true)
			.matchName("match name")
			.playerOneName("player one name")
			.playerTwoName("player two name")
			.build();
		ConfigMessage configMessage = new ConfigMessage();
		ConfigMessage.MatchData matchData = new MatchConfigMessage.MatchData();
		matchData.setDouceMode(true);
		matchData.setMatchName("match name");
		MatchConfigMessage.PlayerDTO playerDTOOne = new MatchConfigMessage.PlayerDTO();
		playerDTOOne.setName("player one name");
		MatchConfigMessage.PlayerDTO playerDTOTwo = new MatchConfigMessage.PlayerDTO();
		playerDTOTwo.setName("player two name");

		matchData.setPlayerOne(playerDTOOne);
		matchData.setPlayerTwo(playerDTOTwo);
		configMessage.setGame(matchData);
		when(kafkaMatchConfigMapper.mapMatchConfig(createMatchRequest)).thenReturn(configMessage);
		kafkaMessagePublisher.publishMatchConfig(createMatchRequest);
		//then
		ArgumentCaptor<Message<MessageEvent>> eventArgumentCaptor = ArgumentCaptor.forClass(Message.class);
		verify(messageChannel).send(eventArgumentCaptor.capture());
		assertThat(eventArgumentCaptor.getValue().getPayload()).isInstanceOf(ConfigMessage.class);
	}
}