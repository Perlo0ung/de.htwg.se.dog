package de.htwg.se.dog.view.modules;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.controller.impl.GameTable;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.view.modules.GuiDrawGameField;

import org.junit.Before;
import org.junit.Test;

public class GuiDrawGameFieldTest {
	private static int PLAYERNUM = 4;
	private static int SIZE = 50;
	JFrame panel;
	GameTableInterface controller;
	GuiDrawGameField gamefield;

	@Before
	public void setUp() {
		controller = new GameTable(PLAYERNUM);
		gamefield = new GuiDrawGameField(controller);
		FieldInterface[] array = controller.getGameField().getGameArray();
		array[0].setBlocked(true);
		panel = new JFrame();
		panel.add(gamefield);
		panel.repaint();
		panel.setVisible(true);
		panel.setSize(SIZE, SIZE);
		panel.setVisible(true);
	}

	@Test
	public void testGuiDrawGameField() {
		BufferedImage bi = new BufferedImage(SIZE, SIZE,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		panel.paintComponents(g2);
		gamefield.allowSecond(true);
		gamefield.clearField();
	}
}
