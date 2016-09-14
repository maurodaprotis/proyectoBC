package proyectoBC.entities.tanques.enemigos;

import proyectoBC.entities.tanques.Tanque;

public abstract class TanqueEnemigo extends Tanque{
	
	
	public TanqueEnemigo(int speed, int x, int y) {
		super(speed,x,y);
	}
	
	public abstract void shoot();
	
	public abstract void impact();
	

}
