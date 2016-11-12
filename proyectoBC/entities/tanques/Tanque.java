package proyectoBC.entities.tanques;

import proyectoBC.entities.Entity;
import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.jugadores.niveles.Nivel;

public abstract class Tanque extends Entity{
	protected int hp;
	protected int shootSpeed;
	protected int direccion;
	protected boolean shooting;
	protected long timer;
	//protected int direction; 0 is up, 1 is right, 2 is down 3 is left;

	public Tanque(int speed, int x, int y) {
		super(speed,x,y);
	}
		
	public int getDireccion(){
		return direccion;
	}
	
	public abstract Proyectil shoot();
		
	public abstract int impact();
	
	public void destroy() {
		timer = System.currentTimeMillis() + 1000;
		super.destroy();
	}
	
	public long getExplosion(){
		return timer;
	}
		
	public abstract void setShooting(boolean value);
	
	public abstract Nivel getLevel();
}

	




