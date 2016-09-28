package proyectoBC.engine;

import proyectoBC.entities.Entity;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.celdas.obstaculos.Ladrillo;
import proyectoBC.entities.tanques.enemigos.TanqueBasico;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;
import proyectoBC.keyboard.ThreadKeyboard;
import proyectoBC.GUI.SwingWindow;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class GameEngine {
	
	private TanqueJugador player;
	private TanqueBasico enemigoBasico;
	private ThreadKeyboard keyboard;
	private Celda[][] matrix;
	private Celda celda;
	private SwingWindow gui;
	private ImageIcon ladrillo;
	private ImageIcon aguila;
	private ImageIcon acero;
	
	private int score;
	
	
	private static final int width=24;
	private static final int height=24;
	private static final int max_X=312;
	private static final int max_Y=312;


	public GameEngine(SwingWindow gui) {
		this.gui = gui;
		this.matrix = new Celda[13][13];
		this.initMatrix();
		// Creo el jugador y lo agrego el grafico a la gui.
		this.player = new TanqueJugador(3,96,288);
		gui.getContentPane().add(this.player.getImage());
		System.out.println("Game Engine Creado");
		ladrillo= new ImageIcon((this.getClass().getResource("/proyectoBC/assets/images/obstaculos/ladrillo/ladrillo_1.gif")));
		aguila= new ImageIcon((this.getClass().getResource("/proyectoBC/assets/images/obstaculos/aguila/aguila.gif")));
		acero= new ImageIcon((this.getClass().getResource("/proyectoBC/assets/images/obstaculos/acero/acero_1.gif")));
		gui.setScore(0000);
		
		//addmaplevel1(gui);
		
		
	}
	
	private void initMatrix(){
		for (int i = 0; i < 13; i++) {
			for (int j = 0;j < 13; j++) {
				this.matrix[i][j] = new Celda(i*width,j*height,0,"vacio");
			}
		}
	}
	
	
	
	public void movePlayer(int dir){
		int distancia = canMove(player,dir);
		System.out.println(distancia);
		player.move(dir,distancia);
	}
	
	private int canMove(Entity entity,int direccion){
		
		Point point= entity.getPosition();
		int pointX= (int) point.getX();
		int pointY= (int) point.getY();
		int distancia = 0;
		switch (direccion){
			case KeyEvent.VK_UP: {
				int d = 0;
				boolean multiplo = pointX % width == 0;
				Celda col1 = matrix[pointX / width][(pointY / height) - d ];
				Celda col2 = col1;
				if (!multiplo) {
					col2 = matrix[(pointX / width) + 1][(pointY / height) - d ];
				}
				if (pointY < height) distancia = pointY;
				while (col1.movein() && col2.movein() && (pointY / height) - d > 0) {
					distancia += height;
					d++;
					col1 = matrix[pointX / width][(pointY / height) - d ];
					col2 = col1;
					if (!multiplo) {
						col2 = matrix[(pointX / width) + 1][(pointY / height) - d ];
					}
				}
			}break;
			case KeyEvent.VK_RIGHT: {
				int d = 0;
				boolean multiplo = pointY % height == 0;
				Celda col1 = matrix[((pointX+width) / width) + d][pointY / height];
				Celda col2 = col1;
				if (!multiplo) {
					col2 = matrix[((pointX+width)/ width) + d][pointY / height + 1 ];
				}
				if (max_X - pointX + width < width) distancia = max_X - pointX ; // ERROR ACA no funciona bien
				while (col1.movein() && col2.movein() && ((pointX+width) / width) + d < 12) {
					distancia += width;
					d++;
					col1 = matrix[((pointX+width) / width) + d][pointY / height];
					col2 = col1;
					if (!multiplo) {
						col2 = matrix[((pointX+width) / width) + d][pointY / height + 1 ];
					}
				}
			}break;
			case KeyEvent.VK_LEFT: {
				int d = 0;
				boolean multiplo = pointY % height == 0;
				Celda col1 = matrix[(pointX / width) - d][pointY / height];
				Celda col2 = col1;
				if (!multiplo) {
					col2 = matrix[(pointX / width) - d][pointY / height + 1 ];
				}
				if (pointX < width) distancia = pointX;
				while (col1.movein() && col2.movein() && (pointX / width) - d > 0) {
					distancia += width;
					d++;
					col1 = matrix[(pointX / width) - d][pointY / height];
					col2 = col1;
					if (!multiplo) {
						col2 = matrix[(pointX / width) - d][pointY / height + 1 ];
					}
				}
			}break;
			case KeyEvent.VK_DOWN: {
				int d = 0;
				boolean multiplo = pointX % width == 0;
				Celda col1 = matrix[pointX / width][(pointY / height) + d ];
				Celda col2 = col1;
				if (!multiplo) {
					col2 = matrix[(pointX / width) + 1][(pointY / height) + d ];
				}
				if (pointY < height) distancia = pointY;  // ERROR ACA no funciona bien
				while (col1.movein() && col2.movein() && (pointY / height) + d < 12) {
					distancia += height;
					d++;
					col1 = matrix[pointX / width][(pointY / height) + d ];
					col2 = col1;
					if (!multiplo) {
						col2 = matrix[(pointX / width) + 1][(pointY / height) + d ];
					}
				}
			}break;
		}
		return distancia;	
	}	
	/**
	 * Armado del mapa nivel 1
	 * @param gui
	 */
	
	private void addmaplevel1(SwingWindow gui){
		
		for(int f=0;f<13;f++){
	        for(int c=0;c<13;c++){
	        	  if(aguila (f,c)){
		            	JLabel labelPared=new JLabel (aguila);
		        		gui.add(labelPared);
		        		labelPared.setBounds(f*24, c*24, 24,24);	
		            				}
	        	if(blocks1 (f,c)){
	       			 JLabel labelPared=new JLabel (ladrillo);
			        		gui.add(labelPared);
			        		labelPared.setBounds(f*24, c*24, 24,24);	
			        		     }  
	        	if(blocks2 (f,c)){
	       			 JLabel labelPared=new JLabel (ladrillo);
			        		gui.add(labelPared);
			        		labelPared.setBounds(f*24, c*24, 24,24);	
	        					}	
	        	if(blocks3 (f,c)){
	       			 JLabel labelPared=new JLabel (ladrillo);
			        		gui.add(labelPared);
			        		labelPared.setBounds(f*24, c*24, 24,24);	
	        					}	
	        	if(blocks4 (f,c)){
	       			 JLabel labelPared=new JLabel (acero);
			        		gui.add(labelPared);
			        		labelPared.setBounds(f*24, c*24, 24,24);	
	        					}	
	        	
             }  	    
	       } 
		 }	
     
	
	// Distribicion de bloques en el tablero (nivel 1).

	private boolean blocks1 (int f , int c){
	 boolean  distribution = ((f%2==1 && c!=0 && c!=10 && (c !=3 && c!=4 && c!=5 && c!=6 && c!=12  )));
	 return   distribution; 
	}
	private boolean blocks2 (int f , int c){
		 boolean  distribution = ((c==12 && (f==5 || f==7)) || (c==11 && (f==6 ) ));
		 return   distribution; 
	}
	private boolean blocks3 (int f , int c){
		 boolean  distribution = ((c==8 && f==6) ||(c==6 && (f==5 || f==7)) ||(c==4 && (f==5 || f==7))   );
		 return   distribution; 
	}	
	
	private boolean blocks4 (int f , int c){
		 boolean  distribution = (((c==5 && (f==0 || f==2 ||f==3 ||f==9 || f==10 ||f==12)) || (c==2 && f==6)));
		 return   distribution; 
	}	
	private boolean aguila (int f , int c){
			 boolean  distribution = (c==12 && f==6);
			 return   distribution; 
    }		
	
	
	public void agregarTanque(){
		if (enemigoBasico==null){
			enemigoBasico= new TanqueBasico(1,0,0);
			System.out.println("Agrego tanque enemigo");
			gui.getContentPane().add(enemigoBasico.getImage());			
		}
		else
			destruirTanque();
		gui.repaint();
	}
	
	private void destruirTanque(){
		System.out.println("Destruyo tanque enemigo");
		if (enemigoBasico.impact()==0){
			gui.remove(enemigoBasico.getImage());
			score= score + enemigoBasico.getPoints();
			enemigoBasico=null;
			gui.setScore(score);
		}
	}
	
	public void levelUp(){
		this.player.levelUp();
		gui.repaint();
	}
	public void levelDown(){
		this.player.resetLevel();
		gui.repaint();
	}
	
	public void addWall(){
		if (celda==null){
		celda = new Celda(144,144,4,"ladrillo");
		this.matrix[6][6] = celda;
		System.out.println("Agrego pared");
		gui.getContentPane().add(celda.getImage());		}	
		else 
		destroyWall();	
		gui.repaint();
	}
	
	public void destroyWall (){	
		System.out.println("Se destruyo pared");
		if (celda.impact()==0 ){
		System.out.println("Pared Removida");	
		gui.remove(celda.getImage());
		celda = null;
		gui.repaint();
		}

		
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	

	
	
	

