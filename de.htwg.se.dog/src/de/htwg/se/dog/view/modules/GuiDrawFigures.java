package de.htwg.se.dog.view.modules;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import javax.swing.JPanel;
/**
 * Draws a circle with the number of figures a player has on his hands
 * @author Michael
 *
 */
public class GuiDrawFigures extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int CIRCLE = 360;
	private static final int FIVE= 5;
	private static final int TWENTY = 20;
	private static final int FONTX = 8;
	private static final int FONTY = 32;
	private static final int FOURTY = 40;
	private ColorMap colorM = new ColorMap();
	private Color col = Color.BLACK;
	private String fig = "";
	/**
	 * initializes the panel and sets the controller this panel is working with
	 * @param controller the gamecontroller
	 */
	public GuiDrawFigures() {
		this.setBackground(Color.WHITE);
	}

	/**
	 * Draws the point with the number of figures left on a players hand
	 */
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

		g2d.setColor(col);
		g2d.fill(new Arc2D.Double(1, FIVE, FOURTY, FOURTY, 0, CIRCLE,
				Arc2D.OPEN));
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Tahoma", Font.BOLD, TWENTY));
		g2d.drawString(fig, FONTX, FONTY);
	}
	
	public void changePlayer(int playernum,int fignum) {
		col = colorM.getColor(playernum);
		fig = String.format("%2s",fignum);
	}
}
