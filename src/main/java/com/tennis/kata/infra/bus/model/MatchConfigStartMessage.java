package com.tennis.kata.infra.bus.model;

import lombok.Data;

@Data
public class MatchConfigStartMessage extends MatchConfigMessage {
	public static final String TYPE = "com.tennis.kata.game.start";

	public MatchConfigStartMessage() {
		super(TYPE);
	}
}
