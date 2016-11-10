package proyectoBC.entities.tanques.enemigos;

import javax.swing.ImageIcon;

import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.jugadores.niveles.Nivel;


public class TanquePoder extends TanqueEnemigo {

	public TanquePoder(int speed, int x, int y) {
		super(speed,x,y);
		shootSpeed=3;
		points=300;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquepoder/tanquepoder_up_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquepoder/tanquepoder_right_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquepoder/tanquepoder_down_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquepoder/tanquepoder_left_1_02.gif"));
	}
	
	public int impact(){
		return hp;
	}

	@Override
	public Nivel getLevel(){
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getPoints(){
		return points;
	}
	
}
