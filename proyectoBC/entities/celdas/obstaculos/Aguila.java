package proyectoBC.entities.celdas.obstaculos;

import javax.swing.ImageIcon;

public class Aguila extends Obstaculo {
	
	public Aguila(int speed,int x, int y, int hp){
		super();
		}
		public boolean  movein (){return false;}
		public int impact() {return 1;}
		public boolean impacton  () {return true;}
}
