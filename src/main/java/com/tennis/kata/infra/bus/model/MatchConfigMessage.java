package com.tennis.kata.infra.bus.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class MatchConfigMessage extends MessageEvent {

	private MatchData game;
	public MatchConfigMessage(String type) {
		super(type);
	}

	@Data
	public static class MatchData {
		private String matchName;
		private boolean douceMode;
		private PlayerDTO playerOne;
		private PlayerDTO playerTwo;
	}
	@Data
	public static class PlayerDTO {
		private String name;
	}
}
