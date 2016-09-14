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

public class SwingWindow extends JFrame {

	private JFrame frmBatleCity;
	private JPanel contentPane;
	private GameEngine ge;
	private ThreadKeyboard tk;

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
		//initialize();
		this.contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		this.contentPane.setBorder(new EmptyBorder(0,0,0,0));
		this.contentPane.setLayout(null);
		setContentPane(this.contentPane);
		getContentPane().setLayout(null);
		
		
		//JLabel background = new JLabel("");
		//background.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/interfaz/Background.png")));
		//background.setBounds(0, 0, 365, 365);
		//contentPane.add(background);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,365,365);
		
		initGame();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBatleCity = new JFrame();
		frmBatleCity.setTitle("Battle City");
		frmBatleCity.setIconImage(Toolkit.getDefaultToolkit().getImage(SwingWindow.class.getResource("/proyectoBC/assets/images/icon.png")));
		frmBatleCity.setResizable(false);
		frmBatleCity.setBounds(100, 100, 365, 365);
		frmBatleCity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		frmBatleCity.getContentPane().add(panel, BorderLayout.NORTH);
		
		
		initGame();
	}
	
	private void initGame(){
		this.ge = new GameEngine(this);
		//this.tk = new ThreadKeyboard(ge);
		//this.tk.listen();
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ev){
				distpach(ev);
			}
		});
	}
	
	private void distpach(KeyEvent key){
		this.ge.movePlayer(key.getKeyCode());
	}
}
