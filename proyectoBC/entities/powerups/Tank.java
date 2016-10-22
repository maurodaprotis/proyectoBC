package proyectoBC.entities.powerups;

import javax.swing.ImageIcon;

import proyectoBC.engine.GameEngine;

public class Tank extends PowerUp{
	
	public Tank (int x,int y) {
		super(x,y);
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/powerups/tanque.gif"));
	}
	
	public void activate(GameEngine ge) {
		ge.Tank();
	}
}
