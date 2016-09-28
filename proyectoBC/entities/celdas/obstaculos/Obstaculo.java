package proyectoBC.entities.celdas.obstaculos;

import proyectoBC.entities.celdas.Celda;

public abstract class Obstaculo {
	
	public Obstaculo (){
	}
	
	public abstract int impact ();
	public abstract boolean  movein ();

}
