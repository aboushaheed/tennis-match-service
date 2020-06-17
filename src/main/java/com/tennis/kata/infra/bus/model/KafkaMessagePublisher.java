package com.tennis.kata.infra.bus.model;

 import com.tennis.kata.domaine.match.model.CreateMatchRequest;
 import com.tennis.kata.infra.bus.PublisherService;
import com.tennis.kata.infra.bus.mapper.KafkaMatchConfigMapper;
 import lombok.extern.slf4j.Slf4j;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
@Slf4j
public class KafkaMessagePublisher implements PublisherService {

	private Source source;
	private KafkaMatchConfigMapper kafkaMatchConfigMapper;

	@Autowired
	public KafkaMessagePublisher(
		Source source,  KafkaMatchConfigMapper kafkaMatchConfigMapper) {
		this.source = source;
		this.kafkaMatchConfigMapper = kafkaMatchConfigMapper;
	}

	@Override
	public void publishMatchConfig(CreateMatchRequest createMatchRequest) {
		log.info("creation of a new match : {}",createMatchRequest);
		source.output().send(MessageBuilder.withPayload(kafkaMatchConfigMapper.mapMatchConfig(createMatchRequest)).build());

	}
}
