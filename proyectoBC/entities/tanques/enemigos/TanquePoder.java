package proyectoBC.entities.tanques.enemigos;

import javax.swing.ImageIcon;

public class TanquePoder extends TanqueEnemigo {

	public TanquePoder(int speed, int x, int y) {
		super(speed,x,y);
		points=200;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquepoder_up_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquepoder_right_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquepoder_down_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquepoder_left_1_02.gif"));
	}
	
	public int impact(){
		return hp;
	}
	
	public void shoot(){
		
	}
	
	public int getPoints(){
		return points;
	}
}
