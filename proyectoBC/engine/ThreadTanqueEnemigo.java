package proyectoBC.engine;

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
		
		public ThreadTanqueEnemigo (Vector<TanqueEnemigo> venemy,GameEngine ge) {
			this.ge=ge;
			this.venemy=venemy;
			this.enemy=enemy;
			this.detener = false;
		}
		
		@Override
		public void run() {
			// Ejecuto indefinidamente hasta que el flag sea verdadero.
			while(!this.detener){
				// Duermo el hilo 1 segundo.
				// De esta manera cada turno se ejecuta cada 1 segundo.
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) { }
	
			for (int i=0;i<venemy.size();i++){
				TanqueEnemigo enemy =venemy.get(i);
				for (int j=0;j<enemy.getSpeed();j++){
					int move = this.ge.canMove(enemy, enemy.getDireccion());
				    if (move==0)
				    enemy.girar();
				    else
				    enemy.move();	
					}
			}    		    	 
		}
	}
		
		public void detener() {
			// Interrumpo el hilo para que no continue con su ejecución.
			this.interrupt(); 
			// Seteamos el flag para detener su ejecución.
			this.detener = true;
		}		
}

	