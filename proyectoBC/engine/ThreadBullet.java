package proyectoBC.engine;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
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
	private Vector<Proyectil> vRemoveBulletsEnemies;
	protected GameEngine ge;
	protected boolean play;
	protected SwingWindow gui;
	
	
	public ThreadBullet(Vector<Proyectil> vProyectil,Vector<Proyectil> vProyectilEnemy,GameEngine g,SwingWindow gui){
		start();
		this.vProyectil= vProyectil;
		this.vProyectilEnemy= vProyectilEnemy;
		this.vRemoveBulletsEnemies= new Vector<Proyectil>();
		ge=g;
		this.gui=gui;
		play=true;
	}
	
	public void addBullet(Proyectil proyectil){
		vProyectil.add(proyectil);
		gui.getContentPane().add(proyectil.getImage());
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
                    ThreadBullet.sleep(20) ;
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
		Iterator<Proyectil> i=vProyectil.iterator();
		Proyectil proyectil;
		while (i.hasNext()){
			proyectil = i.next();
			if (enRango((int)proyectil.getPosition().getX(),(int)proyectil.getPosition().getY(),proyectil.getDireccion())){
				proyectil.move();											
				checkColisionCelda(proyectil,i);
				checkColisionEnemy(proyectil,i);
				checkColisionAguila(proyectil,i);
			}
			else{ //ge.removeBulletPlayer(proyectil);
				i.remove();
				gui.remove(proyectil.getImage());
			}
		}
	}
	
	private void moveBulletsEnemies(){
		Iterator<Proyectil> i= vProyectilEnemy.iterator();
		Proyectil proyectil;
		while (i.hasNext()){
			proyectil= i.next();
			if (enRango((int)proyectil.getPosition().getX(),(int)proyectil.getPosition().getY(),proyectil.getDireccion())){
				proyectil.move();											
				checkColisionCelda(proyectil,i);
				checkColisionPlayer(proyectil,i);
				checkColisionAguila(proyectil,i);
			}
			else{//ge.removeBulletEnemies(proyectil);
				//i.remove();
				System.out.println("Fuera de rango");
				vRemoveBulletsEnemies.add(proyectil);
				proyectil.getTanque().setShooting(false);
				//vProyectilEnemy.remove(proyectil);
				gui.remove(proyectil.getImage());
			}
		}
		for (Proyectil p: vRemoveBulletsEnemies){
			vProyectilEnemy.remove(p);
		}
		vRemoveBulletsEnemies.removeAllElements();
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
	
	private void checkColisionCelda(Proyectil proyectil,Iterator<Proyectil> iProyectil){
		System.out.println("Checkcolision con celda");
		int posXProyectil,posYProyectil,posXCelda, posYCelda, cWidth, cHeigth, pWidth, pHeigth;
		Rectangle recCelda,recProyectil;
		Vector<Celda> vCeldas = ge.getCeldas();
		posXProyectil= (int) proyectil.getPosition().getX();
		posYProyectil= (int) proyectil.getPosition().getY();
		pWidth= proyectil.getImage().getWidth();
		pHeigth= proyectil.getImage().getHeight();
		recProyectil= new Rectangle(posXProyectil,posYProyectil,pWidth,pHeigth);
		Iterator<Celda> iCelda= vCeldas.iterator();
		Celda celda;
		while (iCelda.hasNext()) {
			celda= iCelda.next();
			if (celda.impacton()) {
				posXCelda= (int) celda.getPosition().getX();
				posYCelda= (int) celda.getPosition().getY();
				cWidth= celda.getImage().getWidth();
				cHeigth= celda.getImage().getHeight();
				recCelda= new Rectangle(posXCelda,posYCelda,cWidth,cHeigth);
				if (recProyectil.intersects(recCelda)){
					System.out.println("Impacto proyectil con celda");
					//iProyectil.remove();
					//vProyectilEnemy.remove(proyectil);
					vRemoveBulletsEnemies.add(proyectil);
					proyectil.getTanque().setShooting(false);
					//ge.removeBulletPlayer(proyectil);
					gui.remove(proyectil.getImage());
					if (celda.impact() == 0){
						iCelda.remove();
						//ge.removeCelda(celda);
						vCeldas.remove(celda);
						gui.remove(celda.getImage());
					}					
				}
			}			
		}
	}
	
	private void checkColisionEnemy(Proyectil proyectil,Iterator<Proyectil> iProyectil){
		int posXProyectil,posYProyectil,posXCelda, posYCelda, cWidth, cHeigth, pWidth, pHeigth;
		Rectangle recCelda,recProyectil;
		Vector<TanqueEnemigo> vTanquesEnemigos = ge.getVectorEnemigos();
		posXProyectil= (int) proyectil.getPosition().getX();
		posYProyectil= (int) proyectil.getPosition().getY();
		pWidth= proyectil.getImage().getWidth();
		pHeigth= proyectil.getImage().getHeight();
		recProyectil= new Rectangle(posXProyectil,posYProyectil,pWidth,pHeigth);
		Iterator<TanqueEnemigo> iTanqueEnemigo= vTanquesEnemigos.iterator();
		TanqueEnemigo te;
		while (iTanqueEnemigo.hasNext()){
			te= iTanqueEnemigo.next();
			posXCelda= (int) te.getPosition().getX();
			posYCelda= (int) te.getPosition().getY();
			cWidth= te.getImage().getWidth();
			cHeigth= te.getImage().getHeight();
			recCelda= new Rectangle(posXCelda,posYCelda,cWidth,cHeigth);
				if (recProyectil.intersects(recCelda)){
					//ge.removeBulletEnemies(proyectil);
					iProyectil.remove();
					proyectil.getTanque().setShooting(false);
					gui.remove(proyectil.getImage());
					if (te.impact() == 0){
						//ge.removeEntity(te);
						iTanqueEnemigo.remove();
						gui.remove(te.getImage());
						gui.setScore(30);
					}					
				}
		}
	}
	
	private void checkColisionPlayer(Proyectil proyectil,Iterator<Proyectil> iProyectil){
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
			//ge.removeBulletPlayer(proyectil);
			iProyectil.remove();
			proyectil.getTanque().setShooting(false);
			gui.remove(proyectil.getImage());			
		}
	}
	
	private void checkColisionAguila(Proyectil proyectil,Iterator<Proyectil> iProyectil){
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
			//ge.removeBulletPlayer(proyectil);
			//iProyectil.remove();
			proyectil.getTanque().setShooting(false);
			gui.getContentPane().remove(proyectil.getImage());
			ge.removeCelda(aguila);
			ge.gameOver();
		}		
	}
}
