package proyectoBC.entities.tanques.jugadores.niveles;

import javax.swing.Icon;

public interface Nivel {
	
	public Nivel SubirNivel();
	public Icon[] getImages();
	public int getSpeed();
	public int getShootSpeed();
	public int getShootCount();
}
