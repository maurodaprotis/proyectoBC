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
import javax.swing.UIManager;

public class SwingWindow extends JFrame {

	private static SwingWindow window;
	private JFrame frmBatleCity;
	private JPanel contentPane;
	private GameEngine ge;
	private ImageIcon obstaculos ;
	private ThreadKeyboard tk;
	private JLabel label_cantScore;
	private JLabel label_cant_Enemies;
	private JLabel label_cantLives;
	private JLabel label_cantStage;
	private JLabel lblGameOver;
	private JLabel lblWin;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new SwingWindow();
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
		
		JLabel label_player2 = new JLabel("New label");
		label_player2.setBackground(Color.WHITE);
		label_player2.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/icon_jugador_2.gif")));
		label_player2.setBounds(320, 228, 13, 14);
		contentPane.add(label_player2);
		
		label_cantLives = new JLabel("4");
		label_cantLives.setBackground(Color.BLACK);
		label_cantLives.setFont(new Font("Arial Black", Font.BOLD, 13));
		label_cantLives.setForeground(Color.BLACK);
		label_cantLives.setFocusCycleRoot(true);
		label_cantLives.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		label_cantLives.setBounds(338, 192, 22, 14);
		contentPane.add(label_cantLives);
		
		JLabel label_Enemies = new JLabel("New label");
		label_Enemies.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/tanque_enemigo.gif")));
		label_Enemies.setBounds(10, 327, 22, 14);
		contentPane.add(label_Enemies);
		
		label_cantStage = new JLabel("1");
		label_cantStage.setFont(new Font("Arial Black", Font.BOLD, 11));
		label_cantStage.setForeground(Color.WHITE);
		label_cantStage.setFocusCycleRoot(true);
		label_cantStage.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		label_cantStage.setBounds(336, 276, 24, 14);
		contentPane.add(label_cantStage);
		
	    JLabel label_Stage = new JLabel("New label");
		label_Stage.setForeground(Color.WHITE);
		label_Stage.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/stage.gif")));
		label_Stage.setBounds(320, 289, 46, 14);
		contentPane.add(label_Stage);
		
		JLabel label_Player = new JLabel("New label");
		label_Player.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/icon.png")));
		label_Player.setBounds(320, 193, 13, 17);
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
		
		label_cant_Enemies = new JLabel("16");
		label_cant_Enemies.setForeground(Color.YELLOW);
		label_cant_Enemies.setFont(new Font("Arial Black", Font.BOLD, 13));
		label_cant_Enemies.setFocusCycleRoot(true);
		label_cant_Enemies.setDisplayedMnemonic(KeyEvent.VK_KATAKANA);
		label_cant_Enemies.setBounds(33, 327, 22, 14);
		contentPane.add(label_cant_Enemies);
		
		lblWin = new JLabel("       Victoria");
		lblWin.setBackground(Color.BLACK);
		lblWin.setFont(new Font("Monospaced", Font.PLAIN, 24));
		lblWin.setForeground(Color.YELLOW);
		lblWin.setBounds(0, 0, 310, 316);
		
		lblGameOver = new JLabel();
		lblGameOver.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/pantalla/gameover.gif")));
		lblGameOver.setBounds(0, 0, 312, 314);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,366,385);
		
		
		initGame();
	}
	
	public void initGame(){
		this.ge = new GameEngine(this);
		this.tk = new ThreadKeyboard(this.ge);
		this.addKeyListener(tk);		
	}
	
	public void SwingGameOver() {
		this.tk.stop();
		contentPane.add(lblGameOver);
		contentPane.setComponentZOrder(lblGameOver, 1);
	}
	
	public void gameWin() {
		this.tk.stop();
		contentPane.add(lblWin);
		this.label_cant_Enemies.setText("0");
		contentPane.setComponentZOrder(lblWin, 1);
	}
	
	public void setScore(Integer s) {
		String st =this.label_cantScore.getText();
		int sc= Integer.parseInt(st);
		int newScore= sc + s;
		this.label_cantScore.setText(new Integer(newScore).toString());
	}
	

	public int getScore(){
		return Integer.parseInt(label_cantScore.getText());
	}
	

	public void setCantEnemies (int enemiesleft){

		String ce =this.label_cant_Enemies.getText();
		int cantEnemies= Integer.parseInt(ce);
		int newcantEnemies= cantEnemies-enemiesleft;
		if (newcantEnemies==0){
			newcantEnemies=16;
			String cs =this.label_cantStage.getText();
			int nextLevel= Integer.parseInt(cs);
			nextLevel++;
			this.label_cantStage.setText(new Integer(nextLevel).toString());	
		}    
		this.label_cant_Enemies.setText(new Integer(newcantEnemies).toString());	
	}
	
	public void setCantLives (int leftlives){
		String cl =this.label_cantLives.getText();
		int cantLives= Integer.parseInt(cl);
		cantLives=leftlives;
		if(cantLives==0)
			cantLives=0;
		this.label_cantLives.setText(new Integer(cantLives).toString());	
	}
	
	public void addLive(){
		String cl =this.label_cantLives.getText();
		int cantLives= Integer.parseInt(cl);
		cantLives+=1;
		this.label_cantLives.setText(new Integer(cantLives).toString());
	}
}	

