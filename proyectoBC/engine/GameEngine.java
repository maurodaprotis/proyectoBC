package proyectoBC.engine;

import proyectoBC.entities.tanques.jugadores.TanqueJugador;

public class GameEngine {
	
	private TanqueJugador player;
	
	
	public GameEngine() {
		System.out.println("Game Engine Creado");
	}
	
	public void movePlayer(int dir){
		player.move(canMove(dir));
	}
	
	private int canMove(int direccion){
		int x= player.getX();
		int y= player.getY();
		int distancia=0;
		switch (direccion){
			case 0: distancia= y;
			case 1: distancia= (450 - x) - 24;
			case 2: distancia= (300 - y) - 24;
			case 3: distancia= x;
		}
		return distancia;
	}
}
