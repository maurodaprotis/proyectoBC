package proyectoBC.entities.tanques.enemigos;

import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.Tanque;

public abstract class TanqueEnemigo extends Tanque{
	
	protected int points;
	
	public TanqueEnemigo(int speed, int x, int y) {
		super(speed,x,y);
		points=0;
	}
	
	public abstract Proyectil shoot();
	
	public abstract int impact();
	
	public abstract int getPoints();

}
