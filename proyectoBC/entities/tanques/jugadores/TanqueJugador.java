package proyectoBC.entities.tanques.jugadores;

import proyectoBC.engine.GameEngine;
import proyectoBC.entities.tanques.Tanque;

public class TanqueJugador extends Tanque {
	protected int level;
	protected boolean shield;
	protected int lives;
	protected GameEngine ge;
	protected String[] imagenes;
	
	public TanqueJugador(int x,int y,GameEngine ge){
		this.x = x;
		this.y = y;
		this.ge = ge;
		this.shield = false;
		this.lives = 4;
		this.imagenes = new String[4];
		this.imagenes[0] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24.png");
		this.imagenes[1] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24der.png");
		this.imagenes[2] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24inv.png");
		this.imagenes[3] = new String("/proyectoBC/assets/images/tanques/jugador/0_24x24izq.png");
		
		System.out.println("Tanque jugador Creado");
	}
	
	public String[] getImages() {
		return this.imagenes;}
		
	public String getImage(int i) {
			return this.imagenes[i];
		
								}  
	public void move(int d){
		x+=d;
		y+=d;
	    setX(x);
        setY(y);
        
             
}

public void levelup(){
	this.level+=1; 
	if (this.level==2){
		sethp(1);
    	 setmoveSpeed(3);
    	 setshootSpeed(2);
	     //falta disparos simultaneos sin ds
	}
	 else{
	      if (this.level==3){
	    	  	sethp(2);
		    	 setmoveSpeed(2);
		    	 setshootSpeed(2);
		     //falta disparos simultaneos ds=2
	      }
	      else{
		     if (this.level==4){
		    	 sethp(4);
		    	 setmoveSpeed(2);
		    	 setshootSpeed(3);
			     //falta disparos simultaneos ds=3
                 }
	         }
		}
	 }


public void impact(){
	this.lives = getLives()-1;
	setLevel(1);
	sethp(1);
	setmoveSpeed(2);
	setshootSpeed(1);
	  
}

public void shoot(){
		
}

public void shieldUp(){
	setshield(true);
	
	
	
}

public int getLives (){ return lives;}

public void setLevel(int l){level=l;}

public void setshield(boolean b){ shield=b;}

	
	
	
	
	
	
}
