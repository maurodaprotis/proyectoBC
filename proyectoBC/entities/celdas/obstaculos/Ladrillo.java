package proyectoBC.entities.celdas.obstaculos;

import javax.swing.ImageIcon;

public class Ladrillo extends Obstaculo{

	public Ladrillo (int speed,int x,int y,int hp){
		super();
	}
	public boolean  movein (){return false;}
	public int impact() {return 1;}
}
