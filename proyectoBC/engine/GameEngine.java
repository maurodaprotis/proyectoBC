package proyectoBC.engine;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.celdas.obstaculos.Ladrillo;
import proyectoBC.entities.powerups.*;
import proyectoBC.entities.tanques.enemigos.TanqueBasico;
import proyectoBC.entities.tanques.enemigos.TanqueEnemigo;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;
import proyectoBC.keyboard.ThreadKeyboard;
import proyectoBC.File.FReader;
import proyectoBC.GUI.SwingWindow;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class GameEngine extends Thread {
	
	private TanqueJugador player;
	private TanqueBasico enemigoBasico;
	private ThreadKeyboard keyboard;
	private ThreadBullet threadBullet;
	private Vector<TanqueEnemigo> enemies;
	private Vector<TanqueEnemigo> vDestroyedEnemies;
	private Vector<PowerUp> vPowerUps;
	private ThreadTanqueEnemigo enemiesthread;
	private Vector<Celda> vCeldas;
	private Vector<Celda> vBaseCeldas;
	private Celda aguila;
	private SwingWindow gui;
	private FReader file;
	
	private int score;
	
	private boolean gameOver,grenadeUp,showelUp,shieldUp,timerUp;
	private long showelTime;
	private long shieldTime;
	private long timerTime;
	private long grenadeTime;
	
	
	private static final int width=24;
	private static final int height=24;
	private static final int max_X=312;
	private static final int max_Y=312;


	public GameEngine(SwingWindow gui) {
		gameOver = grenadeUp = showelUp = shieldUp = timerUp = false;
		this.gui = gui;
		this.vCeldas = new Vector<Celda>();
		this.vBaseCeldas = new Vector<Celda>();
		this.vPowerUps = new Vector<PowerUp>();
	    this.enemies= new Vector<TanqueEnemigo>();
	    this.vDestroyedEnemies = new Vector<TanqueEnemigo>();
	    this.enemiesthread= new ThreadTanqueEnemigo(enemies,this);   
		// Creo el jugador y lo agrego el grafico a la gui.
		this.player = new TanqueJugador(3,96,288);
		// Creo los tanques  y lo agrego el grafico a la gui.
		threadenemigos();
		this.threadBullet = new ThreadBullet(this,gui);
		gui.getContentPane().add(this.player.getImage());
		System.out.println("Game Engine Creado");
		gui.setScore(0000);
		file= new FReader();
		initBase();
		addmaplevel1();
		//this.tryPA();
		
		this.start();
	}	
	
	public void run() {
		this.timerTime = System.currentTimeMillis();
		this.shieldTime = System.currentTimeMillis();
		this.showelTime = System.currentTimeMillis();
		this.grenadeTime = System.currentTimeMillis();
		while (!gameOver) {
			try {
                ThreadKeyboard.sleep(30);
			 }catch (InterruptedException e) {
               e.printStackTrace();
			 }
			
			// Si se activo power up grenade
			if (grenadeUp) {
				// Si ya paso el tiempo de las explosiones
				if (grenadeTime < System.currentTimeMillis()) {
					for (int i = 0; i < vDestroyedEnemies.size(); i++) {
						this.gui.getContentPane().remove(vDestroyedEnemies.get(i).getImage());
					}
					vDestroyedEnemies.clear();
				}
			}
			
			//Si se activo powerUp shield
			if (shieldUp) {
				//Si ya paso el tiempo del shield
				if (shieldTime < System.currentTimeMillis()) {
					this.player.ShieldToggle();
				}
			}
			
			//Si se activo timer
			if (timerUp) {
				// Si ya paso el tiempo del timer
				if (timerTime < System.currentTimeMillis()) {
					this.enemiesthread.continuar();
				}
			}
			
			if (showelUp) {
				if (showelTime < System.currentTimeMillis()) {
					
					for (int i=0;i<this.vBaseCeldas.size();i++) {
						Celda c = this.vBaseCeldas.get(i);
						this.vCeldas.remove(c);
						c.set("ladrillo");
						this.addCelda(c);
					}
					gui.repaint();
					
				}
			}
			
		}
	}
	
	public Vector<Celda> getCeldas(){
		return this.vCeldas;
	}
	public void removeCelda(Celda c){
		this.vCeldas.remove(c);
		this.gui.getContentPane().remove(c.getImage());
		gui.repaint();
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
	
	private void addCelda(Celda c) {
		this.vCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
		gui.repaint();
	}
	
	private void initBase() {
		Celda c = new Celda(6*24,12*24,1,"aguila");
		this.aguila = c;
		this.vCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
		
		c = new Celda(5*24,12*24,4,"ladrillo");
		this.vCeldas.add(c);
		this.vBaseCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
		
		c = new Celda(7*24,12*24,4,"ladrillo");
		this.vCeldas.add(c);
		this.vBaseCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
		
		c = new Celda(5*24,11*24,4,"ladrillo");
		this.vCeldas.add(c);
		this.vBaseCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
		
		c = new Celda(6*24,11*24,4,"ladrillo");
		this.vCeldas.add(c);
		this.vBaseCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
		
		c = new Celda(7*24,11*24,4,"ladrillo");
		this.vCeldas.add(c);
		this.vBaseCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
	}
	
	/**
	 * Armado del mapa nivel 1
	 * @param gui
	 */
	
	private void addmaplevel1(){
		try {
			String [][] obs= file.getObstaculos("Map1");
			String o;
			Celda c=null;
			int posX=0;
			int posY=0;
			for(int i=0;i<obs[0].length;i++){
				for(int j=0;j<obs.length;j++){
					o=obs[i][j];
					if (j!=0){
						posX=posX + 24;
					}
					if (o=="ladrillo" || o=="acero" || o=="arbol" || o=="agua" || o=="aguila"){
						c = new Celda(posX,posY,4,o);
						this.vCeldas.add(c);
						gui.getContentPane().add(c.getImage());
						gui.getContentPane().setComponentZOrder(c.getImage(), 1);
					}
				}
				posY=posY+24;
				posX=0;
			}	
			
			/*int x = 150;
			int y = 80;
			for(int i = 0;i < 4; i++) {
				Celda c = null;
				if (i == 0) c = new Celda(x,y,4,"ladrillo");
				if (i == 1) c = new Celda(x,y,4,"agua");
				if (i == 2) c = new Celda(x,y,4,"arbol");
				if (i == 3) c = new Celda(x,y,4,"acero");
			
				this.vCeldas.add(c);
				gui.getContentPane().add(c.getImage());
				y += 50;
			}*/
			gui.repaint();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public Vector<TanqueEnemigo> getVectorEnemigos() {
		return this.enemies;
	}
	
	public TanqueJugador getPlayer(){
		return player;
	}
	
	public Celda getAguila() {
		return this.aguila;
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
			TanqueEnemigo enemy = new TanqueBasico (3,(i*50),0); 
			enemies.add(enemy);
			gui.getContentPane().add(enemy.getImage());
			}
	}
	
	public void shoot() {
		if (threadBullet.cantidadProyectiles() < player.shootCount()) {
			threadBullet.addBullet(player.shoot());
		}
	}
	
	public void Star() {
		this.levelUp();
	}
	
	public void Shield() {
		this.shieldTime = System.currentTimeMillis() + 10000;
		this.shieldUp = true;
		this.player.ShieldToggle();
	}
	
	public void Grenade() {
		this.grenadeTime = System.currentTimeMillis() + 1000;
		this.grenadeUp = true;
		for (int i = 0; i < enemies.size(); i++) {
			TanqueEnemigo te = enemies.get(i);
			te.destroy();
			vDestroyedEnemies.add(te);
		}
		enemies.clear();		
	}
	
	public void Timer() {
		this.timerTime = System.currentTimeMillis() + 10000;
		this.timerUp = true;
		this.enemiesthread.detener();		
	}
	
	public void Tank() {
		this.player.levelUp();
	}
	
	public void Showel() {
		this.showelTime = System.currentTimeMillis() + 10000;
		this.showelUp = true;
		
		for (int i=0;i<this.vBaseCeldas.size();i++) {
			Celda c = this.vBaseCeldas.get(i);
			this.vCeldas.remove(c);
			c.set("acero");
			this.addCelda(c);
		}
		gui.repaint();
		
	}
	
	private void tryPA() {
		PowerUp p = new Grenade(24,24);
		gui.getContentPane().add(p.getImage());
		gui.getContentPane().setComponentZOrder(p.getImage(), 0);
		this.vPowerUps.add(p);
		p = new Shield(48,24);
		gui.getContentPane().add(p.getImage());
		gui.getContentPane().setComponentZOrder(p.getImage(), 0);
		this.vPowerUps.add(p);
		p = new Showel(72,24);
		gui.getContentPane().add(p.getImage());
		gui.getContentPane().setComponentZOrder(p.getImage(), 0);
		this.vPowerUps.add(p);
		p = new Star(96,24);
		gui.getContentPane().add(p.getImage());
		gui.getContentPane().setComponentZOrder(p.getImage(), 0);
		this.vPowerUps.add(p);
		p = new Tank(120,24);
		gui.getContentPane().add(p.getImage());
		gui.getContentPane().setComponentZOrder(p.getImage(), 0);
		this.vPowerUps.add(p);
		p = new Timer(144,24);
		gui.getContentPane().add(p.getImage());
		gui.getContentPane().setComponentZOrder(p.getImage(), 0);
		this.vPowerUps.add(p);
		gui.repaint();		
	}
}
	

	
	
	

