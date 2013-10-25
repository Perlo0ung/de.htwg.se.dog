package de.htwg.se.dog.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FeldTest {	
	private final int NOOWNER = 0;
	private final int OWNER = 1;
	private final int FIGNUM = 1;
	public Field feld1;
	public Field feld2;	
	Figure f = new Figure(OWNER,FIGNUM);
	
	@Before
	public void setUp() {
		feld1 = new Field(NOOWNER);
		feld2 = new Field(OWNER);
		feld2.setFigure(f);
		feld2.setBlocked(true);
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
		assertEquals(true, feld2.getBlocked());
	}
	@Test
	public void testIsHouse(){
		assertEquals(true, feld2.isHouse());
	}

}
