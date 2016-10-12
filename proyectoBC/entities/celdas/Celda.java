package proyectoBC.entities.celdas;

import java.awt.Point;

import javax.swing.ImageIcon;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.obstaculos.Ladrillo;
import proyectoBC.entities.celdas.obstaculos.Obstaculo;

/**
 * Clase Celda
 * 
 *
 */

public class Celda extends Entity {
	protected int hp;
	protected Obstaculo[] matriz; 

	/**
	 * Constructor de la clase Celda.
	 * 
	 * @param p
	 *            Posición de la celda en la Matriz.
	 */
	public Celda(int x, int y, int hp,String tipo) {
		super(0, x, y);
		this.hp = hp;
		this.matriz = new Obstaculo[4];
		this.initMatriz(tipo);
	}
	private void initMatriz(String tipo){
		switch (tipo) {
			case "ladrillo": 
				this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/ladrillo/ladrillo_1.gif"));
				for (int i=0;i<4;i++){
					this.matriz[i] = new Ladrillo(0,0,0,0);
				};break;
			default: 
				for (int i=0;i<4;i++){
					this.matriz[i] = null;
				};break;
		}
	}

	public Point getceldaposition() {
		return this.position;
	}

	public void setceldaposition(Point p) {
		position = p;
	}

	public int impact() {
		if (this.hp == 0) {
			this.initMatriz("vacio");
		}
		return --hp;
	}
	public boolean movein() {
		//System.out.println("Celda X: "+this.position.getX()+" Y: "+this.position.getY());
		int i = 0;
		boolean tr = true;
		while (i<4 && tr) {
			if (matriz[i] != null) {
				tr = matriz[i].movein();
			}
			i++;
		}
		return tr;			
	}

}