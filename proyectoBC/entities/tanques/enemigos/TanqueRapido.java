package proyectoBC.entities.tanques.enemigos;

import javax.swing.ImageIcon;

import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.jugadores.niveles.Nivel;

public class TanqueRapido extends TanqueEnemigo{
	
	
	public TanqueRapido(int speed, int x, int y) {
		super(speed,x,y);
		shootSpeed=2;
		points=200;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquerapido/tanquerapido_up_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquerapido/tanquerapido_right_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquerapido/tanquerapido_down_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquerapido/tanquerapido_left_1_02.gif"));
	}
	
	public int impact(){
		return hp;
	}

	public int getPoints(){
		return points;
	}

	@Override
	public Nivel getLevel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
