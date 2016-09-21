package proyectoBC.entities.tanques.enemigos;

import javax.swing.ImageIcon;

public class TanqueBlindado extends TanqueEnemigo {
	
	
	
	
	
	
	public TanqueBlindado(int speed, int x, int y) {
		super(speed,x,y);
		points=100;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanqueblindado_up_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanqueblindado_right_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanqueblindado_down_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanqueblindado_left_1_02.gif"));
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
