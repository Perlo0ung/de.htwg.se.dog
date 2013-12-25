package de.htwg.se.dog.view.modules;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.FieldInterface;
import de.htwg.se.dog.models.GameFieldInterface;

/**
 * Draws the gamefield
 * 
 * @author Michael
 * 
 */
public class GuiDrawGameField extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private static int RADIUS;
	private static final int HUNDRED = 100;
	private static final int CIRCLE = 360;
	private static final int MAXRADIUS = 90;
	private static final int NORM = 35;
	private GameTableInterface controller;
	// Map with all Gamefields
	private static SortedMap<Integer, Arc2D.Double> gMap;
	// Map with highlighted GameFields
	private static SortedMap<Integer, Arc2D.Double> gHigh;
	private static ColorMap col = new ColorMap();
	private static GameFieldInterface game;
	private static FieldInterface[] array;

	/**
	 * initializes the panel and sets the controller this panel is working with
	 * 
	 * @param controller
	 *            the gamecontroller
	 */
	public GuiDrawGameField(GameTableInterface controller) {
		this.controller = controller;
		this.setBackground(Color.WHITE);
		gMap = new TreeMap<Integer, Arc2D.Double>();
		gHigh = new TreeMap<Integer, Arc2D.Double>();
		this.addMouseListener(this);
		game = controller.getGameField();
		array = game.getGameArray();
	}

	/**
	 * Draws the complete gamefield as a circle
	 */
	@Override
	protected void paintComponent(Graphics g) {
		RADIUS = (int) ((this.getHeight() / 2.3) - NORM);
		gMap.clear();
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);

		// Maximum quality
		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(renderHints);

		int house = game.getHouseCount() * game.getPlayerCount();
		int size = game.getFieldSize();
		int start = game.getFieldsTillHouse() + game.getHouseCount();
		int a = getWidth() / 2;
		int b = getHeight() / 2;

		// draw comic dog in the middle of this panel
		try {
			BufferedImage img = ImageIO.read(new File(this.getClass()
					.getResource("/dog.jpg").getPath()));
			g2d.drawImage(img, (getWidth() - img.getWidth()) / 2,
					(getHeight() - img.getHeight()) / 2, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// PREVENT too big fields e.g when playing with 1:1 fields
		double r2 = 2 * Math.PI * RADIUS / size;
		if (r2 > MAXRADIUS) {
			r2 = MAXRADIUS;
		}
		//next point on outer circle
		double t = 0;
		//counter for normalfields
		int counter = 1;
		//counter for housefields
		int counterhouse = 1;
		//-radius where the house field will be drawn
		double dif = -(r2 * 1.2);
		//x,y coordinates for points
		double x, y;
		Arc2D.Double gArc;
		g2d.setStroke(new BasicStroke(RADIUS / HUNDRED));
		g2d.setFont(new Font("Tahoma", Font.BOLD, (int) Math.round(r2 * 0.5)));
		/**
		 * house fields are treated different cause they need to be inside the
		 * circle in one line
		 * 
		 * @t: defines at which radius the point will be drawn next point will
		 * only be drawn when counter is inkremented so we make sure that
		 * counter gets only inkremented after all houses have been drawn or
		 * after each normal field
		 * 
		 * @dif: defines where the next point is inside the circle
		 * 
		 * @r2: the radius of a 1 gamefield
		 */
		for (int i = 0; i < size; i++) {

			if (array[i].isHouse()) {

				x = a + (RADIUS + dif) * -Math.cos(t);
				y = b + (RADIUS + dif) * -Math.sin(t);
				gArc = new Arc2D.Double(x - r2, y - r2, r2, r2, 0, CIRCLE,
						Arc2D.OPEN);
				dif -= (r2 * 1.2);
				g2d.setColor(col.getColor(array[i].getOwner()));
				if (array[i].getFigureOwnerNr() != -1) {
					g2d.fill(gArc);
				} else {
					g2d.draw(gArc);
				}
				drawString(g2d, String.valueOf(counterhouse + 1), r2, x, y);

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
				x = a + RADIUS * -Math.cos(t);
				y = b + RADIUS * -Math.sin(t);
				gArc = new Arc2D.Double(x - r2, y - r2, r2, r2, 0, CIRCLE,
						Arc2D.OPEN);
				if (i % start == 0 && !array[i].isBlocked()) {

					if (array[i].getFigure() == null) {
						g2d.draw(gArc);
					} else {
						g2d.fill(gArc);
					}
					drawString(g2d, "S", r2, x, y);
				} else {
					g2d.fill(gArc);
				}
				if (array[i].isBlocked()) {
					g2d.setColor(Color.BLACK);
					drawString(g2d, "B", r2, x, y);
				}
				counter++;
			}
			gMap.put(i, gArc);

		}
		/**
		 * override the fields with the number and playercolor when they have
		 * been clicked 
		 */
		for (Entry<Integer, Arc2D.Double> arc : gHigh.entrySet()) {
			g2d.setColor(col.getColor(controller.getCurrentPlayer()
					.getPlayerID()));
			g2d.fill(arc.getValue());
			drawString(g2d, String.valueOf(arc.getKey()), r2, arc.getValue().x
					+ r2, arc.getValue().y + r2);
		}
	}

	/**
	 * Draw a String on this Panel
	 * 
	 * @param g2d
	 *            the graphics interface
	 * @param s
	 *            the string that shoule be drawn
	 * @param r2
	 *            the radius of the circle in which the String gets places
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 */
	private void drawString(Graphics2D g2d, String s, double r2, double x,
			double y) {
		g2d.setColor(Color.BLACK);
		g2d.drawString(String.format("%2s", s),
				Float.parseFloat(String.valueOf(x - r2 * 0.78)),
				Float.parseFloat(String.valueOf(y - r2 * 0.35)));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	/**
	 * Highlight a gamefield when we klick on it
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getButton() == 1) {
			for (Entry<Integer, Arc2D.Double> a : gMap.entrySet()) {
				if (a.getValue().contains(arg0.getX(), arg0.getY())) {
					Arc2D.Double arc = a.getValue();
					Arc2D.Double newArc = new Arc2D.Double(arc.x, arc.y,
							arc.width, arc.height, 0, CIRCLE, Arc2D.OPEN);
					if (gHigh.containsKey(a.getKey())) {
						gHigh.remove(a.getKey());
						repaint();
					} else {
						if (gHigh.size() == 2) {
							gHigh.clear();
						}
						if (array[a.getKey()].isHouse()) {
							if (array[a.getKey()].getOwner() == controller
									.getCurrentPlayer().getPlayerID()) {
								gHigh.put(a.getKey(), newArc);
								repaint();
							}
						} else {
							gHigh.put(a.getKey(), newArc);
							repaint();
						}
						if (gHigh.size() == 2) {
							Object[] str = gHigh.keySet().toArray();
							JOptionPane.showMessageDialog(null, String.format(
									"von %s nach %s", str[0], str[1]));
						}
					}
					break;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
