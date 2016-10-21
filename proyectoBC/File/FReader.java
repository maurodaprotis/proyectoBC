package proyectoBC.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FReader {
	private String[][] obstaculos;
	
	public FReader(){
		obstaculos= new String[13][13];
	}
		
	public String [][] getObstaculos(String archivo) throws FileNotFoundException, IOException {
	      String cadena;
	      File file = new File(archivo);
	      FileReader f = new FileReader(file.getAbsolutePath());
	      BufferedReader b = new BufferedReader(f);
	      int fila=0;
	      while((cadena = b.readLine())!=null) {
	          procesarLinea(cadena,fila);
	          fila++;
	      }
	      b.close();
	      return obstaculos;
	}
	
	void procesarLinea(String cadena,int fila){
		char c;
		int col=0;
		for (int i=0;i<cadena.length();i++){
			c=cadena.charAt(i);
			if (c==']'){
				col++;
			}
			else{
				if (c=='l'){
					i=i+7;
					obstaculos[fila][col]="ladrillo";
				}
				else
					if (c=='a'){
							i++;
							c=cadena.charAt(i);
							if (c!='g')
								if (c=='c'){
									i=i+3;
									obstaculos[fila][col]="acero";
								}
								else{
									i=i+3;
									obstaculos[fila][col]="arbol";
								}
							else
							{
								i++; i++;
								c=cadena.charAt(i);
								if (c=='a')
								{
									obstaculos[fila][col]="agua";
								}
								else{
									i=i+2;
									obstaculos[fila][col]="aguila";
								}
							}
					}
					else
						if (c=='v')
						{
							i=i+4;
							obstaculos[fila][col]="vacio";
						}
			}
					
		}
	}
	
	
}
