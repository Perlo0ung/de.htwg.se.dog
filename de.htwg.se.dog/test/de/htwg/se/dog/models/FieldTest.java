package de.htwg.se.dog.models;

import static org.junit.Assert.*;
import de.htwg.se.dog.models.Field;
import de.htwg.se.dog.models.Figure;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {	
	private final int OWNER = 1;
	private final int FIGNUM = 1;
	public Player owner;
	public Field feld1;
	public Field feld2;	
	Figure f = new Figure(OWNER,FIGNUM);
	
	@Before
	public void setUp() {
		feld1 = new Field(NOOWNER);
		feld2 = new Field(OWNER);
		feld2.setFigure(f);
		feld2.setBlocked(true);
		feld2.getBlocked();
	}
	@Test
	public void testSetFigure() {
		assertEquals(f, feld2.getFigure());
	}
	@Test
	public void testGetFigure() {
		assertEquals(f, feld2.getFigure());
	}
	@Test
	public void testGetOwner(){
		assertEquals(OWNER, feld2.getOwner());
	}
	@Test
	public void setBlocked(){
		assertEquals(feld2.getBlocked(),true);
	}
	@Test
	public void testIsHouse(){
		assertEquals(true, feld2.isHouse());
	}

}
