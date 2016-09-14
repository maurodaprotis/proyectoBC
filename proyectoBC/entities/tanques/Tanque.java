package proyectoBC.entities.tanques;

public abstract class Tanque {
	protected int x;
	protected int y;
	protected int hp;
	protected int moveSpeed;
	protected int shootSpeed;
	protected int direction; //0 is up, 1 is right, 2 is down 3 is left;
	
	public void move(int d){
		switch(direction) {
			case 0: this.x -= d;break;
			case 1: this.y += d;break;
			case 2: this.x += d;break;
			case 3: this.y -= d;break;
		}
	}
	
	

public abstract void shoot();
	
public abstract void impact();
	
 public int getdireccion(){ return direction;}	

 public void setX (int x) {this.x=x;}

 public int getX(){ return x;	}
	
 public int getY(){ return y;   }

 public void setY (int y) {this.y=y;}
 
 public int getmovespeed(){ return moveSpeed;}

 public void setmoveSpeed(int ms){moveSpeed=ms;}

 public int getshootspeed(){ return shootSpeed;}

 public void setshootSpeed(int ss){shootSpeed=ss;}

 public int gethp(){return hp;}

 public void sethp(int hp){this.hp=hp;}
	
}

	




