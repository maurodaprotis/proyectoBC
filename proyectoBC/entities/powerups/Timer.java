package proyectoBC.entities.powerups;

import javax.swing.ImageIcon;

import proyectoBC.engine.GameEngine;

public class Timer extends PowerUp{
	
	public Timer (int x,int y) {
		super(x,y);
		this.images[0] = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/powerups/timer.gif"));
	}
	
	public void activate(GameEngine ge) {
		ge.Timer();
	}
}
