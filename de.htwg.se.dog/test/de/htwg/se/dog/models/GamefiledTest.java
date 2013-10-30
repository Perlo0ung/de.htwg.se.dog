package de.htwg.se.dog.models;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GamefiledTest {
	GameField f;
	int playerCount = 3;
	int fieldsTillHouse = 2;
	int houseCount = 3;
	int owner = 1;
	int noowner = 0;
	
	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Before
	public void setUp() {
		f = new GameField(fieldsTillHouse, playerCount, houseCount);
	}
	
	@Test
	public void testGenerateGameField() {
		assertEquals(noowner, f.getOwner(fieldsTillHouse+houseCount));
		assertEquals(owner, f.getOwner(fieldsTillHouse));
	}
	@Test
	public void testGetGameField(){
		
	}
}
