package proyectoBC.entities.tanques.enemigos;

import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import proyectoBC.entities.proyectiles.Proyectil;
import proyectoBC.entities.tanques.Tanque;

public abstract class TanqueEnemigo extends Tanque{
	
	protected int points;
	protected int rnd;

	public TanqueEnemigo(int speed, int x, int y) {
		super(speed,x,y);
		this.destroyedImage = new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/pantalla/gif_explotion.gif"));
		Random rnd = new Random();
		this.rnd= (rnd.nextInt(100))%2;
		this.direccion=rnd.nextInt(4);
		this.shooting=false;
		this.points=0;
	}
	
	public void move() {
	
			switch (direccion) {
			
			case MOVE_UP : 				
				this.position.setLocation(this.position.x, this.position.y - 1);	
				break;
			case MOVE_DOWN :
				this.position.setLocation(this.position.x, this.position.y + 1);
				break;
			case MOVE_LEFT :
				this.position.setLocation(this.position.x - 1, this.position.y);
				break;
			case MOVE_RIGHT :
				this.position.setLocation(this.position.x + 1, this.position.y);
				break;
		}
		
		super.move(direccion);	
		}
	public void girar(){
		Random r= new Random();
		if (rnd==0)       
			this.direccion=(this.direccion+1)%4;  
		else
			{if (direccion == 0 )
			this.direccion=r.nextInt(4);
			else	
			this.direccion-= 1;}	
		super.move(direccion);
		}
	
		public Proyectil shoot(){

			if(shooting==false){
				this.shooting = true;
				if (this.direccion == 0)
					return new Proyectil(direccion,shootSpeed,(int) position.getX() + 10,(int) position.getY(),this);
				if (this.direccion == 1)
					return new Proyectil(direccion,shootSpeed,(int) position.getX() + 23,(int) position.getY() +12,this);
				if (this.direccion == 2)
					return new Proyectil(direccion,shootSpeed,(int) position.getX() + 10,(int) position.getY() + 23,this);
				if (this.direccion == 3)
					return new Proyectil(direccion,shootSpeed,(int) position.getX(),(int) position.getY() + 12,this);
			}
			else 
				return null;	
		return null;
		}
	
	
	public abstract int impact();
	
	public abstract int getPoints();
	
	public void setShooting(boolean value) {
		this.shooting = value;
	}
}
