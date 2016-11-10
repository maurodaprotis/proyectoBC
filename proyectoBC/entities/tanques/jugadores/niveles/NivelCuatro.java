package proyectoBC.entities.tanques.jugadores.niveles;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class NivelCuatro implements Nivel {
	protected Icon images[];
	protected final int speed = 3;
	protected final int shootSpeed = 7;
	protected final int shootCant = 3;
	
	public NivelCuatro(){
		this.images = new Icon[4];
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel4/tanquejugadorN4_up_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel4/tanquejugadorN4_right_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel4/tanquejugadorN4_down_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel4/tanquejugadorN4_left_1_02.gif"));
	}
	public Nivel SubirNivel() {
		return this;
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
		return 4;
	}
}