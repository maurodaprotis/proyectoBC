package proyectoBC.entities.proyectiles;

import proyectoBC.entities.Entity;

public class Proyectil extends Entity{

	protected int direccion;
	
	public Proyectil(int direccion,int speed, int x, int y) {
		super(speed, x, y);
		this.direccion = direccion;
		
		
		// TODO Auto-generated constructor stub
	}

	
}
