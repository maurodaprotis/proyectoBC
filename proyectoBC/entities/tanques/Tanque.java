package proyectoBC.entities.tanques;

import proyectoBC.entities.Entity;

public abstract class Tanque extends Entity{
	protected int hp;
	protected int shootSpeed;
	//protected int direction; 0 is up, 1 is right, 2 is down 3 is left;

	public Tanque(int speed, int x, int y) {
		super(speed,x,y);
	}
		
	public abstract void shoot();
		
	public abstract void impact();
		
}

	




