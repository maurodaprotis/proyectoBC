package proyectoBC.engine;

import java.util.Random;
import java.util.Vector;
import proyectoBC.entities.tanques.enemigos.TanqueEnemigo;

	public class ThreadTanqueEnemigo extends Thread {
		
	// Logica que implementa al tanque enemigo.
		private TanqueEnemigo enemy;
		private Vector<TanqueEnemigo> venemy;
		private GameEngine ge;
		
		
		// Flag que indica cuando debe detenerse la ejecución del hilo.
		// Es volatile porque es accedida desde concurrentemente desde diferentes threads.
		private volatile boolean detener;
	// agregar ThreadBullet y gui.
		public ThreadTanqueEnemigo (Vector<TanqueEnemigo> venemy,GameEngine ge) {
			this.ge=ge;
			this.venemy=venemy;
			this.enemy=enemy;
			this.detener = false;
			this.start();
		}
		
		@Override
		public void run() {
			// Ejecuto indefinidamente hasta que el flag sea verdadero.
			while(!this.detener){
				// Duermo el hilo 1 segundo.
				// De esta manera cada turno se ejecuta cada 1 segundo.
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) { }
	
			for (int i=0;i<venemy.size();i++){
				TanqueEnemigo enemy =venemy.get(i);
				int move;
				for (int j=0;j<enemy.getSpeed();j++){
					if(enemy.getDireccion()==3){		
						move= this.ge.canMove(enemy, 38-1);
						Random r = new Random();
						if (move==0 || r.nextInt(313)==enemy.getPosition().x || r.nextInt(140)==r.nextInt(200) || r.nextInt(240)==enemy.getPosition().y)
					    enemy.girar();
						else
						enemy.move();}
					else{
						if(enemy.getDireccion()==2){	
						move= this.ge.canMove(enemy, 38+2);
						Random r = new Random();
						if (move==0 || r.nextInt(313)==move|| r.nextInt(313)==r.nextInt(250) ||r.nextInt(250)==r.nextInt(250) || r.nextInt(140)==r.nextInt(140))
					    enemy.girar();
						else
						enemy.move();}
						
					else
						if(enemy.getDireccion()==1){	
						move= this.ge.canMove(enemy, 38+1);
						Random r = new Random();
						if (move==0 || r.nextInt(313)==75|| r.nextInt(313)==156 || r.nextInt(200)==r.nextInt(200)||r.nextInt(140)==r.nextInt(140))					    
						enemy.girar();
						else
						enemy.move();}
						
					else{
						if(enemy.getDireccion()==0){		
						move= this.ge.canMove(enemy, 38);
						Random r = new Random();
						if (move==0 || r.nextInt(313)==75|| r.nextInt(313)==156 || r.nextInt(140)==enemy.getPosition().y || enemy.getPosition().x==156  )
					    enemy.girar();
						else
						enemy.move();
							}
						}			
					}
				}    		    	 
			}
		}
	}
	
	    public void continuar(){
	    	this.detener=false;
	    	this.run();
	    	this.start();	
	    }	
		
	    public void detener() {
			// Interrumpo el hilo para que no continue con su ejecución.
			this.interrupt(); 
			// Seteamos el flag para detener su ejecución.
			this.detener = true;
		}		
}

