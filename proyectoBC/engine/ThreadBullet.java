package proyectoBC.engine;

import java.awt.Rectangle;
import java.util.Iterator;
import java.util.Vector;

import proyectoBC.GUI.SwingWindow;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.enemigos.TanqueEnemigo;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;

public class ThreadBullet extends Thread {
	protected Vector<Proyectil> vProyectil;
	protected Vector<Proyectil> vProyectilEnemy;
	private Vector<Proyectil> vRemoveBulletsEnemies;
	private Vector<Proyectil> vRemoveBulletsPlayer; 
	private Vector<Celda> vRemoveCeldas;
	private Vector<TanqueEnemigo> vRemoveTanques;
	protected GameEngine ge;
	protected boolean play;
	protected SwingWindow gui;
	private volatile boolean detener;
	
	public ThreadBullet(Vector<Proyectil> vProyectil,Vector<Proyectil> vProyectilEnemy,GameEngine g,SwingWindow gui){
		start();
		this.vProyectil= vProyectil;
		this.vProyectilEnemy= vProyectilEnemy;
		this.vRemoveBulletsEnemies= new Vector<Proyectil>();
		this.vRemoveBulletsPlayer= new Vector<Proyectil>();
		this.vRemoveCeldas= new Vector<Celda>();
		this.vRemoveTanques= new Vector<TanqueEnemigo>();
		ge=g;
		this.gui=gui;
		play=true;
		this.detener = false;
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
		while (!this.detener){
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
		synchronized (vProyectil){
			Iterator<Proyectil> i=vProyectil.iterator();
			Proyectil proyectil;
			while (i.hasNext()){
				proyectil = i.next();
				if (enRango((int)proyectil.getPosition().getX(),(int)proyectil.getPosition().getY(),proyectil.getDireccion())){
					proyectil.move();											
					checkColisionCeldaPPlayer(proyectil);
					checkColisionEnemy(proyectil);
					checkColisionAguila(proyectil);
					gui.repaint();
				}
				else{
					vRemoveBulletsPlayer.add(proyectil);
					gui.remove(proyectil.getImage());
					gui.repaint();
				}
			}
			for (Proyectil p: vRemoveBulletsPlayer){
				vProyectil.remove(p);
			}
			vRemoveBulletsPlayer.removeAllElements();
		}
	}
	
	private void moveBulletsEnemies(){
		synchronized (vProyectilEnemy) {
			Iterator<Proyectil> i= vProyectilEnemy.iterator();
			Proyectil proyectil;
			while (i.hasNext()){
				proyectil= i.next();
				if (enRango((int)proyectil.getPosition().getX(),(int)proyectil.getPosition().getY(),proyectil.getDireccion())){
					proyectil.move();											
					checkColisionCeldaPEnemy(proyectil);
					checkColisionPlayer(proyectil);
					checkColisionAguila(proyectil);
					gui.repaint();
				}
				else{
					vRemoveBulletsEnemies.add(proyectil);
					proyectil.getTanque().setShooting(false);
					gui.remove(proyectil.getImage());
					gui.repaint();
				}
			}
			for (Proyectil p: vRemoveBulletsEnemies){
				vProyectilEnemy.remove(p);
			}
			vRemoveBulletsEnemies.removeAllElements();
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
	
	private void checkColisionCeldaPEnemy(Proyectil proyectil){
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
					vRemoveBulletsEnemies.add(proyectil);
					proyectil.getTanque().setShooting(false);
					gui.remove(proyectil.getImage());
					if (celda.impact(proyectil.getTanque()) == 0){
						vRemoveCeldas.add(celda);
						gui.remove(celda.getImage());
					}					
				}
			}			
		}
		for (Celda c: vRemoveCeldas){
			vCeldas.remove(c);
		}
		vRemoveCeldas.removeAllElements();
	}
	
	private void checkColisionCeldaPPlayer(Proyectil proyectil){
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
					vRemoveBulletsPlayer.add(proyectil);
					gui.remove(proyectil.getImage());
					if (celda.impact(ge.getPlayer()) == 0){
						vRemoveCeldas.add(celda);
						gui.remove(celda.getImage());
					}					
				}
			}			
		}
		for (Celda c: vRemoveCeldas){
			vCeldas.remove(c);
		}
		vRemoveCeldas.removeAllElements();
	}
	
	private void checkColisionEnemy(Proyectil proyectil){
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
					vRemoveBulletsPlayer.add(proyectil);
					gui.remove(proyectil.getImage());
					if (te.impact() == 0){
						vRemoveTanques.add(te);
						iTanqueEnemigo.remove();
						gui.remove(te.getImage());
						gui.setCantEnemies();
						gui.setScore(te.getPoints());
					}					
				}
		}
		for (TanqueEnemigo t: vRemoveTanques){
			vTanquesEnemigos.remove(t);
			ge.spawnEnemy();
		}
		vRemoveTanques.removeAllElements();
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
			vRemoveBulletsEnemies.add(proyectil);
			gui.remove(proyectil.getImage());
			gui.setCantLives();
			if (player.impact() == 0)
				
				ge.gameOver();						
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
			ge.getCeldas().remove(aguila);
			gui.getContentPane().remove(aguila.getImage());
			gui.getContentPane().remove(proyectil.getImage());
			ge.gameOver();
		}		
	}
	
	public void detener() {
		// Interrumpo el hilo para que no continue con su ejecución.
		this.interrupt(); 
		// Seteamos el flag para detener su ejecución.
		this.detener = true;
	}		
}
