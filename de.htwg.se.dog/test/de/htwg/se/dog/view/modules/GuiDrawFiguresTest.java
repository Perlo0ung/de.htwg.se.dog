package de.htwg.se.dog.view.modules;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.dog.view.modules.GuiDrawFigures;

public class GuiDrawFiguresTest {
	private static int PLAYERNUM = 4;
	private static int SIZE = 50;
	JFrame panel;
	GuiDrawFigures figures;
	@Before
	public void setUp() {
		figures = new GuiDrawFigures();
		panel = new JFrame();
		panel.add(figures);
		panel.repaint();
		panel.setVisible(true);
		panel.setSize(SIZE, SIZE);
		panel.setVisible(true);
	}
	@Test
	public void testAllowSecond() {
		figures.changePlayer(PLAYERNUM, PLAYERNUM);
	}
}
