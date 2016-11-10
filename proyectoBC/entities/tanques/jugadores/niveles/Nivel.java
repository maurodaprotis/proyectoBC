package proyectoBC.entities.tanques.jugadores.niveles;

import javax.swing.Icon;
//cambiar por clase abstracta
public interface Nivel {
	
	public Nivel SubirNivel();
	public Icon[] getImages();
	public int getSpeed();
	public int getShootSpeed();
	public int getShootCount();
	public int getNivel();
}
