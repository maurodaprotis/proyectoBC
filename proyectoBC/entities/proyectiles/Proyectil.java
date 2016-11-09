package proyectoBC.entities.proyectiles;

import javax.swing.ImageIcon;

import proyectoBC.entities.Entity;
import proyectoBC.entities.tanques.Tanque;

public class Proyectil extends Entity{

	protected int direccion;
	protected Tanque tanque;
	
	public Proyectil(int direccion,int speed, int x, int y,Tanque tanque) {
		super(speed, x, y);
		this.direccion = direccion;
		this.tanque=tanque;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/disparo/bala_"+direccion+".gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/disparo/bala_"+direccion+".gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/disparo/bala_"+direccion+".gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/disparo/bala_"+direccion+".gif"));
		
		// TODO Auto-generated constructor stub
	}

	public int getDireccion(){
		return direccion;
	}
	
	public void move(){
		switch(direccion){
			case MOVE_UP: this.position.setLocation(this.position.x, this.position.y - speed); break;
			case MOVE_DOWN: this.position.setLocation(this.position.x, this.position.y + speed); break;
			case MOVE_RIGHT: this.position.setLocation(this.position.x + speed, this.position.y); break;
			case MOVE_LEFT: this.position.setLocation(this.position.x - speed, this.position.y); break;
		}
		super.move(direccion);
	}
	
	public Tanque getTanque(){
		return tanque;
	}
}
