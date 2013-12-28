package de.htwg.se.dog.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.util.IOEvent;
import de.htwg.se.dog.util.IOMsgEvent;
import de.htwg.se.dog.util.IObserver;
import de.htwg.se.dog.view.modules.ColorMap;
import de.htwg.se.dog.view.modules.GuiDrawFigures;
import de.htwg.se.dog.view.modules.GuiDrawGameField;
import de.htwg.se.dog.view.modules.OverlapLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class GraphicalUserInterface extends JFrame implements IObserver {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GameTableInterface controller;
	private ColorMap col = new ColorMap();
	private JFormattedTextField tFieldRound;
	private JLabel tFieldCurrentPlayer;
	private JLabel[] cards;
	private OverlapLayout layout;
	private Component up;
	private GuiDrawFigures figures;
	private GuiDrawGameField gameField;
	private JTextArea tAreaStatus;
	// statics for findbugs
	private static final int CARD1 = 1;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int TWELVE = 12;
	private static final int THIRTEEEN = 13;
	private static final int CARD13 = 13;
	private static final int CARD14 = 14;
	private static final int FIFTEEN = 15;
	private static final int SIXTEEN = 16;
	private static final int TWENTY = 20;
	private static final int TWENTYTWO = 22;
	private static final int TWENTYFIVE = 25;
	private static final int THIRTY = 30;
	private static final int FOURTYFIVE = 45;
	private static final int SEVENTYFIVE = 75;
	private static final int EIGHTY = 80;
	private static final int NINETYFIVE = 95;
	private static final int NINETYSEVEN = 97;
	private static final int HUNDRET = 100;
	private static final int HUNDRETTEN = 110;
	private static final int HUNDRETTWENTY = 120;
	private static final int HUNDRETTHIRTY = 130;
	private static final int TWOHUNDRETTEN = 210;	
	private static final int THREEHUNDRET = 300;
	private static final int THTWENTYSIX = 326;
	private static final int SIXHUNDRET = 600;
	private static final int SIXHUNDRETEIGHTEEN = 618;
	private static final int TEXTFIELDY = 633;
	private static final int SIXHUNDRETFOURTY = 640;
	private static final int SIXHUNDRETSIXTY = 660;
	private static final int SEVENHUNDRET = 700;
	private static final int GAMEFIELDY = 750;
	private static final int WINDOWY = 800;
	private static final int NHFIFTY = 950;
	private static final int GAMEFIELDX = 1270;
	private static final int WINDOWX = 1280;

	
	
	
	
	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
	 */
	public GraphicalUserInterface(final GameTableInterface controller) {
		controller.addObserver(this);
		setResizable(false);
		this.setIconImage(new ImageIcon(this.getClass().getResource(
				"/dog_icon.png")).getImage());
		this.setVisible(true);
		this.controller = controller;
		this.setTitle("DogGame");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int quit = JOptionPane.showConfirmDialog(contentPane,
						"Wirklich beenden?", "Beenden",
						JOptionPane.YES_NO_OPTION);
				if (quit == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		setBounds(HUNDRET, HUNDRET, WINDOWX, WINDOWY);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mnExit = new JMenuItem("Dog beenden?");
		mnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(contentPane,
						"Wirklich beenden?", "Beenden",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		mnExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				Event.ALT_MASK));
		mnExit.setIcon(new ImageIcon(this.getClass().getResource("/off.png")
				.getPath()));
		mnGame.addSeparator();
		mnGame.add(mnExit);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(FIVE, FIVE, FIVE, FIVE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnGo = new JButton("GO!");
		contentPane.add(btnGo);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO

			}
		});
		btnGo.setBounds(THTWENTYSIX, SEVENHUNDRET, HUNDRET, TWENTYFIVE);

		gameField = new GuiDrawGameField(controller);
		// JPanel gameField = new JPanel();
		gameField.setBounds(0, 0, GAMEFIELDX, GAMEFIELDY);
		contentPane.add(gameField);
		gameField.setBackground(Color.WHITE);
		gameField.setLayout(null);

		tFieldCurrentPlayer = new JLabel();
		tFieldCurrentPlayer.setBorder(BorderFactory
				.createLineBorder(Color.white));
		tFieldCurrentPlayer.setBounds(TWELVE, TEXTFIELDY, NINETYSEVEN,
				GraphicalUserInterface.TWENTYTWO);
		gameField.add(tFieldCurrentPlayer);
		tFieldCurrentPlayer.setFont(new Font("Tahoma", Font.BOLD, FIFTEEN));
		tFieldCurrentPlayer.setBackground(Color.WHITE);

		JLabel lbCurrentPlayer = new JLabel("CurrentPlayer");
		lbCurrentPlayer.setBounds(TWELVE,
				GraphicalUserInterface.SIXHUNDRETEIGHTEEN, HUNDRET, SIXTEEN);
		gameField.add(lbCurrentPlayer);
		lbCurrentPlayer.setFont(new Font("Tahoma", Font.BOLD, THIRTEEEN));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(HUNDRETTWENTY, SIXHUNDRET, TWOHUNDRETTEN,
				HUNDRETTHIRTY);
		gameField.add(scrollPane);

		layout = new OverlapLayout(new Point(TWENTYFIVE, 0));
		layout.setPopupInsets(new Insets(TWENTY, 0, 0, 0));
		JPanel cardHand = new JPanel(layout);
		scrollPane.setViewportView(cardHand);
		cardHand.setBackground(Color.WHITE);
		figures = new GuiDrawFigures();
		figures.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int card = getValueForCardIcon();
				if ((card == CARD1 || card == CARD13 || card == CARD14)
						&& !controller.isPlayerStartfieldBlocked()) {
					int quit = JOptionPane.showConfirmDialog(contentPane,
							"Spielfigur aufs Spielfeld setzen?", "Rausgehen?",
							JOptionPane.YES_NO_OPTION);
					if (quit == JOptionPane.YES_OPTION) {
						controller.moveFigureToStart(card);
						controller.nextPlayer();
						controller.notifyObservers();
					}
				}
			}
		});
		// JPanel figures = new JPanel();
		figures.setBounds(THIRTY, SIXHUNDRETSIXTY, FOURTYFIVE, FOURTYFIVE);
		gameField.add(figures);
		figures.setBackground(Color.WHITE);
		figures.setLayout(null);

		JScrollPane panetAreaStatus = new JScrollPane();
		panetAreaStatus.setEnabled(false);
		panetAreaStatus.setBorder(BorderFactory.createLineBorder(Color.white));
		panetAreaStatus
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panetAreaStatus
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panetAreaStatus.setBounds(NHFIFTY, SIXHUNDRETFOURTY, THREEHUNDRET, EIGHTY);
		gameField.add(panetAreaStatus);

		tAreaStatus = new JTextArea();
		tAreaStatus.setLineWrap(true);
		tAreaStatus.setFont(new Font("Tahoma", Font.PLAIN, THIRTEEEN));
		tAreaStatus.setEditable(false);
		panetAreaStatus.setViewportView(tAreaStatus);

		tFieldRound = new JFormattedTextField();
		contentPane.add(tFieldRound);
		tFieldRound.setFont(new Font("Tahoma", Font.BOLD, THIRTEEEN));
		tFieldRound.setEditable(false);
		tFieldRound.setColumns(10);
		tFieldRound.setBorder(BorderFactory.createLineBorder(Color.white));
		tFieldRound.setBackground(Color.WHITE);
		tFieldRound.setBounds(326, 680, 90, 20);
		cards = new JLabel[SIX];
		for (int i = 0; i < cards.length; i++) {
			cards[i] = new JLabel();
			cards[i].setBounds(0, 0, SEVENTYFIVE, NINETYFIVE);
			cards[i].setPreferredSize(new Dimension(EIGHTY, HUNDRETTEN));
			cards[i].setName(String.valueOf(i));
			cards[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					cardOut(e.getComponent());
				}
			});
			cardHand.add(cards[i]);
		}
	}

	@Override
	public void update(IOEvent e) {
		if (e instanceof IOMsgEvent) {
			SimpleDateFormat dateF = new SimpleDateFormat("HH:mm");
			String date = dateF.format(new Date());
			tAreaStatus.append(String.format("[%s] %s\n", date,
					((IOMsgEvent) e).getMessage()));
			tAreaStatus.setCaretPosition(tAreaStatus.getDocument().getLength());
		} else {
			int playerID = controller.getCurrentPlayerID();
			tFieldCurrentPlayer.setForeground(col.getColor(playerID));
			tFieldCurrentPlayer.setText(controller.getPlayerString());
			tFieldRound.setText(String.format("Round: %d",
					controller.getRound()));
			// update the figures sysmbol
			figures.changePlayer(playerID, controller.getCurrentPlayer()
					.getFigureList().size());
			// clear gamefield highlighters
			gameField.clearField();
			// reset highlighted card
			if (up != null) {
				cardOut(up);
			}
			/* reset the labels */
			repaintCardLabels();

			this.validate();
			this.repaint();
		}
	}

	/**
	 * repaint the label icons and make unessesary icons invisible
	 */
	private void repaintCardLabels() {
		int cardListSize = controller.getCurrentPlayer().getCardList().size();
		for (int i = 0; i < cards.length; i++) {
			if (i < cardListSize) {
				int card = controller.getCurrentPlayer().getCardList().get(i)
						.getValue();
				cards[i].setIcon(new ImageIcon(getClass().getResource(
						String.format("/%d.gif", card))));
				continue;
			}
			cards[i].setVisible(false);
		}
	}

	/**
	 * resets the position of the cardlabel, so it becomes
	 * highlighted/dehighlighted
	 * 
	 * @param c
	 *            the component that will be highlighted
	 */
	private void cardOut(Component c) {
		Boolean constraint = layout.getConstraints(c);
		if (up != null) {
			layout.addLayoutComponent(up, OverlapLayout.POPDOWN);
			up = null;
		}
		if (constraint == null || constraint == OverlapLayout.POPDOWN) {
			layout.addLayoutComponent(c, OverlapLayout.POPUP);
			up = c;
		} else {
			layout.addLayoutComponent(c, OverlapLayout.POPDOWN);
			up = null;
		}
		c.getParent().invalidate();
		c.getParent().validate();

	}

	/**
	 * returns the index at which the card is in the players cardlist
	 * 
	 * @return index for card, or -1 if no card was highlighted
	 */
	private int getValueForCardIcon() {
		int ret = -1;
		if (up != null) {
			int index = Integer.parseInt(up.getName());
			int card = controller.getCurrentPlayer().getCardList().get(index)
					.getValue();
			ret = card;
		}
		return ret;
	}
}
