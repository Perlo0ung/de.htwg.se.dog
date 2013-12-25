package de.htwg.se.dog.view.modules;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import de.htwg.se.dog.controller.GameTableInterface;

public class GuiDrawFigures extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameTableInterface controller;
	private static final int CIRCLE = 360;
	private static final int FIFTEEN = 15;
	private static final int TWENTY = 20;
	private static final int FONTX = 22;
	private static final int FONTY = 41;
	private static final int FOURTY = 40;
	private static ColorMap col = new ColorMap();

	public GuiDrawFigures(GameTableInterface controller) {
		this.controller = controller;
		this.setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(renderHints);
		int fignum = controller.getCurrentPlayer().getFigureList().size();
		g2d.setColor(col.getColor(controller.getCurrentPlayer().getPlayerID()));
		g2d.fill(new Arc2D.Double(FIFTEEN, FIFTEEN, FOURTY, FOURTY, 0, CIRCLE,
				Arc2D.OPEN));
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Tahoma", Font.BOLD, TWENTY));
		g2d.drawString(String.format("%2d", fignum), FONTX, FONTY);
	}
}
