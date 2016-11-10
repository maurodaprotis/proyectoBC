package proyectoBC.entities.celdas.obstaculos;

import proyectoBC.entities.tanques.Tanque;

public abstract class Obstaculo {
	
	public Obstaculo (){
	}
	
	public abstract int impact (Tanque t);
	public abstract boolean  movein ();
	public abstract boolean impacton();

}
