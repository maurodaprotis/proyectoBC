package proyectoBC.entities.tanques.jugadores.niveles;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class NivelUno implements Nivel {
	protected Icon images[];
	protected final int speed = 3;
	protected final int shootSpeed = 3;
	protected final int shootCant = 1;
	
	public NivelUno(){
		this.images = new Icon[4];
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel1/tanquejugador_n1_up_1.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel1/tanquejugador_n1_right_1.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel1/tanquejugador_n1_down_1.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel1/tanquejugador_n1_left_1.gif"));
	}
	public Nivel SubirNivel() {
		return new NivelDos();
	}
	public Icon[] getImages(){
		return images;
	}
	public int getSpeed() {
		return speed;
	}
	public int getShootSpeed(){
		return shootSpeed;
	}
	public int getShootCount(){
		return shootCant;
	}
	@Override
	public int getNivel() {
		return 1;
	}
}
