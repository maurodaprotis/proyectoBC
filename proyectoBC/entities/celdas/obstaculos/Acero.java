package proyectoBC.entities.celdas.obstaculos;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Acero extends Obstaculo {
	
	public Acero (int speed,int x, int y, int hp){
		super();
		}
		public boolean  movein (){return false;}
		public int impact() {return 1;}
}
