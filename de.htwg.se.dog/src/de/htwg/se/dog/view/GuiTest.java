package de.htwg.se.dog.view;

import java.awt.Color;
import java.awt.Event;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.models.PlayerInterface;
import de.htwg.se.dog.util.IOEvent;
import de.htwg.se.dog.util.IObserver;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GuiTest extends JFrame implements IObserver {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GameTableInterface controller;
	private ColorMap col = new ColorMap();
	private JFormattedTextField tFieldCurrentPlayer;
	private JComboBox<Object> cbCards;

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
            public void windowClosing(WindowEvent e){
            	int quit = JOptionPane.showConfirmDialog(contentPane,"Wirklich beenden?","Beenden",JOptionPane.YES_NO_OPTION);
                    if(quit == JOptionPane.YES_OPTION ){
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
				if (JOptionPane.showConfirmDialog(contentPane, "Wirklich beenden?",
						"Beenden", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		mnExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				Event.ALT_MASK));
		mnExit.setIcon(new ImageIcon(this.getClass()
				.getResource("/off.png").getPath()));
		mnGame.addSeparator();
		mnGame.add(mnExit);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel gameField = new GuiDrawGameField(controller);
		//JPanel gameField = new JPanel();
		gameField.setBounds(0, 0, 1274, 668);
		contentPane.add(gameField);
		gameField.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 668, 1274, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tFieldCurrentPlayer = new JFormattedTextField();
		tFieldCurrentPlayer.setFont(tFieldCurrentPlayer.getFont().deriveFont(tFieldCurrentPlayer.getFont().getStyle() | Font.BOLD, tFieldCurrentPlayer.getFont().getSize() + 4f));
		tFieldCurrentPlayer.setBorder(new LineBorder(tFieldCurrentPlayer.getBackground()));
		tFieldCurrentPlayer.setBackground(Color.WHITE);
		tFieldCurrentPlayer.setBounds(93, 28, 116, 22);
		panel.add(tFieldCurrentPlayer);
		tFieldCurrentPlayer.setColumns(10);
		
		JLabel lbCurrentPlayer = new JLabel("CurrentPlayer");
		lbCurrentPlayer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbCurrentPlayer.setBounds(93, 13, 101, 16);
		panel.add(lbCurrentPlayer);
		
		cbCards = new JComboBox<Object>();
		cbCards.setBackground(Color.WHITE);
		cbCards.setBounds(232, 28, 138, 22);
		panel.add(cbCards);
		
		JLabel lblPlayCard = new JLabel("Cardlist");
		lblPlayCard.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayCard.setBounds(232, 13, 56, 16);
		panel.add(lblPlayCard);
		
		JButton btnPlayIt = new JButton("play");
		btnPlayIt.setBackground(Color.WHITE);
		btnPlayIt.setBounds(371, 28, 97, 22);
		panel.add(btnPlayIt);
		
		JPanel figures = new GuiDrawFigures(controller);
		//JPanel figures = new JPanel();
		figures.setBounds(18, -3, 72, 60);
		panel.add(figures);
		figures.setBackground(Color.WHITE);
	}
	
	@Override
	public void update(IOEvent e) {
		PlayerInterface current = controller.getCurrentPlayer();
		tFieldCurrentPlayer.setForeground(col.getColor(current.getPlayerID()));
		tFieldCurrentPlayer.setText(current.toString());
		cbCards.setModel(new DefaultComboBoxModel<Object>(current.getCardList().toArray()));

		repaint();
	}
}
