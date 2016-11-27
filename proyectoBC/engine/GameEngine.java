package proyectoBC.engine;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.celdas.obstaculos.Ladrillo;
import proyectoBC.entities.powerups.*;
import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.enemigos.TanqueBasico;
import proyectoBC.entities.tanques.enemigos.TanqueBlindado;
import proyectoBC.entities.tanques.enemigos.TanqueEnemigo;
import proyectoBC.entities.tanques.enemigos.TanquePoder;
import proyectoBC.entities.tanques.enemigos.TanqueRapido;
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
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import proyectoBC.engine.levels.*;

public class GameEngine extends Thread {
	
	private TanqueJugador player;
	private TanqueBasico enemigoBasico;
	private ThreadKeyboard keyboard;
	private ThreadBullet threadBullet;
	private Vector<TanqueEnemigo> enemies;
	private Vector<TanqueEnemigo> vDestroyedEnemies;
	private Vector<PowerUp> vPowerUps;
	private Vector<Proyectil> vBulletsPlayer;
	private Vector<Proyectil> vBulletsEnemies;
	private ThreadTanqueEnemigo enemiesthread;
	private Vector<Celda> vCeldas;
	private Vector<Celda> vBaseCeldas;
	private Celda aguila;
	private SwingWindow gui;
	private FReader file;
	private int enemiesLives;
	private Level level;
	
	private int score;
	
	private boolean gameOver,grenadeUp,showelUp,shieldUp,timerUp,addLive,upLevel;
	private long showelTime;
	private long shieldTime;
	private long timerTime;
	private long grenadeTime;
	private long timeChangeLevel;
	
	
	private static final int width=24;
	private static final int height=24;
	private static final int max_X=312;
	private static final int max_Y=312;


	public GameEngine(SwingWindow gui) {
		gameOver = grenadeUp = showelUp = shieldUp = timerUp = addLive = upLevel= false;
		this.gui = gui;
		this.vCeldas = new Vector<Celda>();
		this.vBaseCeldas = new Vector<Celda>();
		this.vPowerUps = new Vector<PowerUp>();
	    this.enemies= new Vector<TanqueEnemigo>();
	    this.vDestroyedEnemies = new Vector<TanqueEnemigo>();
	    this.vBulletsPlayer= new Vector<Proyectil>();
	    this.vBulletsEnemies= new  Vector<Proyectil>();
		initBase();
		this.level = new LevelOne(this);
		// Creo el jugador y lo agrego el grafico a la gui.
		this.player = new TanqueJugador(3,96,288);		
		gui.getContentPane().add(this.player.getImage());
		this.threadBullet = new ThreadBullet(vBulletsPlayer,vBulletsEnemies,this,gui);
		// Creo los tanques  y lo agrego el grafico a la gui.
		threadenemigos();		
	    this.enemiesthread= new ThreadTanqueEnemigo(enemies,this,threadBullet);   
		gui.setScore(0000);
		enemiesLives=16;
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
					this.spawnEnemy();
					this.spawnEnemy();
					this.spawnEnemy();
					this.spawnEnemy();
					this.subEnemies();
					this.subEnemies();
					this.subEnemies();
					this.subEnemies();
					grenadeUp = false;
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
				/**
				if (timerTime < System.currentTimeMillis()) {
					//this.enemiesthread.continuar();
					timerUp = false;
				}
				**/
				
			}
			
