package de.htwg.se.dog.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.models.Field;
import de.htwg.se.dog.models.GameField;
import de.htwg.se.dog.models.Player;

public class MovementTest {
	GameField gamefield;
	Player tp1, tp2;
	Field[] array;
	private final int PLAYERCOUNT = 2;
	private final int FIELDSTILLHOUSE = 2;
	private final int HOUSECOUNT = 2;
	private final int FIGCOUNT = 2;
	private final int PLAYERID1 = 1;
	private final int PLAYERID2 = 2;
	
	@Before
	public void setUp() throws Exception {
		gamefield = new GameField(FIELDSTILLHOUSE, PLAYERCOUNT, HOUSECOUNT);
		tp1 = new Player(PLAYERID1, FIGCOUNT);
		tp2 = new Player(PLAYERID2, FIGCOUNT);
		array = gamefield.getGamefield();
		assertFalse(condition);
	}

	@Test
	public void testMoveFigure() {

	}

	@Test
	public void testvalidMove() {
		array[1].putFigure(tp1.removeFigure());
		array[1].setBlocked(true);
		array[1].putFigure(tp1.removeFigure());
		
		assert
	}
	@Test
	public void testKickPlayer() {

	}
	
	@Test
	public void testGetTargetfield() {

	}
}
