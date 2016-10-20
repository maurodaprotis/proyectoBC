package proyectoBC.engine;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import proyectoBC.GUI.SwingWindow;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.Tanque;

public class ThreadBullet extends Thread {
	protected Vector<Proyectil> vProyectil;
	protected GameEngine ge;
	protected boolean play;
	protected SwingWindow gui;
	
	
	public ThreadBullet(GameEngine g,SwingWindow gui){
		start();
		vProyectil= new Vector<Proyectil>();
		ge=g;
		this.gui=gui;
		play=true;
	}
	
	public void addBullet(Proyectil p){
		
		vProyectil.add(p);
		gui.getContentPane().add(p.getImage());
		gui.repaint();
	}
	
	public int cantidadProyectiles(){
		return vProyectil.size();
	}
	
	public void run(){
		while (play){
            try {
                    ThreadBullet.sleep(12) ;
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            moveBullets();
            gui.repaint();  
		}
	}
	
	public void moveBullets(){
		
		Vector<Proyectil> elimProyectil = new Vector<Proyectil>();
		Vector<Celda> elimCelda = new Vector<Celda>();
		int posXProyectil, posYProyectil, posXCelda, posYCelda, pWidth, pHeigth, cWidth, cHeigth;
		Proyectil proyectil;		
		Celda celda;
		Rectangle recProyectil, recCelda;
		for (int i=0; i<vProyectil.size();i++){
			proyectil= vProyectil.get(i);
			proyectil.move();
			posXProyectil= (int) proyectil.getPosition().getX();
			posYProyectil= (int) proyectil.getPosition().getY();
			
			if (enRango(posXProyectil,posYProyectil,proyectil.getDireccion())){
				pWidth= proyectil.getImage().getWidth();
				pHeigth= proyectil.getImage().getHeight();
				recProyectil= new Rectangle(posXProyectil,posYProyectil,pWidth,pHeigth);				
				Vector<Celda> vCeldas = ge.getCeldas();
				
				for (int j=0; j<vCeldas.size();j++){
					celda= vCeldas.get(j);
					if (celda.impacton()) {
						posXCelda= (int) celda.getPosition().getX();
						posYCelda= (int) celda.getPosition().getY();
						cWidth= celda.getImage().getWidth();
						cHeigth= celda.getImage().getHeight();
						recCelda= new Rectangle(posXCelda,posYCelda,cWidth,cHeigth);
						if (recProyectil.intersects(recCelda)){
							elimProyectil.add(proyectil);
							gui.remove(proyectil.getImage());
							if (celda.impact() == 0){
								elimCelda.add(celda);
								gui.remove(celda.getImage());
							}					
						}
					}
					
				}
			}
			else
				{
					elimProyectil.add(proyectil);
					gui.remove(proyectil.getImage());
				}			
		}
		for (int i=0;i<elimProyectil.size();i++){
			vProyectil.remove(elimProyectil.get(i));
			gui.getContentPane().remove(elimProyectil.get(i).getImage());;
		}
			
		for (int i=0;i<elimCelda.size();i++)
			ge.removeCelda(elimCelda.get(i));
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
}
