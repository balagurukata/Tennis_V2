package com.bnpp.kata.tennis;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.bnpp.kata.tennis.exception.InvalidUserInputException;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TennisGameTest {
	
	TennisGame tennisGame;
	
	@Before
	public void initialize() {
		tennisGame = new TennisGame();
	}

	@Test
	public void firstPlayerScoredShouldBeZeroBeforeGameBegins() {
		
		assertThat(0, is(tennisGame.getFirstPlayer().getScoredPoint()));
	}
	
	@Test
	public void secondPlayerScoredShouldBeZeroBeforeGameBegins() {

		assertThat(0, is(tennisGame.getSecondPlayer().getScoredPoint()));
	}
	
	@Test
	public void firstPlayerNameShouldBeInitializedAsFirstPlayerBeforeGameBegins() {

		assertThat("FirstPlayer", is(tennisGame.getFirstPlayer().getName()));
	}
	
	@Test
	public void secondPlayerNameShouldBeInitializedAsSecondPlayerBeforeGameBegins() {

		assertThat("SecondPlayer", is(tennisGame.getSecondPlayer().getName()));
	}
	
	@Test
	public void gameScoreShouldBeLoveAllBeforeGameBegins() {
		
		assertThat("Love-All", is(tennisGame.computeGameScore()));
	}
	
    @Test
    public void firstPlayerScoreShouldBeFifteenLoveWhenFirstPlayerWontheFirstService() {
    	
    	updatePlayerScore(1,0);
        
        assertThat("Fifteen-Love", is(tennisGame.computeGameScore()));
    }
    
    @Test
    public void gameScoreShouldBeLoveThirtyWhenSecondPlayerWonFirstAndScondService() {
    	
    	updatePlayerScore(0,2);
        
        assertThat("Love-Thirty", is(tennisGame.computeGameScore()));
    }
    
    @Test
    public void gameScoreShouldBeFifteenAllWhenBothPlayersWontheOneServiceRespectively() {
    	
    	updatePlayerScore(1,1);
        
        assertThat("Fifteen-All", is(tennisGame.computeGameScore()));
    }
    
    @Test
    public void gameScoreShouldBeFifteenFortyWhenFirstPlayerWonOneServiceAndSecondPlayerWonTheeServices() {
    	
        updatePlayerScore(1,3);
        
        assertThat("Fifteen-Forty", is(tennisGame.computeGameScore()));
    }
    
	@Test
	@Parameters({"0, 1, Love-Fifteen", "2, 1, Thirty-Fifteen", "2, 3, Thirty-Forty",
			"2, 2, Thirty-All" })
	public void gameScoreShouldBeExpectedResultWhichIsMentionedInPreDefinedParametersWithPlayersPointsOfTheGame(
			int firstPlayerPoints, int secondPlayerPoints, String expectedGameResult) {
		
		updatePlayerScore(firstPlayerPoints, secondPlayerPoints);

		assertThat(expectedGameResult, is(tennisGame.computeGameScore()));
	}

    @Test
    public void gameShouldBeWonByFirstPlayerWhenFirstPlayerScoreMinimumFourPointsAndSecondPlayerScoreShouldBeTwoPointsDifferenceWithFirstPlayer() {
    	
    	updatePlayerScore(4,1);
    	
    	assertThat("FirstPlayer won the game", is(tennisGame.computeGameScore()));
    }
    
    @Test
    public void gameShouldBeWonBySecondPlayerWhenSecondPlayerScoreMinimumFourPointsAndFirstPlayerScoreShouldBeTwoPointsDifferenceWithSecondPlayer() {
    	
    	updatePlayerScore(2,4);
    	
    	assertThat("SecondPlayer won the game", is(tennisGame.computeGameScore()));
    }
    
	@Test
    public void gameScoreShouldBeDeuceWhenBothPlayersHavingSameScoreAsMinimumTheePoints() {
		
		updatePlayerScore(3,3);
		
		assertThat("Deuce", is(tennisGame.computeGameScore()));
    }
	
	@Test
	@Parameters({ "4,4", "5,5", "7,7", "10,10", "12,12", "14,14" })
	public void gameScoreShouldBeDeuceWhenBothPlayersHavingSameScoreForMoreScenarios(int firstPlayerPoints,
			int secondPlayerPoints) {
		
		updatePlayerScore(firstPlayerPoints, secondPlayerPoints);

		assertThat("Deuce", is(tennisGame.computeGameScore()));
	}
	
	@Test
	public void gameScoreShouldBeFirstPlayerHasAdvantageWhenFirstPlayerScoredFourPointsAndSecondPlayerScoredThreePoints() {
		updatePlayerScore(4,3);

		assertThat("FirstPlayer has advantage", is(tennisGame.computeGameScore()));
	}
	
	@Test
	@Parameters({ "5,4, FirstPlayer has advantage", "6,5, FirstPlayer has advantage",
			"6, 7, SecondPlayer has advantage", "9, 8, FirstPlayer has advantage", "10, 9, FirstPlayer has advantage",
			"12, 13, SecondPlayer has advantage", "14, 13, FirstPlayer has advantage" })
	public void gameScoreShouldBePlayersHasAdvantageWhenBothPlayersScoreHasOnePointDifference(int firstPlayerScore,
			int secondPlayerScore, String expectedGameResult) {
		
		updatePlayerScore(firstPlayerScore, secondPlayerScore);

		assertThat(expectedGameResult, is(tennisGame.computeGameScore()));
	}
	
    @Test
    public void bothPlayerNamesShouldBeEqualWithInitializedGameInstantParameter() {
    	
        tennisGame = new TennisGame("AAA", "BBB");
        
        assertThat("AAA", is(tennisGame.getFirstPlayer().getName()));
        assertThat("BBB", is(tennisGame.getSecondPlayer().getName()));
    }
    
    @Test
    public void firstPlayerScoreShouldBeAutoIncrementByOneWhenPassingPlayerIndicatorAsA() throws InvalidUserInputException {
    	
    	updatePlayerScore(1,1);
    	
        int expectedPlayerOnePoint = tennisGame.getFirstPlayer().getScoredPoint() + 1;
        tennisGame.addServicePointToWinner("A");
        
        assertThat(expectedPlayerOnePoint, is(tennisGame.getFirstPlayer().getScoredPoint()));
    }
    
    @Test
    public void secondPlayerScoreShouldBeAutoIncrementByOneWhenPassingPlayerIndicatorAsB() throws InvalidUserInputException {
    	
    	updatePlayerScore(1,2);
    	
        int expectedPlayerTwoPoint = tennisGame.getSecondPlayer().getScoredPoint() + 1;
        tennisGame.addServicePointToWinner("B");
        
        assertThat(expectedPlayerTwoPoint, is(tennisGame.getSecondPlayer().getScoredPoint()));
    }
    
	@Test(expected = InvalidUserInputException.class)
	public void shouldThrowInvalidUserInputExceptionWhenInvalidIndicatorPassingForPlayer() throws InvalidUserInputException{
    	
		updatePlayerScore(1,2);
    	
        tennisGame.addServicePointToWinner("X");
	}
	
	private void updatePlayerScore(int firsPlayerWinningCounts, int secondPlayerWinningCounts) {
		
		for (int winningCount = 0; winningCount < firsPlayerWinningCounts; winningCount++) {
			tennisGame.getFirstPlayer().incrementPlayerScore();
		}
		for (int winningCount = 0; winningCount < secondPlayerWinningCounts; winningCount++) {
			tennisGame.getSecondPlayer().incrementPlayerScore();
		}
	}
	
}