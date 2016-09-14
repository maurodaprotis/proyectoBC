package proyectoBC.keyboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import proyectoBC.engine.*;

public class ThreadKeyboard extends Thread implements KeyListener{
	
		private GameEngine ge;
		protected boolean pressed, UPpressed,DOWNpressed,LEFTpressed,RIGHTpressed,SPACEpressed,gameOver;
		
		public ThreadKeyboard(GameEngine ge){
			UPpressed=DOWNpressed=LEFTpressed=RIGHTpressed=false;
			pressed=false;
			this.ge=ge;
            gameOver=false;
		}
		public void listen() {
            start();
		}
		
		 public void run(){
			 
			 while (!gameOver){
				 try {
                      ThreadKeyboard.sleep(30);
				 }catch (InterruptedException e) {
                     e.printStackTrace();
             	}
				if (UPpressed){
					ge.movePlayer(KeyEvent.VK_UP);
					System.out.println("up");
				}
					 
				 if (DOWNpressed)
					 ge.movePlayer(KeyEvent.VK_DOWN);
				 if (RIGHTpressed)
					 ge.movePlayer(KeyEvent.VK_RIGHT);
				 if (LEFTpressed)
					 ge.movePlayer(KeyEvent.VK_LEFT);
			 }
		 }

		 public void gameOver(){
	        	gameOver=true;
	     }
		 
		public void keyPressed(KeyEvent ev){
			System.out.println("keypressed");
			int keyCode= ev.getKeyCode();
			switch(keyCode){
        		case KeyEvent.VK_UP: {
        				System.out.println("up press");
        				pressed= true;
        				UPpressed=true;
        			break;
           		}
        		case KeyEvent.VK_DOWN: {
        			if (!pressed){
        				pressed= true;
        				DOWNpressed=true;
        			}
        			break;
        		}
        		case KeyEvent.VK_LEFT: {
        			if (!pressed){
        				pressed= true;
        				LEFTpressed=true;
        			}
        			break;
        		}
        		case KeyEvent.VK_RIGHT: {
        			if (!pressed){
        				pressed= true;
        				RIGHTpressed=true;
        			}
        			break;
        		}
        		case KeyEvent.VK_SPACE: {
        			SPACEpressed=true;
        			break;
        		}        		
			}
		}
        public void keyReleased(KeyEvent event){
        	System.out.println("keyreleased");
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
            if (UPpressed==false &&  DOWNpressed==false && LEFTpressed==false && RIGHTpressed==false )
            	  pressed=true;
             
           
        }

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
