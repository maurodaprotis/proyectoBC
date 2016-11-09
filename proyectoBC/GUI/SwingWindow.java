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
	private JLabel label_cantScore;
	

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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(196, 194, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label_cantLifes = new JLabel("3");
		label_cantLifes.setBackground(Color.BLACK);
		label_cantLifes.setFont(new Font("Arial Black", Font.BOLD, 13));
		label_cantLifes.setForeground(Color.YELLOW);
		label_cantLifes.setFocusCycleRoot(true);
		label_cantLifes.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		label_cantLifes.setBounds(338, 227, 22, 14);
		contentPane.add(label_cantLifes);
		
		JLabel label_Enemies = new JLabel("New label");
		label_Enemies.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/tanque_enemigo.gif")));
		label_Enemies.setBounds(10, 327, 22, 14);
		contentPane.add(label_Enemies);
		
		JLabel label_cantStage = new JLabel("1");
		label_cantStage.setFont(new Font("Arial Black", Font.BOLD, 11));
		label_cantStage.setForeground(Color.WHITE);
		label_cantStage.setFocusCycleRoot(true);
		label_cantStage.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		label_cantStage.setBounds(326, 276, 48, 14);
		contentPane.add(label_cantStage);
		
		JLabel label_Stage = new JLabel("New label");
		label_Stage.setForeground(Color.WHITE);
		label_Stage.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/stage.gif")));
		label_Stage.setBounds(314, 290, 46, 14);
		contentPane.add(label_Stage);
		
		JLabel label_Player = new JLabel("New label");
		label_Player.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/lifes.gif")));
		label_Player.setBounds(326, 228, 13, 17);
		contentPane.add(label_Player);
		
		label_cantScore = new JLabel("0");
		label_cantScore.setFocusCycleRoot(true);
		label_cantScore.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		label_cantScore.setForeground(Color.WHITE);
		label_cantScore.setFont(new Font("Arial Black", label_cantScore.getFont().getStyle() & ~Font.BOLD, label_cantScore.getFont().getSize() + 2));
		label_cantScore.setBounds(196, 329, 48, 14);
		contentPane.add(label_cantScore);
		
		JLabel labelDashboard = new JLabel("");
		labelDashboard.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/interfaz/Tablero.png")));
		labelDashboard.setBounds(312, 0, 48, 314);
		contentPane.add(labelDashboard);
		
		JLabel label_Score = new JLabel("New label");
		label_Score.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/CartelScore.gif")));
		label_Score.setBounds(126, 329, 60, 14);
		contentPane.add(label_Score);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setBounds(0, 312, 316, 2);
		contentPane.add(separator);
		
		JLabel label_cant_Enemies = new JLabel("0");
		label_cant_Enemies.setForeground(Color.ORANGE);
		label_cant_Enemies.setFont(new Font("Arial Black", Font.BOLD, 13));
		label_cant_Enemies.setFocusCycleRoot(true);
		label_cant_Enemies.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		label_cant_Enemies.setBounds(33, 327, 22, 14);
		contentPane.add(label_cant_Enemies);
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
		String st =this.label_cantScore.getText();
		int sc= Integer.parseInt(st);
		int newScore= sc + s;
		this.label_cantScore.setText(new Integer(newScore).toString());
	}
}