package proyectoBC.entities.tanques.jugadores;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import proyectoBC.entities.tanques.Tanque;
import proyectoBC.entities.tanques.jugadores.niveles.*;

public class TanqueJugador extends Tanque {
	protected boolean shield;
	protected int lives;
	protected Nivel level;
	
	public TanqueJugador(int speed,int x,int y){
		super(speed,x,y);
		this.shield = false;
		this.lives = 4;
		this.level = new NivelUno();
		this.images = level.getImages();
		System.out.println("Tanque jugador Creado");
	}
	public void levelUp(){
		this.level = level.SubirNivel();
		this.images = this.level.getImages();
	}
	public void resetLevel(){
		this.level = new NivelUno();
		this.images = this.level.getImages();
	}
	public void move(int dir,int dist){
		int distance = (dist > this.level.getSpeed()) ? this.level.getSpeed():dist;
		switch (dir){
		case KeyEvent.VK_UP : 
			this.position.setLocation(this.position.x, this.position.y - distance);
			super.move(MOVE_UP);
			break;
		case KeyEvent.VK_DOWN :
			this.position.setLocation(this.position.x, this.position.y + distance);
			super.move(MOVE_DOWN);
			break;
		case KeyEvent.VK_LEFT :
			this.position.setLocation(this.position.x - distance, this.position.y);
			super.move(MOVE_LEFT);
			break;
		case KeyEvent.VK_RIGHT :
			this.position.setLocation(this.position.x + distance, this.position.y);
			super.move(MOVE_RIGHT);
			break;
		}		
	}
	public void impact(){		  
	}
	public void shoot(){
	}
}
