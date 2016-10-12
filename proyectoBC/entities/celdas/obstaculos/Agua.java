package proyectoBC.entities.celdas.obstaculos;

import javax.swing.ImageIcon;

public class Agua extends Obstaculo {

	public Agua (int speed,int x, int y, int hp){
		super();
		}
		public boolean  movein (){return false;}
		public int impact() {return 1;}
}
