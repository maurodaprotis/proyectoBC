package proyectoBC.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import proyectoBC.engine.GameEngine;
import proyectoBC.keyboard.ThreadKeyboard;

import javax.swing.ImageIcon;

public class SwingWindow {

	private JFrame frmBatleCity;
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
					window.frmBatleCity.setVisible(true);
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
		initialize();
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
		
		JLabel Backgroundlbl = new JLabel("");
		Backgroundlbl.setIcon(new ImageIcon(SwingWindow.class.getResource("/proyectoBC/assets/images/interfaz/Background.png")));
		GroupLayout groupLayout = new GroupLayout(frmBatleCity.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Backgroundlbl)
					.addContainerGap(398, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Backgroundlbl)
					.addContainerGap(257, Short.MAX_VALUE))
		);
		frmBatleCity.getContentPane().setLayout(groupLayout);
		
		initGame();
	}
	
	private void initGame(){
		this.ge = new GameEngine(this);
		this.tk = new ThreadKeyboard(ge);
	}
}
