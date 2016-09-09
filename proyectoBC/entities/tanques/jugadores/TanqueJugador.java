package proyectoBC.entities.tanques.jugadores;

import proyectoBC.engine.GameEngine;
import proyectoBC.entities.tanques.Tanque;

public class TanqueJugador extends Tanque {
	protected int level;
	protected boolean shield;
	protected int lives;
	protected GameEngine ge;
	protected String[] imagenes;
	
	public TanqueJugador(int x,int y,GameEngine ge){
		this.x = x;
		this.y = y;
		this.ge = ge;
		this.shield = false;
		this.lives = 4;
		this.imagenes = new String[4];
		this.imagenes[0] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24.png");
		this.imagenes[1] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24der.png");
		this.imagenes[2] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24inv.png");
		this.imagenes[3] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24izq.png");
		
		System.out.println("Tanque jugador Creado");
	}
	
	public String[] getImages() {
		return this.imagenes;
	}
}
