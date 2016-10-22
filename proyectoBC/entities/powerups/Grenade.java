package proyectoBC.entities.powerups;

import javax.swing.ImageIcon;

import proyectoBC.engine.GameEngine;

public class Grenade extends PowerUp{
	
	public Grenade (int x,int y) {
		super(x,y);
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/powerups/granada.gif"));
	}
	
	public void activate(GameEngine ge) {
		ge.Grenade();
	}
}
