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
    	
        tennisGame.incrementFirstPlayerScore();
        
        assertThat("Fifteen-Love", is(tennisGame.computeGameScore()));
    }
	
}