package de.htwg.se.dog.view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.CardInterface;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GuiDrawCardHand extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ABSTAND = 25;
	private static final int IMGHEIGHT = 97;
	private static final int IMGWIDTH = 73;
	private GameTableInterface controller;
	private List<Entry<CardInterface, Rectangle>> cards;
	private CardInterface hCards = null;
	
	public GuiDrawCardHand(GameTableInterface controller) {
		this.controller = controller;
		this.addMouseListener(this);
		cards = new ArrayList<Entry<CardInterface, Rectangle>>(6);

	}

	@Override
	protected void paintComponent(Graphics g) {
		cards.clear();
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		int cardNum = controller.getCurrentPlayer().getCardList().size();

		RenderingHints renderHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(renderHints);

		int x = 0;
		int count = 1;
		Rectangle rect;
		for (CardInterface c : controller.getCurrentPlayer().getCardList()) {
			if (count == cardNum) {
				rect = new Rectangle(x, ABSTAND, IMGWIDTH, IMGHEIGHT);
			} else {
				rect = new Rectangle(x, ABSTAND, ABSTAND, IMGHEIGHT);
			}
			try {
				BufferedImage img = ImageIO.read(new File(this.getClass()
						.getResource(String.format("/%d.gif", c.getValue()))
						.getPath()));
				if (c == hCards) {
					g2d.drawImage(img, x, 0, null);
				} else {
					g2d.drawImage(img, x, ABSTAND, null);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cards.add(new MyEntry<CardInterface, Rectangle>(c,rect));
			x += ABSTAND;
			count++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == 1) {
			for (Entry<CardInterface, Rectangle> a : cards) {
				if (a.getValue().contains(arg0.getX(), arg0.getY())) {

					if(hCards == a.getKey()) {
						hCards = null;
					} else {
						hCards = a.getKey();
					}
					repaint();			
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


	final class MyEntry<K, V> implements Map.Entry<K, V> {
	    private final K key;
	    private V value;

	    public MyEntry(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public K getKey() {
	        return key;
	    }

	    @Override
	    public V getValue() {
	        return value;
	    }

	    @Override
	    public V setValue(V value) {
	        V old = this.value;
	        this.value = value;
	        return old;
	    }
	}
}