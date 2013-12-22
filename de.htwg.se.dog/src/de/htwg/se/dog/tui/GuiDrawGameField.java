package de.htwg.se.dog.tui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import javax.swing.JPanel;
import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.GameFieldInterface;

/** @see http://stackoverflow.com/questions/2508704 */
public class GuiDrawGameField extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int RADIUS = 380;
	private GameTableInterface controller;

	public GuiDrawGameField(GameTableInterface controller) {
		this.controller = controller;
		this.setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		GameFieldInterface game = controller.getGameField();
		FieldInterface[] array = game.getGameArray();
		int house = game.getHouseCount() * game.getPlayerCount();
		int size = game.getFieldSize();
		double a = getWidth() / 2;
		double b = getHeight() / 2;
		double r2 = 2 * Math.PI * RADIUS / size;
		double t = 0;
		int counter = 1;
		int counterhouse = 1;
		double dif = -(r2*1.2);
		double x, y;
		
		/*g2d.setColor(Color.MAGENTA);
        g2d.fill(new Arc2D.Double(a - RADIUS-NORM, b - RADIUS-NORM, 2*RADIUS, 2*RADIUS,0,360,Arc2D.OPEN));*/

		for (int i = 0; i < size; i++) {
			
			if (array[i].isHouse()) {
				
				g2d.setColor(Color.RED);
				x = a + (RADIUS+dif) * Math.cos(t);
				y = b + (RADIUS+dif) * Math.sin(t);
				g2d.fill(new Arc2D.Double(x - r2, y - r2, r2, r2,0,360,Arc2D.OPEN));
				dif-=(r2*1.2);
				
				if(counterhouse == game.getHouseCount()) {
					counter++;
				}
				counterhouse++;
			} else {
				
				dif = -(r2*1.2);
				counterhouse=0;
				g2d.setColor(Color.GRAY);
				t = 2 * Math.PI * (counter+0.075) / (size-house);
				x = a + RADIUS * Math.cos(t);
				y = b + RADIUS * Math.sin(t);
				g2d.fill(new Arc2D.Double(x - r2, y - r2, r2, r2,0,360,Arc2D.OPEN));
				counter++;
			}
		}
	}

}
