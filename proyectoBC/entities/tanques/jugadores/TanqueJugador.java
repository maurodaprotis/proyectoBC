package proyectoBC.entities.tanques.jugadores;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.Tanque;
import proyectoBC.entities.tanques.jugadores.niveles.*;

public class TanqueJugador extends Tanque {
	protected boolean shield;
	protected int lives;
	protected Nivel level;
	
	
	
	public TanqueJugador(int speed,int x,int y){
		super(speed,x,y);
		this.shield = false;
		this.lives = 4;
		this.level = new NivelUno();
		this.images = level.getImages();
		direccion=0;
	}
	public void levelUp(){
		this.level = level.SubirNivel();
		this.images = this.level.getImages();
		this.image.setIcon(this.images[0]);
		this.image.setBounds(this.position.x, this.position.y, this.width, this.height);
	}
	public void resetLevel(){
		this.level = new NivelUno();
		this.images = this.level.getImages();
		this.image.setIcon(this.images[0]);
		this.position.setLocation(96,288);
		this.direccion = MOVE_UP;
		this.image.setBounds(this.position.x, this.position.y, this.width, this.height);
		this.move(MOVE_UP,0);
	}
	public void move(int dir,int dist){
		int distance = (dist > this.level.getSpeed()) ? this.level.getSpeed():dist;
		switch (dir){
		case KeyEvent.VK_UP : 
			this.position.setLocation(this.position.x, this.position.y - distance); 
			this.direccion=MOVE_UP;
			super.move(MOVE_UP);
			break;
		case KeyEvent.VK_DOWN :
			this.position.setLocation(this.position.x, this.position.y + distance);
			this.direccion=MOVE_DOWN;
			super.move(MOVE_DOWN);
			break;
		case KeyEvent.VK_LEFT :
			this.position.setLocation(this.position.x - distance, this.position.y);
			this.direccion=MOVE_LEFT;
			super.move(MOVE_LEFT);
			break;
		case KeyEvent.VK_RIGHT :
			this.position.setLocation(this.position.x + distance, this.position.y);
			this.direccion=MOVE_RIGHT;
			super.move(MOVE_RIGHT);
			break;
		}		
	}
	
	public int impact(){
		// Escudo activo ignoro golpes
		if (shield) return 1;
		else {
			// Si me queda más de una vida, re aparezco en el inicio
			if (lives > 1) {
				lives--;
				this.resetLevel();
				return 1;
			}
		}
		return 0;
	}
	
	
	public Proyectil shoot(){
		if (this.direccion == 0)
			return new Proyectil(direccion,level.getShootSpeed(),(int) position.getX() + 10,(int) position.getY(),this);
		if (this.direccion == 1)
			return new Proyectil(direccion,level.getShootSpeed(),(int) position.getX() + 23,(int) position.getY() +12,this);
		if (this.direccion == 2)
			return new Proyectil(direccion,level.getShootSpeed(),(int) position.getX() + 10,(int) position.getY() + 23,this);
		if (this.direccion == 3)
			return new Proyectil(direccion,level.getShootSpeed(),(int) position.getX(),(int) position.getY() + 12,this);
		return null;
	}
	
	public int shootCount(){
		return level.getShootCount();
	}
	
	public int getSpeed(){
		return level.getSpeed();
	}
	
	public void setScore(int points){
		
	}
	
	public void ShieldToggle(){
		this.shield = !this.shield;
	}
	
	public void liveUp() {
		this.lives++;
	}
	
	public int lives() {
		return lives;
	}
	
	public void addLives(){
		lives++;
	}
	
	public void setShooting(boolean shoot){}
	
	public Nivel getLevel(){
		return level;
	}
	
	public void setPoint(int x, int y){
		this.position.setLocation(x, y);
	}
}
