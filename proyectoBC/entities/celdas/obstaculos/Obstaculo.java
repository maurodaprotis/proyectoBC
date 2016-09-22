package proyectoBC.entities.celdas.obstaculos;

import proyectoBC.entities.celdas.Celda;

public abstract class Obstaculo extends Celda {
	
	public Obstaculo (int speed,int x,int y ,int hp){
		super (0,x,y,hp);
	}
	
	public abstract int impact ();
	public abstract void  movein (int e);

}
