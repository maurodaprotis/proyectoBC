package proyectoBC.entities.celdas.obstaculos;

import javax.swing.ImageIcon;

public class Ladrillo extends Obstaculo{

	public Ladrillo (int speed,int x,int y,int hp){
		super(speed,x,y,hp);
		this.images[0]=new ImageIcon(this.getClass().getResource("/proyectoBC/assets/images/obstaculos/ladrillo/ladrillo_1.gif"));
	}
	
	public int impact (){
		if (hp==0) hp=0;
	else
		this.hp--;
		return this.hp;}
	public void  movein (int e){}
}
