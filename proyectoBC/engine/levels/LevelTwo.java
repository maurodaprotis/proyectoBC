package proyectoBC.engine.levels;

import java.io.FileNotFoundException;
import java.io.IOException;

import proyectoBC.engine.GameEngine;
import proyectoBC.entities.celdas.Celda;
import proyectoBC.entities.powerups.*;
import proyectoBC.entities.tanques.enemigos.TanqueBasico;
import proyectoBC.entities.tanques.enemigos.TanqueBlindado;
import proyectoBC.entities.tanques.enemigos.TanqueEnemigo;
import proyectoBC.entities.tanques.enemigos.TanquePoder;
import proyectoBC.entities.tanques.enemigos.TanqueRapido;

public class LevelTwo extends Level{
	
	public LevelTwo(GameEngine ge) {
		super(ge);
		initCeldas();
		initEnemies();
		initPowerUps();
	}
	
	public TanqueEnemigo getTanque() {
		if (tankCount != 0) {
			tankCount--;
			switch (tankCount) {
			case 11: ge.addPowerUp(this.getPowerUp());break;
			case 9: ge.addPowerUp(this.getPowerUp());break;
			case 5: ge.addPowerUp(this.getPowerUp());break;
			case 3: ge.addPowerUp(this.getPowerUp());break;
			}
			return vEnemies.get(tankCount);
		}
		return null;
	}
	private PowerUp getPowerUp() {
		if (pwCount != 0) {
			pwCount --;
			return vPowerUp.get(pwCount);
		}
		return null;
	}
	
	private void initPowerUps() {
		this.vPowerUp.add(new Star(45,100));
		this.vPowerUp.add(new Shield(100,45));
		this.vPowerUp.add(new Grenade(200,80));
		this.vPowerUp.add(new Timer(250,250));
	}
	
	private void initEnemies() {
		int m;
		for (int i = 0; i < 16; i++) {
			m = i % 4;
			if(m==0)
				this.vEnemies.add(new TanquePoder(3,(m*50),0)); 
			if(m==1)
				this.vEnemies.add(new TanqueBlindado (2,(m*50),0)); 
			if(m==2)	
				this.vEnemies.add(new TanqueRapido (4,(m*50),0)); 
			if(m==3)	
				this.vEnemies.add(new TanqueBasico (2,(m*50),0));
		}
	}
	
	private void initCeldas() {
		try {
			String [][] obs= file.getObstaculos("Map2");
			String o;
			Celda c=null;
			int posX=0;
			int posY=0;
			for(int i=0;i<obs[0].length;i++){
				for(int j=0;j<obs.length;j++){
					o=obs[i][j];
					if (j!=0){
						posX=posX + 24;
					}
					if (o=="ladrillo" || o=="acero" || o=="arbol" || o=="agua" || o=="aguila"){
						c = new Celda(posX,posY,4,o);
						ge.addCelda(c);
					}
				}
				posY=posY+24;
				posX=0;
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Level upLevel(){
		return new LevelThree(ge);
	}
}
