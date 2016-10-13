package proyectoBC.entities.tanques.enemigos;

import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.Tanque;

public abstract class TanqueEnemigo extends Tanque{
	
	protected int points;
	protected int rnd;
	public TanqueEnemigo(int speed, int x, int y) {
		super(speed,x,y);
		Random rnd = new Random();
		this.rnd= (rnd.nextInt(100))%2;
		this.direccion=MOVE_UP;
		points=0;
	}
	
	public void move() {
	
			switch (direccion) {
			
			case MOVE_UP : 				
				this.position.setLocation(this.position.x, this.position.y - 1);	
				break;
			case MOVE_DOWN :
				this.position.setLocation(this.position.x, this.position.y + 1);
				break;
			case MOVE_LEFT :
				this.position.setLocation(this.position.x - 1, this.position.y);
				break;
			case MOVE_RIGHT :
				this.position.setLocation(this.position.x + 1, this.position.y);
				break;
		}
		
		super.move(direccion);	
		}
	public void girar(){
	if (rnd==1){
		this.direccion=(this.direccion+1)%4;
	           }
		else{
			if (direccion == 0) direccion = 3;
			else {
				direccion-= 1;
			}
		}
		super.move(direccion);
	}
	
	public abstract Proyectil shoot();
	
	public abstract int impact();
	
	public abstract int getPoints();

}
