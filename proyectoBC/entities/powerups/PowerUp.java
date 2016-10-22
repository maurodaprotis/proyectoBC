package proyectoBC.entities.powerups;

import javax.swing.JLabel;

import proyectoBC.engine.GameEngine;
import proyectoBC.entities.Entity;

public abstract class PowerUp extends Entity{
	protected PowerUp(int x,int y){
		super(0,x,y);
	}

	public abstract void activate(GameEngine ge);
	
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see proyectoBC.entities.Entity#getImage()
	 */
	@Override
	public JLabel getImage() {
		if(this.image == null){
			this.image = new JLabel(this.images[0]);
			this.image.setBounds(this.position.x, this.position.y, this.width, this.height);
		}
		return this.image;
	}
}