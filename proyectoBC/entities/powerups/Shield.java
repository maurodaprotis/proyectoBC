package proyectoBC.entities.powerups;

import javax.swing.ImageIcon;

import proyectoBC.engine.GameEngine;

public class Shield extends PowerUp{
	
	public Shield (int x,int y) {
		super(x,y);
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/powerups/casco.gif"));
	}
	
	public void activate(GameEngine ge) {
		ge.Shield();
	}
}
