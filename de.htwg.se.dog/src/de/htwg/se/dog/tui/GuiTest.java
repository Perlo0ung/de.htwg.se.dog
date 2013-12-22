package de.htwg.se.dog.tui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import de.htwg.se.dog.controller.GameTableInterface;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class GuiTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GameTableInterface controller;


	/**
	 * Create the frame.
	 */
	public GuiTest(GameTableInterface controller) {
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
		
		//JPanel gameField = new JPanel();
		JPanel gameField = new GuiDrawGameField(controller);
		gameField.setBackground(Color.WHITE);
		gameField.setBounds(0, 0, 1480, 878);
		contentPane.add(gameField);
		
		JPanel tuEs = new JPanel();
		tuEs.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "TU ES", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		tuEs.setBounds(0, 877, 1394, 88);
		contentPane.add(tuEs);
		tuEs.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 13, 1370, 62);
		tuEs.add(panel_1);
		
	}
}
