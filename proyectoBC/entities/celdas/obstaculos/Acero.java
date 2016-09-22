package proyectoBC.entities.celdas.obstaculos;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Acero extends Obstaculo {
	
	public Acero (int speed,int x, int y, int hp){
	super (0,x,y,hp);
	this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/acero/acero_1.gif"));
	}
	
	public int impact (){this.hp-=1; return this.hp; }
	public  void  movein (int e){}
}
