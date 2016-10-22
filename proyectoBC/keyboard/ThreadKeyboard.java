package proyectoBC.keyboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import proyectoBC.engine.GameEngine;

public class ThreadKeyboard extends Thread implements KeyListener{
	
	private GameEngine ge;
	protected boolean pressed, UPpressed,DOWNpressed,LEFTpressed,RIGHTpressed,SPACEpressed,gameOver;
	
	
	public ThreadKeyboard(GameEngine ge){
		UPpressed=DOWNpressed=LEFTpressed=RIGHTpressed=false;
		pressed=false;
		this.ge=ge;
        gameOver=false;
        this.start();
	}
	public void run(){
		 
		 while (!gameOver){
			 try {
                  ThreadKeyboard.sleep(30);
			 }catch (InterruptedException e) {
                 e.printStackTrace();
     		}
			// Movimiento del tanque
			if (UPpressed && !RIGHTpressed && !LEFTpressed)
				ge.movePlayer(KeyEvent.VK_UP);				 
			if (DOWNpressed && !RIGHTpressed && !LEFTpressed)
				ge.movePlayer(KeyEvent.VK_DOWN);
			if (RIGHTpressed && !UPpressed && !DOWNpressed)
				ge.movePlayer(KeyEvent.VK_RIGHT);
			if (LEFTpressed && !UPpressed && !DOWNpressed)
				ge.movePlayer(KeyEvent.VK_LEFT);
			// Agregar lógica de de otras teclas
			if (SPACEpressed)
				ge.shoot();
			
		 }		
	 }

	 public void gameOver(){
        	gameOver=true;
     }
	 public void keyPressed(KeyEvent ev){
		 	int keyCode= ev.getKeyCode();
			switch(keyCode){
	    		case KeyEvent.VK_UP: 
	    			UPpressed=true;	
	    			break;
	    		case KeyEvent.VK_DOWN:
    				DOWNpressed=true;
	    			break;
	    		case KeyEvent.VK_LEFT:
    				LEFTpressed=true;
	    			break;
	    		case KeyEvent.VK_RIGHT:
    				RIGHTpressed=true;
	    			break;
	    		case KeyEvent.VK_SPACE:
	    			SPACEpressed=true;
	    			break;
	    		case KeyEvent.VK_Q:{
	    			ge.levelUp();
	    			break;
	    		}
	    		case KeyEvent.VK_W:{
	    			ge.levelDown();
	    			break;
	    		}
	    		case KeyEvent.VK_B: {
	    			ge.Grenade();
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
