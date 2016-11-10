package proyectoBC.entities.tanques.jugadores.niveles;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class NivelTres implements Nivel {
	protected Icon images[];
	protected final int speed = 3;
	protected final int shootSpeed = 5;
	protected final int shootCant = 2;
	
	public NivelTres(){
		this.images = new Icon[4];
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel3/tanquejugadorN3_up_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel3/tanquejugadorN3_right_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel3/tanquejugadorN3_down_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanques/jugador/nivel3/tanquejugadorN3_left_1_02.gif"));
	}
	public Nivel SubirNivel() {
		return new NivelCuatro();
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
		return 3;
	}
}