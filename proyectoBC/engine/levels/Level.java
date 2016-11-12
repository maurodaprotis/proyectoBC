package proyectoBC.engine.levels;

import proyectoBC.engine.GameEngine;
import java.util.Vector;
import proyectoBC.File.FReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import proyectoBC.entities.tanques.enemigos.*;
import proyectoBC.entities.powerups.*;


public abstract class Level {
	protected GameEngine ge;
	protected Vector<TanqueEnemigo> vEnemies;
	protected int tankCount;
	protected int pwCount;
	protected Vector<PowerUp> vPowerUp;
	protected FReader file;
	
	protected Level (GameEngine ge) {
		this.ge = ge;
		this.tankCount = 16;
		this.pwCount = 4;
		this.vEnemies = new Vector<TanqueEnemigo>();
		this.vPowerUp = new Vector<PowerUp>();
		file= new FReader();
	}
	
	public int leftEnemies() {
		return tankCount;
	}
	
	public abstract TanqueEnemigo getTanque();
	
	public abstract Level upLevel();
}
