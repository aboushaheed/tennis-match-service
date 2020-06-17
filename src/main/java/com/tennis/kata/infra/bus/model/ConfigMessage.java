package com.tennis.kata.infra.bus.model;

import lombok.Data;

@Data
public class ConfigMessage extends MatchConfigMessage {
	public static final String TYPE = "com.tennis.kata.match.config";

	public ConfigMessage() {
		super(TYPE);
	}
}
