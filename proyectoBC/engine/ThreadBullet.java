package proyectoBC.engine;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import proyectoBC.GUI.SwingWindow;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.Tanque;
import proyectoBC.entities.tanques.enemigos.TanqueEnemigo;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;

public class ThreadBullet extends Thread {
	protected Vector<Proyectil> vProyectil;
	protected Vector<Proyectil> vProyectilEnemy;
	protected GameEngine ge;
	protected boolean play;
	protected SwingWindow gui;
	
	
	public ThreadBullet(Vector<Proyectil> vProyectil,Vector<Proyectil> vProyectilEnemy,GameEngine g,SwingWindow gui){
		start();
		this.vProyectil= vProyectil;
		this.vProyectilEnemy= vProyectilEnemy;
		ge=g;
		this.gui=gui;
		play=true;
	}
	
	public void addBullet(Proyectil p){
		vProyectil.add(p);
		gui.getContentPane().add(p.getImage());
		gui.repaint();
	}
	
	public void addBulletEnemy(Proyectil p){
		vProyectilEnemy.add(p);
		gui.getContentPane().add(p.getImage());
		gui.repaint();
	}
	
	public int cantidadProyectiles(){
		return vProyectil.size();
	}
	
	public void run(){
		while (play){
            try {
                    ThreadBullet.sleep(30) ;
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            moveBullets();
            moveBulletsEnemies();
            gui.repaint();  
		}
	}
	
	private void moveBullets(){
		Proyectil proyectil;		
		for (int i=0; i<vProyectil.size();i++){
			proyectil= vProyectil.get(i);
			if (enRango((int)proyectil.getPosition().getX(),(int)proyectil.getPosition().getY(),proyectil.getDireccion())){
				proyectil.move();											
				checkColisionCelda(proyectil);
				checkColisionEnemy(proyectil);
				checkColisionAguila(proyectil);
			}
			else{ ge.removeBulletPlayer(proyectil);
			}
		}
		
	}
	
	private void moveBulletsEnemies(){
		Proyectil proyectil;	
		for (int i=0; i<vProyectilEnemy.size();i++){
			proyectil= vProyectilEnemy.get(i);
			if (enRango((int)proyectil.getPosition().getX(),(int)proyectil.getPosition().getY(),proyectil.getDireccion())){
				proyectil.move();											
				checkColisionCelda(proyectil);
				checkColisionPlayer(proyectil);
				checkColisionAguila(proyectil);
			}
			else{	ge.removeBulletEnemies(proyectil);			
			}
		}
	}
	
	private boolean enRango(int x, int y,int dir){
		switch (dir){
			case 0:  return (y >= 0); 
			case 1: return (x <= 312); 
			case 2: return (y <= 312); 
			case 3: return (x >= 0);
			default: return true;
		}
	}
	
	private void checkColisionCelda(Proyectil proyectil){
		int posXProyectil,posYProyectil,posXCelda, posYCelda, cWidth, cHeigth, pWidth, pHeigth;
		Rectangle recCelda,recProyectil;
		Celda celda;
		Vector<Celda> vCeldas = ge.getCeldas();
		posXProyectil= (int) proyectil.getPosition().getX();
		posYProyectil= (int) proyectil.getPosition().getY();
		pWidth= proyectil.getImage().getWidth();
		pHeigth= proyectil.getImage().getHeight();
		recProyectil= new Rectangle(posXProyectil,posYProyectil,pWidth,pHeigth);		
		for (int j=0; j<vCeldas.size();j++){
			celda= vCeldas.get(j);
			if (celda.impacton()) {
				posXCelda= (int) celda.getPosition().getX();
				posYCelda= (int) celda.getPosition().getY();
				cWidth= celda.getImage().getWidth();
				cHeigth= celda.getImage().getHeight();
				recCelda= new Rectangle(posXCelda,posYCelda,cWidth,cHeigth);
				if (recProyectil.intersects(recCelda)){
					ge.removeBulletPlayer(proyectil);
					gui.remove(proyectil.getImage());
					if (celda.impact() == 0){
						ge.removeCelda(celda);
						gui.remove(celda.getImage());
					}					
				}
			}			
		}
	}
	
	private void checkColisionEnemy(Proyectil proyectil){
		int posXProyectil,posYProyectil,posXCelda, posYCelda, cWidth, cHeigth, pWidth, pHeigth;
		Rectangle recCelda,recProyectil;
		TanqueEnemigo te;
		Vector<TanqueEnemigo> vTanquesEnemigos = ge.getVectorEnemigos();
		posXProyectil= (int) proyectil.getPosition().getX();
		posYProyectil= (int) proyectil.getPosition().getY();
		pWidth= proyectil.getImage().getWidth();
		pHeigth= proyectil.getImage().getHeight();
		recProyectil= new Rectangle(posXProyectil,posYProyectil,pWidth,pHeigth);		
		for (int j=0; j<vTanquesEnemigos.size();j++){
			te= vTanquesEnemigos.get(j);
				posXCelda= (int) te.getPosition().getX();
				posYCelda= (int) te.getPosition().getY();
				cWidth= te.getImage().getWidth();
				cHeigth= te.getImage().getHeight();
				recCelda= new Rectangle(posXCelda,posYCelda,cWidth,cHeigth);
				if (recProyectil.intersects(recCelda)){
					ge.removeBulletEnemies(proyectil);
					gui.remove(proyectil.getImage());
					if (te.impact() == 0){
						ge.removeEntity(te);
						gui.remove(te.getImage());
						gui.setScore(30);
					}					
				}
		}
	}
	
	private void checkColisionPlayer(Proyectil proyectil){
		int posXProyectil,posYProyectil,posXPlayer, posYPlayer, playerWidth, playerHeigth, pWidth, pHeigth;
		Rectangle recPlayer,recProyectil;
		TanqueJugador player=ge.getPlayer();
		posXProyectil= (int) proyectil.getPosition().getX();
		posYProyectil= (int) proyectil.getPosition().getY();
		pWidth= proyectil.getImage().getWidth();
		pHeigth= proyectil.getImage().getHeight();
		recProyectil= new Rectangle(posXProyectil,posYProyectil,pWidth,pHeigth);		
		posXPlayer= (int) player.getPosition().getX();
		posYPlayer= (int) player.getPosition().getY();
		playerWidth= player.getImage().getWidth();
		playerHeigth= player.getImage().getHeight();
		recPlayer= new Rectangle(posXPlayer,posYPlayer,playerWidth,playerHeigth);
		if (recProyectil.intersects(recPlayer)){
			ge.removeBulletPlayer(proyectil);
			gui.remove(proyectil.getImage());			
		}
	}
	
	private void checkColisionAguila(Proyectil proyectil){
		int posXProyectil,posYProyectil,posXAguila, posYAguila, aguilaWidth, aguilaHeight, pWidth, pHeigth;
		Rectangle recAguila,recProyectil;
		posXProyectil= (int) proyectil.getPosition().getX();
		posYProyectil= (int) proyectil.getPosition().getY();
		pWidth= proyectil.getImage().getWidth();
		pHeigth= proyectil.getImage().getHeight();
		recProyectil= new Rectangle(posXProyectil,posYProyectil,pWidth,pHeigth);
		Celda aguila= ge.getAguila();
		posXAguila= (int) aguila.getPosition().getX();
		posYAguila= (int) aguila.getPosition().getY();
		aguilaWidth= aguila.getImage().getWidth();
		aguilaHeight= aguila.getImage().getHeight();
		recAguila= new Rectangle(posXAguila,posYAguila,aguilaWidth,aguilaHeight);
		if (recProyectil.intersects(recAguila)){
			ge.removeBulletPlayer(proyectil);
			gui.getContentPane().remove(proyectil.getImage());
			ge.removeCelda(aguila);
			ge.gameOver();
		}
		
	}
}
