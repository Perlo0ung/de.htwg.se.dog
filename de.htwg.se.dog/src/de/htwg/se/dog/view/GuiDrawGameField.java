package de.htwg.se.dog.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.GameFieldInterface;

public class GuiDrawGameField extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private static final int RADIUS = 380;
	private static final int HUNDRED = 100;
	private static final int CIRCLE = 360;
	private GameTableInterface controller;
	private static Map<Integer, Arc2D> gMap;
	private static ColorMap col = new ColorMap();

	public GuiDrawGameField(GameTableInterface controller) {
		this.controller = controller;
		this.setBackground(Color.WHITE);
		gMap = new HashMap<Integer, Arc2D>();
		this.addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		gMap.clear();
		
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		
		
		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(renderHints);
		
		GameFieldInterface game = controller.getGameField();
		FieldInterface[] array = game.getGameArray();
		int house = game.getHouseCount() * game.getPlayerCount();
		int size = game.getFieldSize();
		int start = game.getFieldsTillHouse() + game.getHouseCount();
		double a = getWidth() / 2;
		double b = getHeight() / 2;
		double r2 = 2 * Math.PI * RADIUS / size;
		double t = 0;
		int counter = 1;
		int counterhouse = 1;
		double dif = -(r2 * 1.2);
		double x, y;
		Arc2D gArc;
		g2d.setStroke(new BasicStroke(RADIUS / HUNDRED));
		g2d.setFont(new Font("Courier New", Font.BOLD, (int) Math
				.round(r2 * 0.5)));

		for (int i = 0; i < size; i++) {
			if (array[i].isHouse()) {
				g2d.setColor(col.getColor(array[i].getOwner()));
				x = a + (RADIUS + dif) * Math.cos(t);
				y = b + (RADIUS + dif) * Math.sin(t);
				gArc = new Arc2D.Double(x - r2, y - r2, r2, r2, 0, CIRCLE,
						Arc2D.OPEN);
				g2d.draw(gArc);
				g2d.setColor(Color.BLACK);
				g2d.drawString(String.valueOf(counterhouse + 1),
						Float.parseFloat(String.valueOf(x - r2 * 0.6)),
						Float.parseFloat(String.valueOf(y - r2 * 0.4)));
				dif -= (r2 * 1.2);

				if (counterhouse == game.getHouseCount()) {
					counter++;
				}
				counterhouse++;
			} else {

				dif = -(r2 * 1.2);
				counterhouse = 0;
				if (array[i].getFigureOwnerNr() != -1) {
					g2d.setColor(col.getColor(array[i].getFigureOwnerNr()));
				} else {
					g2d.setColor(Color.GRAY);
				}
				t = 2 * Math.PI * (counter + 0.075) / (size - house);
				x = a + RADIUS * Math.cos(t);
				y = b + RADIUS * Math.sin(t);
				gArc = new Arc2D.Double(x - r2, y - r2, r2, r2, 0, CIRCLE,
						Arc2D.OPEN);
				if (i % start == 0) {
					g2d.draw(gArc);
					g2d.setColor(Color.BLACK);
					g2d.drawString("S",
							Float.parseFloat(String.valueOf(x - r2 * 0.64)),
							Float.parseFloat(String.valueOf(y - r2 * 0.4)));
				} else {
					g2d.fill(gArc);
				}

				counter++;
			}

			gMap.put(i, gArc);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == 1) {
			for (Entry<Integer, Arc2D> a : gMap.entrySet()) {
				if (a.getValue().contains(arg0.getX(), arg0.getY())) {
					JOptionPane.showMessageDialog(null,
							"Spielfeld nr: " + a.getKey() + " gedrückt");
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
