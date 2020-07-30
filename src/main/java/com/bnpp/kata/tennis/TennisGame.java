package com.bnpp.kata.tennis;

import com.bnpp.kata.tennis.model.Player;

public class TennisGame {
	
	private static final String FIFTEEN_LOVE_GAME_SCORE = "Fifteen-Love";
	private static final String LOVE_ALL_GAME_SCORE = "Love-All";
	private static final String SECOND_PLAYER_NAME = "SecondPlayer";
	private static final String FIRST_PLAYER_NAME = "FirstPlayer";
	private Player firstPlayer;
	private Player secondPlayer;

	public TennisGame() {
		super();
		firstPlayer = new Player(FIRST_PLAYER_NAME);
		secondPlayer = new Player(SECOND_PLAYER_NAME);
	}

	public int getFirstPlayerScoredPoint() {
		return firstPlayer.getScoredPoint();
	}
	
	public int getSecondPlayerScoredPoint() {
		return secondPlayer.getScoredPoint();
	}

	public String getFirstPlayerName() {
		return firstPlayer.getName();
	}

	public String getSecondPlayerName() {
		return secondPlayer.getName();
	}

	public String computeGameScore() {
		String gameScore;
		
		if (firstPlayer.getScoredPoint() == 1 && secondPlayer.getScoredPoint() == 0) {
			gameScore = FIFTEEN_LOVE_GAME_SCORE;
		} else {
			gameScore = LOVE_ALL_GAME_SCORE;
		}
		
		return gameScore;
	}

	public void incrementFirstPlayerScore() {
		firstPlayer.incrementPlayerScore();
	}

}