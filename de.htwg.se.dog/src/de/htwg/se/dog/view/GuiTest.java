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
import javax.swing.border.LineBorder;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.CardInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.util.IOEvent;
import de.htwg.se.dog.util.IObserver;
import de.htwg.se.dog.view.modules.ColorMap;
import de.htwg.se.dog.view.modules.GuiDrawFigures;
import de.htwg.se.dog.view.modules.GuiDrawGameField;
import de.htwg.se.dog.view.modules.OverlapLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
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

import javax.swing.JScrollPane;

public class GuiTest extends JFrame implements IObserver {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GameTableInterface controller;
	private ColorMap col = new ColorMap();
	private JFormattedTextField tFieldCurrentPlayer;
	private JComboBox<Object> cbCards;
	private JLabel[] cards;
	private JPanel cardHand;
	private OverlapLayout layout;
	private Component up;

	/**
	 * Create the frame.
	 */
	public GuiTest(GameTableInterface controller) {
		controller.addObserver(this);
		setResizable(false);
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
		setBounds(100, 100, 1280, 800);

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel gameField = new GuiDrawGameField(controller);
		//JPanel gameField = new JPanel();
		gameField.setBounds(0, 0, 1274, 739);
		contentPane.add(gameField);
		gameField.setBackground(Color.WHITE);
		gameField.setLayout(null);

		cbCards = new JComboBox<Object>();
		cbCards.setBounds(351, 680, 138, 22);
		gameField.add(cbCards);
		cbCards.setBackground(Color.WHITE);

		JLabel lblPlayCard = new JLabel("Cardlist");
		lblPlayCard.setBounds(351, 665, 56, 16);
		gameField.add(lblPlayCard);
		lblPlayCard.setFont(new Font("Tahoma", Font.BOLD, 13));

		JButton btnPlayIt = new JButton("play");
		btnPlayIt.setBounds(350, 704, 97, 22);
		gameField.add(btnPlayIt);
		btnPlayIt.setBackground(Color.WHITE);

		tFieldCurrentPlayer = new JFormattedTextField();
		tFieldCurrentPlayer.setBounds(12, 633, 97, 22);
		gameField.add(tFieldCurrentPlayer);
		tFieldCurrentPlayer.setFont(tFieldCurrentPlayer.getFont().deriveFont(
				tFieldCurrentPlayer.getFont().getStyle() | Font.BOLD,
				tFieldCurrentPlayer.getFont().getSize() + 4f));
		tFieldCurrentPlayer.setBorder(new LineBorder(tFieldCurrentPlayer
				.getBackground()));
		tFieldCurrentPlayer.setBackground(Color.WHITE);
		tFieldCurrentPlayer.setColumns(10);

		JPanel figures = new GuiDrawFigures(controller);
		//JPanel figures = new JPanel();
		figures.setBounds(18, 650, 72, 60);
		gameField.add(figures);
		figures.setBackground(Color.WHITE);

		JLabel lbCurrentPlayer = new JLabel("CurrentPlayer");
		lbCurrentPlayer.setBounds(12, 618, 101, 16);
		gameField.add(lbCurrentPlayer);
		lbCurrentPlayer.setFont(new Font("Tahoma", Font.BOLD, 13));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(120, 606, 213, 133);
		gameField.add(scrollPane);

		layout = new OverlapLayout(new Point(25, 0), true);
		layout.setPopupInsets(new Insets(20, 0, 0, 0));
		cardHand = new JPanel(layout);
		scrollPane.setViewportView(cardHand);
		cardHand.setBackground(Color.WHITE);
		cards = new JLabel[6];
		for (int i = 0; i < cards.length; i++) {
			cards[i] = new JLabel();
			cards[i].setBounds(0, 0, 75, 95);
			cards[i].setPreferredSize(new Dimension(80, 110));
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
		PlayerInterface current = controller.getCurrentPlayer();
		tFieldCurrentPlayer.setForeground(col.getColor(current.getPlayerID()));
		tFieldCurrentPlayer.setText(current.toString());
		cbCards.setModel(new DefaultComboBoxModel<Object>(current.getCardList()
				.toArray()));
		int count = 0;
		for (CardInterface c : current.getCardList()) {
			cards[count++].setIcon(new ImageIcon(getClass().getResource(
					String.format("/%d.gif", c.getValue()))));
		}
		this.validate();
		this.repaint();
	}

	private void cardOut(Component c) {
		Boolean constraint = layout.getConstraints(c);
		/*get item at pos i from cardlist
		System.out.println(controller.getCurrentPlayer().getCardList().get(Integer.parseInt(c.getName())));
		 */
		if (up != null) { 
			layout.addLayoutComponent(up, OverlapLayout.POP_DOWN);
		}
		if (constraint == null || constraint == OverlapLayout.POP_DOWN) {
			layout.addLayoutComponent(c, OverlapLayout.POP_UP);
		} else {
			layout.addLayoutComponent(c, OverlapLayout.POP_DOWN);
		}
		c.getParent().invalidate();
		c.getParent().validate();
		up = c;
	}
}
