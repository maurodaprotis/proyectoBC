package proyectoBC.entities.powerups;

import javax.swing.ImageIcon;

import proyectoBC.engine.GameEngine;

public class Star extends PowerUp{
	
	public Star (int x,int y) {
		super(x,y);
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/powerups/estrella.gif"));
	}
	
	public void activate(GameEngine ge) {
		ge.Star();
	}
}
