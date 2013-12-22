package de.htwg.se.dog.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.util.Event;
import de.htwg.se.dog.util.IObserver;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GuiTest extends JFrame implements IObserver {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GameTableInterface controller;
	private ColorMap col = new ColorMap();
	private JTextField tFieldCurrentPlayer;


	/**
	 * Create the frame.
	 */
	public GuiTest(GameTableInterface controller) {
		controller.addObserver(this);
		setResizable(false);
		this.setVisible(true);
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel gameField = new JPanel();
		//JPanel gameField = new GuiDrawGameField(controller);
		gameField.setBackground(Color.WHITE);
		gameField.setBounds(0, 0, 1480, 878);
		contentPane.add(gameField);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 876, 1394, 89);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tFieldCurrentPlayer = new JTextField();
		tFieldCurrentPlayer.setFont(tFieldCurrentPlayer.getFont().deriveFont(tFieldCurrentPlayer.getFont().getStyle() & ~Font.ITALIC | Font.BOLD, tFieldCurrentPlayer.getFont().getSize() + 2f));
		
		tFieldCurrentPlayer.setBackground(Color.WHITE);
		tFieldCurrentPlayer.setEditable(false);
		tFieldCurrentPlayer.setEnabled(false);
		tFieldCurrentPlayer.setBounds(12, 23, 116, 22);
		tFieldCurrentPlayer.setBorder(new LineBorder(tFieldCurrentPlayer.getBackground()));
		panel.add(tFieldCurrentPlayer);
		tFieldCurrentPlayer.setColumns(10);
		
		JLabel lbCurrentPlayer = new JLabel("CurrentPlayer");
		lbCurrentPlayer.setBounds(12, 9, 101, 16);
		panel.add(lbCurrentPlayer);
	}
	
	@Override
	public void update(Event e) {
		tFieldCurrentPlayer.setForeground(col.getColor(controller.getCurrentPlayer().getPlayerID()));
		tFieldCurrentPlayer.setText(controller.getCurrentPlayer().toString());
		repaint();
	}
}
