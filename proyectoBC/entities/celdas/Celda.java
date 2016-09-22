package proyectoBC.entities.celdas;

import java.awt.Point;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.obstaculos.Obstaculo;

/**
	 * Clase Celda
	 * 
	 *
	 */

	public class Celda extends Entity {
	    protected Point position;
	    protected int hp;
	    
	    /**
	     * Constructor de la clase Celda.
	     * @param p Posición de la celda en la Matriz.
	     */
	    public  Celda(int speed,int x, int y, int hp) {
	    	super(0,x,y);
	        position = new Point (x,y);
	        this.hp=hp;
	    }
	    public Point getceldaposition (){
	    	 return this.position;
	    }
	    public void setceldaposition(Point p){
	    	position =p;
	    }
	    
	    public int impact() {return this.hp--;}
	    
	    
}