package com.bnpp.kata;

import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bnpp.kata.tennis.TennisGame;
import static com.bnpp.kata.tennis.constant.TennisGameConstants.*;
import com.bnpp.kata.tennis.exception.InvalidUserInputException;

public class TennisGameRunner {

	private static final Logger LOGGER = LogManager.getLogger(TennisGameRunner.class);

	public static void main(String[] args) throws InvalidUserInputException {
		new TennisGameRunner().playGame();
	}

	public String playGame() throws InvalidUserInputException {

		BasicConfigurator.configure();

		LOGGER.info(WELCOME_GAME_MESSAGE);
		String gameScore = "";

		Scanner inputScanner = new Scanner(System.in);
		LOGGER.info(ENTER_FIRST_PALYER_NAME_MESSAGE);
		String playerOneName = inputScanner.nextLine();
		LOGGER.info(ENTER_SECOND_PALYER_NAME_MESSAGE);
		String playerTwoName = inputScanner.nextLine();
		TennisGame tennisGame = new TennisGame(playerOneName, playerTwoName);

		String winningIndicatorMessage = String.format(PLAYER_INDICATE_MESSAGE, playerOneName, playerTwoName);

		LOGGER.info(START_GAME_MESSAGE);
		do {
			try {
				LOGGER.info(winningIndicatorMessage);
				char winningIndicator = inputScanner.next().charAt(0);
				tennisGame.addServicePointToWinner(String.valueOf(winningIndicator));
				gameScore = tennisGame.computeGameScore();
				String scoreDetails = String.format(CURRENT_GAME_SCORE_MESSAGE, gameScore);
				LOGGER.info(scoreDetails);
			} catch (InvalidUserInputException e) {
				LOGGER.warn(e.getMessage());
			}
		} while (isGameInProgress(gameScore));

		inputScanner.close();
		LOGGER.info(GAME_OVER_MESSAGE);
		return gameScore;
	}

	private boolean isGameInProgress(String gameScore) {
		return gameScore.indexOf(WON_THE_GAME_SCORE_RESULT) == GAME_SCORE_COMP_NEGATIVE_INDEX;
	}
}