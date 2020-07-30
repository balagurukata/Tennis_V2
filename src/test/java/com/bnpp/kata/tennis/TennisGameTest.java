package com.bnpp.kata.tennis;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class TennisGameTest {
	
	TennisGame tennisGame;
	
	@Before
	public void initialize() {
		tennisGame = new TennisGame();
	}

	@Test
	public void firstPlayerScoredShouldBeZeroBeforeGameBegins() {
		
		assertThat(0, is(tennisGame.getFirstPlayerScoredPoint()));
	}
	
	@Test
	public void secondPlayerScoredShouldBeZeroBeforeGameBegins() {

		assertThat(0, is(tennisGame.getSecondPlayerScoredPoint()));
	}
	
	@Test
	public void firstPlayerNameShouldBeInitializedAsFirstPlayerBeforeGameBegins() {

		assertThat("FirstPlayer", is(tennisGame.getFirstPlayerName()));
	}
	
	@Test
	public void secondPlayerNameShouldBeInitializedAsSecondPlayerBeforeGameBegins() {

		assertThat("SecondPlayer", is(tennisGame.getSecondPlayerName()));
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

	private void updatePlayerScore(int firsPlayerWinningCounts, int secondPlayerWinningCounts) {
		
		for (int winningCount = 0; winningCount < firsPlayerWinningCounts; winningCount++) {
			tennisGame.incrementFirstPlayerScore();
		}
		for (int winningCount = 0; winningCount < secondPlayerWinningCounts; winningCount++) {
			tennisGame.incrementSecondPlayerScore();
		}
	}
	
}