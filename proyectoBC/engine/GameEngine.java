package proyectoBC.engine;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.celdas.obstaculos.Ladrillo;
import proyectoBC.entities.tanques.enemigos.TanqueBasico;
import proyectoBC.entities.tanques.enemigos.TanqueEnemigo;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;
import proyectoBC.keyboard.ThreadKeyboard;
import proyectoBC.GUI.SwingWindow;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Rectangle;
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
	private Vector<TanqueEnemigo> enemies;
	private ThreadTanqueEnemigo enemiesthread;
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
	    this.enemies= new Vector<TanqueEnemigo>();
	    this.enemiesthread= new ThreadTanqueEnemigo(enemies,this);   
		// Creo el jugador y lo agrego el grafico a la gui.
		this.player = new TanqueJugador(3,96,288);
		// Creo los tanques  y lo agrego el grafico a la gui.
		threadenemigos();
		gui.getContentPane().add(this.player.getImage());
		System.out.println("Game Engine Creado");
		gui.setScore(0000);
		addmaplevel1();
	}	
	
	public void movePlayer(int dir){
		for (int i = 0;i < player.getSpeed() ; i++){
			player.move(dir,canMove(player,dir));
		}	
	}
	//Preguntar a la celda siguiente nada m�s
	public int canMove(Entity entity,int direccion){		
		int eWidth = entity.getImage().getWidth();
		int eHeight = entity.getImage().getHeight();
		int xE = (int) entity.getPosition().getX();
		int yE = (int) entity.getPosition().getY();
		int extraW = 0;
		int extraH = 0;
		int extraX = 0;
		int extraY = 0;
		switch (direccion) {
			case KeyEvent.VK_UP: if (yE == 0) return 0;extraY = - 2;extraH = 2;break;
			case KeyEvent.VK_DOWN: if (yE+eHeight == max_Y) return 0;extraH = 2;break;
			case KeyEvent.VK_LEFT: if (xE == 0) return 0;extraX = - 2;extraW = 2;break;
			case KeyEvent.VK_RIGHT: if(xE+eWidth == max_X) return 0;extraW = 2;break;
		}
		
		for (int i = 0; i < vCeldas.size(); i++) {
			Celda c = vCeldas.get(i);
			if (c.movein() == false) {
				int cWidth = c.getImage().getWidth();
				int cHeight = c.getImage().getHeight();
				int xC = (int) c.getPosition().getX();
				int yC = (int) c.getPosition().getY();
				Rectangle rE = new Rectangle(xE + extraX,yE + extraY,eWidth + extraW,eHeight + extraH);
				Rectangle rC = new Rectangle(xC,yC,cWidth,cHeight);
				if (rE.intersects(rC)) {
					return 0;
				}
			}
		}
		
		return 1;
		
	}	
	/**
	 * Armado del mapa nivel 1
	 * @param gui
	 */
	
	private void addmaplevel1(){
		int x = 150;
		int y = 80;
		for(int i = 0;i < 4; i++) {
			Celda c = new Celda(x,y,4,"ladrillo");
			this.vCeldas.add(c);
			gui.getContentPane().add(c.getImage());
			y += 50;
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

public void threadenemigos(){
	for(int i = 0; i < 4; i++){
		TanqueEnemigo enemy = new TanqueBasico (3,(i*50),(i) +40); 
		enemies.add(enemy);
		gui.getContentPane().add(enemy.getImage());
		}
	}
}
	

	
	
	

