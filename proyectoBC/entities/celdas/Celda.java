package proyectoBC.entities.celdas;

import java.awt.Point;

import proyectoBC.entities.celdas.obstaculos.Obstaculo;

/**
	 * Clase Celda
	 * 
	 *
	 */

	public class Celda  {
	    protected Point position;
	    protected Obstaculo obstacule [];
	    
	    /**
	     * Constructor de la clase Celda.
	     * @param p Posición de la celda en la Matriz.
	     */
	    public  Celda(int x, int y) {
	        position = new Point (x,y);
	    }
	    
}