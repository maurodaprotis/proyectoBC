package proyectoBC.entities.tanques.enemigos;

import javax.swing.ImageIcon;

import proyectoBC.entities.proyectiles.Proyectil;

public class TanqueBasico extends TanqueEnemigo{
	
	
	
	public TanqueBasico(int speed, int x, int y) {
		super(speed,x,y);
		points=100;
		hp=1;
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquebasico_right_1_02.gif"));
		this.images[1] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquebasico_down_1_02.gif"));
		this.images[2] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquebasico_left_1_02.gif"));
		this.images[3] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/tanquesenemigos/tanquebasico/tanquebasico_up_1_02.gif"));
		
	}
	
	
	public int impact(){
		hp--;
		if (hp==0)
			destroy();
		return hp;
	}
	
	public Proyectil shoot(){
		return new Proyectil(0,0,0);
	}
	
	public int getPoints(){
		return points;
	}
}