			if (showelUp) 
				if (showelTime < System.currentTimeMillis()){
					Iterator<Celda> iCelda = vBaseCeldas.iterator();
					while(iCelda.hasNext()) {
						Celda c = iCelda.next();
						c.set("ladrillo");
					}						
					showelUp=false;
					gui.repaint();	
				}
			if ((gui.getScore() >= 20000) && (!addLive)){
				addLive= true;
				player.addLives();
				gui.addLive();
			}
			if (upLevel){
				if (timeChangeLevel < System.currentTimeMillis()){
					for(Celda c : vCeldas) {
						gui.remove(c.getImage());
					}
					this.vCeldas.removeAllElements();
					for(PowerUp p : vPowerUps) {
						gui.remove(p.getImage());
					}
					this.vPowerUps.removeAllElements();
					for(TanqueEnemigo t : enemies) {
						gui.remove(t.getImage());
					}
					this.enemies.removeAllElements();
					this.gui.remove(player.getImage());
										
					this.level = this.level.upLevel();
					if (this.level == null) {
						upLevel = false;
						gameWin();
						break;
					}
					this.enemiesLives = 16;
					initBase();
					player.setPoint(96,288);
					gui.getContentPane().add(this.player.getImage());
					player.move(0,0);		
					this.threadenemigos();					
					this.threadBullet = new ThreadBullet(vBulletsPlayer,vBulletsEnemies,this,gui);
					this.enemiesthread = new ThreadTanqueEnemigo(this.enemies,this,this.threadBullet);
					upLevel=false;
					this.movePlayer(38);
				}
			}
			this.checkPowerUpColision();
			gui.repaint();
		}
	}
	
	public void gameWin() {
		this.gui.gameWin();
		gui.repaint();
		this.interrupt();
	}
	
	public void gameOver() {		
		this.enemiesthread.detener();
		this.threadBullet.detener();
		this.gui.SwingGameOver();
	}
	
	public void subEnemies(){
		enemiesLives=enemiesLives - 1 ;
	}
	
	public int getEnemies(){
		return enemiesLives;
	}
	
	public void upLevelGame(){
		timeChangeLevel= System.currentTimeMillis() + 3000;
		this.enemiesthread.detener();
		this.threadBullet.detener();
		upLevel= true;
	}
	
	public Vector<Celda> getCeldas(){
		return this.vCeldas;
	}
	
	public int getLeftEnemies() {
		return level.leftEnemies();
	}
	
	public int getLeftLives() {
		return player.lives();
	}
	
	public void movePlayer(int dir){
		if (!upLevel) {
			for (int i = 0;i < player.getSpeed() ; i++){
				int d = 0;
				if (checkColisionEnemy(player,dir) == 1 && canMove(player,dir) == 1 ) {
					d = 1;
				}
				player.move(dir,d);
			}
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
		synchronized (vCeldas){
			Iterator<Celda> iCeldas = vCeldas.iterator();
			while (iCeldas.hasNext()) {
				Celda c = iCeldas.next();
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
		}
		
		return 1;
		
	}	
	
	public void spawnEnemy() {
		TanqueEnemigo t = this.level.getTanque();
		if (t != null) {
			this.addEnemy(t);
		}
	}
	
	public void addCelda(Celda c) {
		this.vCeldas.add(c);
		gui.getContentPane().add(c.getImage());
		gui.getContentPane().setComponentZOrder(c.getImage(), 1);
	}
	
	public void addPowerUp(PowerUp p) {
		this.vPowerUps.add(p);
		gui.getContentPane().add(p.getImage());
		gui.getContentPane().setComponentZOrder(p.getImage(), 1);
	}
	
	public void addEnemy(TanqueEnemigo t) {
		this.enemies.add(t);
		gui.getContentPane().add(t.getImage());
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
	
	
	public void agregarTanque(){
		if (enemigoBasico==null){
			enemigoBasico= new TanqueBasico(1,0,0);
			gui.getContentPane().add(enemigoBasico.getImage());			
		}
		else
			destruirTanque();
	}
	
	private void destruirTanque(){
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
	}
	public void levelDown(){
		this.player.resetLevel();
	}		

	public void threadenemigos(){
		for(int i = 0; i < 4; i++)
			this.spawnEnemy();
	}
	
	public void shoot() {
		if (threadBullet.cantidadProyectiles() < player.shootCount()) {
			Proyectil proyectil= player.shoot();
			threadBullet.addBullet(proyectil);
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
		synchronized(enemies){
			Iterator<TanqueEnemigo> iEnemies = enemies.iterator();
			while(iEnemies.hasNext()) {
				TanqueEnemigo te = iEnemies.next();
				te.destroy();;	
				gui.setScore(te.getPoints());
				vDestroyedEnemies.add(te);
				iEnemies.remove();
			}
			gui.setCantEnemies(vDestroyedEnemies.size());
		}
	}
	
	public void Timer() {
		this.enemiesthread.timerUp();
		/**
		this.timerTime = System.currentTimeMillis() + 10000;
		this.timerUp = true;
		this.enemiesthread.detener();
		**/		
	}
	
	public void Tank() {
		this.player.liveUp();
		gui.setCantLives(player.lives() - 1);
	}
	
	public void Showel() {
		this.showelTime = System.currentTimeMillis() + 10000;
		this.showelUp = true;
		synchronized (vBaseCeldas){
			Iterator<Celda> iCelda = vBaseCeldas.iterator();
			while(iCelda.hasNext()) {
				Celda c = iCelda.next();
				c.set("acero");
			}
		}
	}
		
	private void checkPowerUpColision() {
		Vector<PowerUp> toRemove = new Vector<PowerUp>();
		int eWidth = player.getImage().getWidth();
		int eHeight = player.getImage().getHeight();
		int xE = (int) player.getPosition().getX();
		int yE = (int) player.getPosition().getY();
		int extraW = 0;
		int extraH = 0;
		int extraX = 0;
		int extraY = 0;
		synchronized (vPowerUps){
			Iterator<PowerUp> iPowerUp = vPowerUps.iterator();
			while (iPowerUp.hasNext()) {
				PowerUp t = iPowerUp.next();
				int tWidth = t.getImage().getWidth();
				int tHeight = t.getImage().getHeight();
				int xT = (int) t.getPosition().getX();
				int yT = (int) t.getPosition().getY();
				Rectangle rE = new Rectangle(xE + extraX,yE + extraY,eWidth + extraW,eHeight + extraH);
				Rectangle rT = new Rectangle(xT,yT,tWidth,tHeight);
				if (rE.intersects(rT)) {
					t.activate(this);
					toRemove.add(t);
					gui.setScore(500);
				}
			}
			for(int i = 0;i < toRemove.size();i++) {
				this.vPowerUps.remove(toRemove.get(i));
				this.gui.remove(toRemove.get(i).getImage());
			}
		
		toRemove.removeAllElements();
		}
	}
	
	private int checkColisionEnemy(Entity entity,int direccion){
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
		synchronized (enemies){
			Iterator<TanqueEnemigo> iEnemy = enemies.iterator();
			while (iEnemy.hasNext()) {
				TanqueEnemigo t = iEnemy.next();
				int tWidth = t.getImage().getWidth();
				int tHeight = t.getImage().getHeight();
				int xT = (int) t.getPosition().getX();
				int yT = (int) t.getPosition().getY();
				Rectangle rE = new Rectangle(xE + extraX,yE + extraY,eWidth + extraW,eHeight + extraH);
				Rectangle rT = new Rectangle(xT,yT,tWidth,tHeight);
				if (rE.intersects(rT)) {
					return 0;
				}
			}
		}
		return 1;
	}
	
	public int checkColisionPlayer(Entity entity,int direccion){
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
		
		int tWidth = player.getImage().getWidth();
		int tHeight = player.getImage().getHeight();
		int xT = (int) player.getPosition().getX();
		int yT = (int) player.getPosition().getY();
		Rectangle rE = new Rectangle(xE + extraX,yE + extraY,eWidth + extraW,eHeight + extraH);
		Rectangle rT = new Rectangle(xT,yT,tWidth,tHeight);
		if (rE.intersects(rT)) {
			return 0;
		}
		return 1;
	}
}
	

	
	
	

