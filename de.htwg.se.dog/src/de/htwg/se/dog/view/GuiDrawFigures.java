package de.htwg.se.dog.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.htwg.se.dog.controller.GameTableInterface;

public class GuiDrawFigures extends JPanel {

	private static final long serialVersionUID = 1L;
	private GameTableInterface controller;
	private static final int CIRCLE = 360;
	private static final int TEN = 10;
	private static final int FOURTY = 40;
	private static final int RADIUS = 22;
	private static ColorMap col = new ColorMap();
	private static Arc2D[] arcs = new Arc2D[] {
			new Arc2D.Double(FOURTY, FOURTY, RADIUS, RADIUS, 0, CIRCLE,
					Arc2D.OPEN),
			new Arc2D.Double(TEN, FOURTY, RADIUS, RADIUS, 0, CIRCLE, Arc2D.OPEN),
			new Arc2D.Double(FOURTY, TEN, RADIUS, RADIUS, 0, CIRCLE, Arc2D.OPEN),
			new Arc2D.Double(TEN, TEN, RADIUS, RADIUS, 0, CIRCLE, Arc2D.OPEN) };

	public GuiDrawFigures(GameTableInterface controller) {
		this.controller = controller;
		this.setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		this.setBorder(new LineBorder(col.getColor(controller.getCurrentPlayer()
				.getPlayerID()),3));
		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(renderHints);
		int fignum = controller.getCurrentPlayer().getFigureList().size();

		for (int i = 0; i < fignum; i++) {

			g2d.setColor(col.getColor(controller.getCurrentPlayer()
					.getPlayerID()));
			g2d.fill(arcs[i]);
		}

	}
}
