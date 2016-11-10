package proyectoBC.entities.celdas;

import java.awt.Point;

import javax.swing.ImageIcon;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.obstaculos.Acero;
import proyectoBC.entities.celdas.obstaculos.Agua;
import proyectoBC.entities.celdas.obstaculos.Aguila;
import proyectoBC.entities.celdas.obstaculos.Arbol;
import proyectoBC.entities.celdas.obstaculos.Ladrillo;
import proyectoBC.entities.celdas.obstaculos.Obstaculo;
import proyectoBC.entities.tanques.Tanque;

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
			case "agua":
				this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/agua/agua_1.gif"));
				for (int i=0;i<4;i++){
					this.matriz[i] = new Agua(0,0,0,0);
				};break;
			case "arbol":
				this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/bosque/arbol.gif"));
				for (int i=0;i<4;i++){
					this.matriz[i] = new Arbol(0,0,0,0);
				};break;
			case "acero":
				this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/acero/acero_1.gif"));
				for (int i=0;i<4;i++){
					this.matriz[i] = new Acero(0,0,0,0);
				};break;
			case "aguila":
				this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/aguila/aguila.gif"));
				for (int i=0;i<4;i++){
					this.matriz[i] = new Aguila(0,0,0,0);
				};break;
			default: 
				for (int i=0;i<4;i++){
					this.matriz[i] = null;
				};break;
		}
	}
	
	public void set(String s) {
		switch (s) {
		case "ladrillo": 
			this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/ladrillo/ladrillo_1.gif"));
			this.image.setIcon(this.images[0]);
			this.hp = 4;
			for (int i=0;i<4;i++){
				this.matriz[i] = new Ladrillo(0,0,0,0);
			};break;
		case "acero":
			this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/acero/acero_1.gif"));
			this.image.setIcon(this.images[0]);
			this.hp = 4;
			for (int i=0;i<4;i++){
				this.matriz[i] = new Acero(0,0,0,0);
			};break;
		}
	}

	public int impact(Tanque t) {
		
		if (this.impacton()) {
			int i = 0;
			boolean imp = true;
			while (i<4 && imp){
				if (matriz[i] != null) {
					 if (matriz[i].impact(t) == 0)
						 imp = false;
				}
				i++;
			}
			if (imp) {
				--hp;
				if (hp==0)
					this.initMatriz("vacio");
				return hp;
			}
		}
		return hp;
		
	}
	public boolean movein() {
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
	public boolean impacton() {
		int i = 0;
		boolean tr = true;
		while (i<4 && tr) {
			if (matriz[i] != null) {
				tr = matriz[i].impacton();
			}
			i++;
		}
		return tr;
	}

}