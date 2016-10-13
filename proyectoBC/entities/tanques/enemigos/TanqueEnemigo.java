package proyectoBC.entities.tanques.enemigos;

import java.util.Random;

import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.Tanque;

public abstract class TanqueEnemigo extends Tanque{
	
	protected int points;
	protected int rnd;
	public TanqueEnemigo(int speed, int x, int y) {
		super(speed,x,y);
		this.direccion=MOVE_DOWN;
		Random rnd = new Random();
		 this.rnd= (rnd.nextInt(100))%2;
	
		points=0;
	}
	
	public void move() {
	
		
		   
			switch (dir) {
			
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
		
		super.move(dir);	
		}
	public void girar(){
	if (rnd==1)
		this.direccion=(this.direccion+1)%2;
		else
			this.direccion=(this.direccion-1)%2; 
			
	}
		
	}
	
	public abstract Proyectil shoot();
	
	public abstract int impact();
	
	public abstract int getPoints();

}
