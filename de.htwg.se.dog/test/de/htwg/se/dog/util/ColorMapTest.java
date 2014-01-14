package de.htwg.se.dog.util;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.view.modules.ColorMap;

public class ColorMapTest {
	ColorMap col;
	private static final int COLORBLACK = 300;
	Color col1;
	
	@Before
	public void setUp() {
		col = new ColorMap();
		col1 = col.getColor(1);
	}

	@Test
	public void testColorMapGet() {
		assertEquals(col.getColor(COLORBLACK), Color.BLACK);
		assertEquals(col1,col.getColor(1));
		assertNotEquals(col1, Color.BLACK);
	}
}
