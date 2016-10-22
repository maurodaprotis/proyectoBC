package proyectoBC.entities.powerups;

import javax.swing.ImageIcon;

import proyectoBC.engine.GameEngine;

public class Showel extends PowerUp{
	
	public Showel(int x,int y) {
		super(x,y);
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/powerups/pala.gif"));
	}
	
	public void activate(GameEngine ge) {
		ge.Showel();
	}
}

