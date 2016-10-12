package proyectoBC.engine;

import java.awt.Point;
import java.util.Vector;

import proyectoBC.GUI.SwingWindow;
import proyectoBC.entities.proyectiles.Proyectil;

public class ThreadBullet extends Thread {
	protected Vector<Proyectil> vProyectil;
	protected GameEngine ge;
	protected boolean play;
	protected SwingWindow gui;
	protected int direccion;
	
	public ThreadBullet(GameEngine g,SwingWindow gui,int direccion){
		start();
		vProyectil= new Vector<Proyectil>();
		ge=g;
		this.gui=gui;
		play=true;
	}
	
	public void addBullet(Proyectil p){
		vProyectil.add(p);
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
		int factor;
		 for (int i=0; i<vProyectil.size();i++){
             Proyectil proyectil=vProyectil.get(i);
             Point posicion= proyectil.getPosition();
             switch (direccion){
             	//case 1 : proyectil.;
             }
		 }
	}
}
