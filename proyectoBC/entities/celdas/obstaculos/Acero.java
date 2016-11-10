package proyectoBC.entities.celdas.obstaculos;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import proyectoBC.entities.tanques.Tanque;

public class Acero extends Obstaculo {
	
	public Acero (int speed,int x, int y, int hp){
		super();
		}
		public boolean  movein (){return false;}
		public int impact(Tanque t){
			if (t.getLevel()!=null)
				if (t.getLevel().getNivel() == 4)
					return 1;
				else
					return 0;
			return 0;
		}
		public boolean impacton () {
			return true;
		}
}
