package proyectoBC.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.KeyAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;

import proyectoBC.engine.GameEngine;
import proyectoBC.keyboard.ThreadKeyboard;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;

public class SwingWindow extends JFrame {

	private JFrame frmBatleCity;
	private JPanel contentPane;
	private GameEngine ge;
	private ImageIcon obstaculos ;
	private ThreadKeyboard tk;
	private JLabel labelScore;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingWindow window = new SwingWindow();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingWindow() {
		setResizable(false);
		setTitle("Battle City 2016");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingWindow.class.getResource("/proyectoBC/assets/images/icon.png")));

		this.contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		this.contentPane.setBorder(new EmptyBorder(0,0,0,0));
		this.contentPane.setLayout(null);
		setContentPane(this.contentPane);
		getContentPane().setLayout(null);
		
		JLabel labelDashboard = new JLabel("");
		labelDashboard.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/interfaz/Tablero.png")));
		labelDashboard.setBounds(312, 0, 48, 314);
		contentPane.add(labelDashboard);
		
		labelScore = new JLabel("0");
		labelScore.setFocusCycleRoot(true);
		labelScore.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		labelScore.setForeground(Color.WHITE);
		labelScore.setFont(new Font("Arial Black", labelScore.getFont().getStyle() & ~Font.BOLD, labelScore.getFont().getSize() + 2));
		labelScore.setBounds(202, 325, 48, 14);
		contentPane.add(labelScore);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/CartelScore.gif")));
		lblNewLabel.setBounds(126, 325, 60, 14);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setBounds(0, 312, 316, 2);
		contentPane.add(separator);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,366,385);
		
		initGame();
	}
	
	private void initGame(){
		this.ge = new GameEngine(this);
		this.tk = new ThreadKeyboard(this.ge);
		this.addKeyListener(tk);
	}
	
	public void setScore(Integer s) {
		String st =this.labelScore.getText();
		int sc= Integer.parseInt(st);
		int newScore= sc + s;
		this.labelScore.setText(new Integer(newScore).toString());
	}
}