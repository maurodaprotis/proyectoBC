package proyectoBC.entities.tanques.enemigos;

import javax.swing.ImageIcon;

import proyectoBC.entities.proyectiles.Proyectil;

public class TanqueRapido extends TanqueEnemigo{
	
	
	public TanqueRapido(int speed, int x, int y) {
		super(speed,x,y);
		points=40;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquerapido_up_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquerapido_right_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquerapido_down_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquerapido_left_1_02.gif"));
	}
	
	
	public int impact(){
		return hp;
	}
	
	public Proyectil shoot(){
		return new Proyectil(0,0,0);
	}

	public int getPoints(){
		return points;
	}
}
