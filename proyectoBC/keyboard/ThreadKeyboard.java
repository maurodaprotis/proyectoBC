package proyectoBC.keyboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import proyectoBC.engine.*;

public class ThreadKeyboard extends Thread implements KeyListener{
	
		private GameEngine tablero;
		protected boolean UPpressed,DOWNpressed,LEFTpressed,RIGHTpressed,SPACEpressed,gameOver;
	
		public ThreadKeyboard(GameEngine tablero){
			this.tablero=tablero;
            gameOver=false;
            start();
		}
		
		 public void run(){
			 while (!gameOver){
				 try {
                      ThreadKeyboard.sleep(30);
				 }catch (InterruptedException e) {
                     e.printStackTrace();
             	}	
				if (UPpressed)
					 tablero.movePlayer(0);
				 if (DOWNpressed)
					 tablero.movePlayer(2);
				 if (RIGHTpressed)
					 tablero.movePlayer(1);
				 if (LEFTpressed)
					 tablero.movePlayer(3);
			 }
		 }

		 public void gameOver(){
	        	gameOver=true;
	     }
		 
		public void keyPressed(KeyEvent ev){
			int keyCode= ev.getKeyCode();
			switch(keyCode){
        		case KeyEvent.VK_UP: {
        			UPpressed=true;
        			break;
        		}
        		case KeyEvent.VK_DOWN: {
        			DOWNpressed=true;
        			break;
        		}
        		case KeyEvent.VK_LEFT: {
        			LEFTpressed=true;
        			break;
        		}
        		case KeyEvent.VK_RIGHT: {
        			RIGHTpressed=true;
        			break;
        		}
        		case KeyEvent.VK_SPACE: {
        			SPACEpressed=true;
        			break;
        		}        		
			}
		}
        public void keyReleased(KeyEvent event){  
        	int keyCode= event.getKeyCode();
            switch(keyCode){
                    case KeyEvent.VK_UP: {
                            UPpressed=false;break;
                    }
                    case KeyEvent.VK_DOWN: {
                            DOWNpressed=false;break;
                    }
                    case KeyEvent.VK_LEFT: {
                            LEFTpressed=false;break;
                    }
                    case KeyEvent.VK_RIGHT: {
                            RIGHTpressed=false;break;
                    }
                    case KeyEvent.VK_SPACE: {
                            SPACEpressed=false;break;
                    }
            }
        }

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
