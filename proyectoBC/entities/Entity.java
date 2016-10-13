package proyectoBC.entities;

import java.awt.Point;

import javax.swing.Icon;
import javax.swing.JLabel;

public abstract class Entity {
	
	public static final int MOVE_UP    = 0;
	public static final int MOVE_RIGHT     = 1;
	public static final int MOVE_DOWN = 2;
	public static final int MOVE_LEFT   = 3;
	protected final int width  = 24;
	protected final int height = 24;
	
	protected JLabel image;
	protected Icon images[];
	protected Icon destroyedImage;
	protected int speed;
	protected Point position;
	
	protected Entity(int speed, int x, int y) {
		this.position  = new Point(x, y);
		this.speed = speed;
		this.images    = new Icon[4];
	}
	
	public int getSpeed() {
		return this.speed;
	}

	public Point getPosition() {
		return this.position;
	}
	
	protected void move(int dir) {
		if(this.image != null){
			this.image.setIcon(this.images[dir]);
			this.image.setBounds(this.position.x, this.position.y, this.images[dir].getIconWidth(), this.images[dir].getIconHeight());
		}
	}
	
	protected void destroy() {
		if(this.image != null){
			this.image.setIcon(this.destroyedImage);
			this.image.setBounds(this.position.x, this.position.y, this.width, this.height);
		}
	}
	
	public JLabel getImage() {
		if(this.image == null){
			this.image = new JLabel(this.images[0]);
			this.image.setBounds(this.position.x, this.position.y, this.width, this.height);
		}
		return this.image;
	}
	
}
