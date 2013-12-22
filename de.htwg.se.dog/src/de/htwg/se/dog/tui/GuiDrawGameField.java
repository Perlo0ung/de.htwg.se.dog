package de.htwg.se.dog.tui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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


public class GuiDrawGameField extends JPanel implements MouseListener{

	private static final String[] COLORS = new String[]{
        "#000000", "#FFFF00", "#1CE6FF", "#FF34FF", "#FF4A46", "#008941", "#006FA6", "#A30059",
        "#FFDBE5", "#7A4900", "#0000A6", "#63FFAC", "#B79762", "#004D43", "#8FB0FF", "#997D87",
        "#5A0007", "#809693", "#FEFFE6", "#1B4400", "#4FC601", "#3B5DFF", "#4A3B53", "#FF2F80",
        "#61615A", "#BA0900", "#6B7900", "#00C2A0", "#FFAA92", "#FF90C9", "#B903AA", "#D16100",
        "#DDEFFF", "#000035", "#7B4F4B", "#A1C299", "#300018", "#0AA6D8", "#013349", "#00846F",
        "#372101", "#FFB500", "#C2FFED", "#A079BF", "#CC0744", "#C0B9B2", "#C2FF99", "#001E09",
        "#00489C", "#6F0062", "#0CBD66", "#EEC3FF", "#456D75", "#B77B68", "#7A87A1", "#788D66",
        "#885578", "#FAD09F", "#FF8A9A", "#D157A0", "#BEC459", "#456648", "#0086ED", "#886F4C",

        "#34362D", "#B4A8BD", "#00A6AA", "#452C2C", "#636375", "#A3C8C9", "#FF913F", "#938A81",
        "#575329", "#00FECF", "#B05B6F", "#8CD0FF", "#3B9700", "#04F757", "#C8A1A1", "#1E6E00",
        "#7900D7", "#A77500", "#6367A9", "#A05837", "#6B002C", "#772600", "#D790FF", "#9B9700",
        "#549E79", "#FFF69F", "#201625", "#72418F", "#BC23FF", "#99ADC0", "#3A2465", "#922329",
        "#5B4534", "#FDE8DC", "#404E55", "#0089A3", "#CB7E98", "#A4E804", "#324E72", "#6A3A4C",
        "#83AB58", "#001C1E", "#D1F7CE", "#004B28", "#C8D0F6", "#A3A489", "#806C66", "#222800",
        "#BF5650", "#E83000", "#66796D", "#DA007C", "#FF1A59", "#8ADBB4", "#1E0200", "#5B4E51",
        "#C895C5", "#320033", "#FF6832", "#66E1D3", "#CFCDAC", "#D0AC94", "#7ED379", "#012C58"};

	private static final long serialVersionUID = 1L;
	private static final int RADIUS = 380;
	private static final int HUNDRED = 100;
	private static final int CIRCLE = 360;
	private GameTableInterface controller;
	private static Map<Integer,Arc2D> gMap;
	
	public GuiDrawGameField(GameTableInterface controller) {
		this.controller = controller;
		this.setBackground(Color.WHITE);
		gMap = new HashMap<Integer, Arc2D>();
		this.addMouseListener(this);
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
		double dif = -(r2 * 1.2);
		double x, y;
		Arc2D gArc;

		for (int i = 0; i < size; i++) {

			if (array[i].isHouse()) {
				g2d.setColor(getColor(array[i].getOwner()));
				g2d.setStroke(new BasicStroke(RADIUS / HUNDRED));
				x = a + (RADIUS + dif) * Math.cos(t);
				y = b + (RADIUS + dif) * Math.sin(t);
				gArc = new Arc2D.Double(x - r2, y - r2, r2, r2, 0, CIRCLE,
						Arc2D.OPEN);
				g2d.draw(gArc);
				g2d.setColor(Color.BLACK);
				g2d.setFont(new Font("Courier New", Font.BOLD, (int) Math.round(r2/2)));
				g2d.drawString(String.valueOf(counterhouse+1), Float.parseFloat(String.valueOf(x-r2*0.6)),Float.parseFloat(String.valueOf(y-r2*0.4)));
				dif -= (r2 * 1.2);

				if (counterhouse == game.getHouseCount()) {
					counter++;
				}
				counterhouse++;
			} else {

				dif = -(r2 * 1.2);
				counterhouse = 0;
				if (array[i].getFigureOwnerNr() != -1) {
					g2d.setColor(getColor(array[i].getOwner()));
				} else {
					g2d.setColor(Color.GRAY);
				}
				t = 2 * Math.PI * (counter + 0.075) / (size - house);
				x = a + RADIUS * Math.cos(t);
				y = b + RADIUS * Math.sin(t);
				gArc = new Arc2D.Double(x - r2, y - r2, r2, r2, 0, CIRCLE,
						Arc2D.OPEN);
				g2d.fill(gArc);
				counter++;
			}
			gMap.put(i, gArc);
		}
	}
	
	public Color getColor(int code) {
		try {
			return Color.decode(COLORS[code]); 
		} catch (IndexOutOfBoundsException ex) {
			return Color.BLACK;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		   if (arg0.getButton() == 1) {
			   for (Entry<Integer,Arc2D> a: gMap.entrySet()) {
				   if (a.getValue().contains(arg0.getX(), arg0.getY())) {
					   JOptionPane.showMessageDialog(null,"Spielfeld nr: " +a.getKey()+ "gedrückt");
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
