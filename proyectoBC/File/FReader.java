package proyectoBC.File;

import java.io.BufferedReader;
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
	      FileReader f = new FileReader(archivo);
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
					System.out.println("["+fila+","+col+","+"Ladrillo]");
				}
				else
					if (c=='a'){
							i++;
							c=cadena.charAt(i);
							if (c!='g')
								if (c=='c'){
									i=i+3;
									obstaculos[fila][col]="acero";
									System.out.println("["+fila+","+col+","+"Acero]");
								}
								else{
									i=i+3;
									obstaculos[fila][col]="arbol";
									System.out.println("["+fila+","+col+","+"Arbol]");
								}
							else
							{
								i++; i++;
								c=cadena.charAt(i);
								if (c=='a')
								{
									obstaculos[fila][col]="agua";
									System.out.println("["+fila+","+col+","+"Agua]");
								}
								else{
									i=i+2;
									obstaculos[fila][col]="aguila";
									System.out.println("["+fila+","+col+","+"Aguila]");
								}
							}
					}
					else 
						if (c=='n'){
							i=i+3;
							obstaculos[fila][col]="nada";
							System.out.println("["+fila+","+col+","+"Vacio]");
						}
				
			}
					
		}
	}
	
	public static void main(String[]args){
		try {
			new FReader().getObstaculos("/home/diego/GitHub/proyectoBC/proyectoBC/File/Mapa1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
