package proyectoBC.entities.celdas.obstaculos;

import javax.swing.ImageIcon;

public class Arbol extends Obstaculo {

	public Arbol (int speed,int x, int y, int hp){
		super();
		}
		public boolean  movein (){return true;}
		public int impact() {return 1;}
		public boolean impacton  () {return false;}
}
