package proyectoBC.entities.tanques.jugadores;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import proyectoBC.entities.tanques.Tanque;

public class TanqueJugador extends Tanque {
	protected int level;
	protected boolean shield;
	protected int lives;
	
	public TanqueJugador(int speed,int x,int y){
		super(speed,x,y);
		this.shield = false;
		this.lives = 4;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/0_24x24.png"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/0_24x24der.png"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/0_24x24inv.png"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/0_24x24izq.png"));
		System.out.println("Tanque jugador Creado");
	}
	public void move(int dir,int dist){
		int distance = (dist > this.speed) ? this.speed:dist;
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
