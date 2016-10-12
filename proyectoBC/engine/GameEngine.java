package proyectoBC.engine;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.celdas.obstaculos.Ladrillo;
import proyectoBC.entities.tanques.enemigos.TanqueBasico;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;
import proyectoBC.keyboard.ThreadKeyboard;
import proyectoBC.GUI.SwingWindow;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class GameEngine {
	
	private TanqueJugador player;
	private TanqueBasico enemigoBasico;
	private ThreadKeyboard keyboard;
	private Vector<Celda> vCeldas;
	private Celda celda;
	private SwingWindow gui;
	private ImageIcon ladrillo;
	private ImageIcon aguila;
	private ImageIcon acero;
	
	private int score;
	
	
	private static final int width=24;
	private static final int height=24;
	private static final int max_X=312;
	private static final int max_Y=312;


	public GameEngine(SwingWindow gui) {
		this.gui = gui;
		this.vCeldas = new Vector<Celda>();
		// Creo el jugador y lo agrego el grafico a la gui.
		this.player = new TanqueJugador(3,96,288);
		gui.getContentPane().add(this.player.getImage());
		System.out.println("Game Engine Creado");
		gui.setScore(0000);
		addmaplevel1();
	}	
	
	public void movePlayer(int dir){
		int distancia = canMove(player,dir);
		System.out.println(distancia);
		player.move(dir,distancia);
	}
	//Preguntar a la celda siguiente nada más
	private int canMove(Entity entity,int direccion){
		return 1;	
	}	
	/**
	 * Armado del mapa nivel 1
	 * @param gui
	 */
	
	private void addmaplevel1(){
		int x = 150;
		int y = 100;
		for(int i = 0;i < 4; i++) {
			Celda c = new Celda(x,y,4,"ladrillo");
			this.vCeldas.add(c);
			gui.getContentPane().add(c.getImage());
			y += 40;
		}
		gui.repaint();
	}	
	
	public void agregarTanque(){
		if (enemigoBasico==null){
			enemigoBasico= new TanqueBasico(1,0,0);
			System.out.println("Agrego tanque enemigo");
			gui.getContentPane().add(enemigoBasico.getImage());			
		}
		else
			destruirTanque();
		gui.repaint();
	}
	
	private void destruirTanque(){
		System.out.println("Destruyo tanque enemigo");
		if (enemigoBasico.impact()==0){
			gui.remove(enemigoBasico.getImage());
			score= score + enemigoBasico.getPoints();
			enemigoBasico=null;
			gui.setScore(score);
		}
	}
	
	public void levelUp(){
		this.player.levelUp();
		gui.repaint();
	}
	public void levelDown(){
		this.player.resetLevel();
		gui.repaint();
	}		
}

	

	
	
	

