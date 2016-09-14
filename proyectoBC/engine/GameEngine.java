package proyectoBC.engine;

import proyectoBC.entities.Entity;
import proyectoBC.entities.tanques.jugadores.TanqueJugador;
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
	private SwingWindow gui;
	private static final int weigth=312;
	private static final int height=312;


	public GameEngine(SwingWindow gui) {
		this.gui = gui;
		System.out.println("Game Engine Creado");
	}
	
	public void movePlayer(int dir){
		int distancia= canMove(player,dir);
		if (distancia > 0)
			player.move(dir,distancia);
	}
	
	private int canMove(Entity entity,int direccion){
		
		Point point= entity.getPosition();
		int pointX= (int) point.getX();
		int pointY= (int) point.getY();
		int distancia=0;
		switch (direccion){
			case KeyEvent.VK_UP: distancia= pointY;
			case KeyEvent.VK_RIGHT: distancia= 312 - (pointX + 24);
			case KeyEvent.VK_LEFT: distancia= pointX;
			case KeyEvent.VK_DOWN: distancia= 312 - (pointY + 24);
		}
		return distancia;	
	}
	
}
