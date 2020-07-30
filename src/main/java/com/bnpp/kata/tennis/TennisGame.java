package com.bnpp.kata.tennis;

import static com.bnpp.kata.tennis.constant.TennisGameConstants.*;
import com.bnpp.kata.tennis.model.Player;

public class TennisGame {
	
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
		
		if (firstPlayer.getScoredPoint() == secondPlayer.getScoredPoint()) {
			if (firstPlayer.getScoredPoint() >= 3) {
				gameScore = "Deuce";
			} else {
				gameScore = getGameAllScore();
			}
		} else if (isPlayerWonTheGame()) {
			gameScore = getTopScoredPlayerName() + WON_THE_GAME_SCORE_RESULT;
		} else {
			gameScore = getGameScore();
		}
		
		return gameScore;
	}

	private String getTopScoredPlayerName() {
		return firstPlayer.getScoredPoint() > secondPlayer.getScoredPoint() ? firstPlayer.getName() : secondPlayer.getName();
	}
	
	private boolean isPlayerWonTheGame() {
		return (firstPlayer.getScoredPoint() >= MINIMUM_WINNING_SCORE
				|| secondPlayer.getScoredPoint() >= MINIMUM_WINNING_SCORE) && isPlayerWinningMinimumDifference();
	}

	private boolean isPlayerWinningMinimumDifference() {
		return Math.abs(firstPlayer.getScoredPoint() - secondPlayer.getScoredPoint()) >= MINIMUM_WINNING_DIFFERENCE_POINT;
	}

	private String getGameScore() {
		return getScores()[firstPlayer.getScoredPoint()] + GAME_SCORE_SEPARATOR
				+ getScores()[secondPlayer.getScoredPoint()];
	}

	private String getGameAllScore() {
		return getScores()[firstPlayer.getScoredPoint()] + GAME_SCORE_SEPARATOR + GAME_SCORE_ALL;
	}

	public void incrementFirstPlayerScore() {
		firstPlayer.incrementPlayerScore();
	}

	public void incrementSecondPlayerScore() {
		secondPlayer.incrementPlayerScore();
	}

}