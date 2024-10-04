package Tarea4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*b). Desde la editorial quieren tener controlado el peso de sus personajes, ya que últimamente han hecho algún exceso que otro. Realiza un programa en java que te permita modificar los datos de un personaje.
 *  El programa recibe desde la línea de comandos el dni y el peso del último mes. Si el personaje no existe devolverá un mensaje de error, sino mostrará en la consola el nombre del personaje y cuantos kilos
 *   ha engordado, adelgazado o si se mantiene en su peso.
 *   
 *   Katerin Sanz	
 *   AD01_Tarea04b*/

public class Tarea4b {
	public static void main(String[] args) throws IOException {
		try {
			String dir = ("." + File.separator + "src" + File.separator + "Tarea4" + File.separator + "Marvel.dat");	
			File fil = new File(dir);
			//Declcara fichero de acceso aleatorio
			RandomAccessFile raf = new RandomAccessFile (fil, "rw");
			
			String dniConsola, dniFichero, nombreFichero;
			int pesoConsola, pesoFichero;
			boolean siDni = false;
			final int longitud = 110;// Longitud en Bytes de cada objeto
			
			//Pedir por consola peso y DNI
			System.out.println("Introduzca el DNI del personaje:");
			dniConsola = Consola.readString();
			
			//Recorremos los datos para comparar Dni
			for (int p = 0; p < fil.length(); p+=longitud) {
				//Nos situamos para leer dni
				raf.seek(p);
				raf.skipBytes(4);
				
				//Paso el DNI a un array de char y lo combierto a String para poder compararlo
				char[] auxDni=new char[9];
				for (int i =0; i<9;i++) {
					auxDni [i] = raf.readChar();
					}
				dniFichero = new String (auxDni);
				
				//Realizamos los mismos pasos con el nombre
				char[] auxNom =new char[10];
				for (int i =0; i<10;i++){
					auxNom [i] = raf.readChar();
					}
				nombreFichero = new String (auxNom);
				
				// Buscamos peso anterior en fichero
				raf.skipBytes(60);
				pesoFichero = raf.readInt();
				
				//Comparamos DNI
				if (dniFichero.equalsIgnoreCase(dniConsola)) {
					siDni = true;
					//Comparamos peso introducido con el que tenemos en fichero
					System.out.println("Introduzca el peso del personaje del último mes:");
					pesoConsola = Consola.readInt();					
					if ( pesoConsola < pesoFichero ) {
						int pesoDif = pesoFichero - pesoConsola;
						System.out.println( nombreFichero +" ha adelgazado "+pesoDif+" kilos.");
						} else if ( pesoConsola > pesoFichero ) {
							int pesoDif = pesoConsola - pesoFichero;
							System.out.println( nombreFichero +" ha engordado "+pesoDif+" kilos.");
							} else if (pesoConsola == pesoFichero){
								System.out.println( nombreFichero +" tiene el mismo peso.");
								} 
					}	
				
			} 
		if (!siDni) {
				//Si no existe personaje manda mensaje
			System.out.println("El personaje no existe.");
			} 
			
		raf.close();
		
		} catch (FileNotFoundException e){}
	}
}

