package Tarea4;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*c). Realiza un programa en Java que te permita visualizar los personajes de un tipo concreto (héroe o villano).
 *  El programa recibe desde la línea de comandos el tipo de personaje y visualiza cuantos personajes hay de dicho tipo y 
 *  todos los datos de dichos personajes. Verifica que exista dicho tipo en el fichero, si no existe saca un mensaje de error por pantalla
 *  
 *  Katerin Sanz
 *  AD01_Tarea4c*/

public class Tarea4c {
	public static void main(String[] args) {
		try {
			String dir = ("." + File.separator + "src" + File.separator + "Tarea4" + File.separator + "Marvel.dat");	
			File fil = new File(dir);
			//Declcara fichero de acceso aleatorio
			RandomAccessFile raf = new RandomAccessFile (fil, "rw");
			
			final int longitud = 110;
			String tipoConsola, tipoFichero, dni, nombre, identidad;
			int peso, altura, contador = 0;
			Boolean siTipo = false;
			
			System.out.println("Elige un tipo de personaje: heroe o villano ");
			tipoConsola = Consola.readString();
			
			//Recorremos el fichero
			for (int p = 0; p < fil.length(); p+=longitud) {
				//Nos posicionamos en el dato que queremos en este caso el tipo.
				raf.seek(p);
				raf.skipBytes(82);
				//Creamos un array de caracteres que recorreremos y pasaremos a String para poder comparar			
				char[] auxTipo=new char[10];
				for(int i=0;i<10;i++)
				{
					auxTipo[i]=raf.readChar();
				}
				
				tipoFichero = new String(auxTipo);
				
				//Comparoel tipo del fichero con el que hemos introducido
				if (tipoFichero.trim().equalsIgnoreCase(tipoConsola)){
					siTipo = true;
					raf.seek(p);
					raf.skipBytes(4);
					
					//Recogemos el DNI
					char[] auxDni =new char[9];
					for(int i=0;i<9;i++)
					{
						auxDni[i]=raf.readChar();
					}
					dni = new String(auxDni);
					
					//Recogemos el nombre
					char[] auxNom =new char[10];
					for(int i=0;i<10;i++)
					{
						auxNom[i]=raf.readChar();
					}
					nombre = new String(auxNom);
				
					//Recogemos la identidad secreta
					char[] auxIden =new char[20];
					for(int i=0;i<20;i++)
					{
						auxIden[i]=raf.readChar();
					}
					identidad = new String(auxIden);
					
					//Saltamos el DNI porque ya lo he recogido
					raf.skipBytes(20);
					//Recogemos el peso
					peso = raf.readInt();
					//Recogemos la altura
					altura = raf.readInt();
				
					//Imprimimos los datos por consola
					
					System.out.println("\tDNI:" + dni);
					System.out.println("\tNombre:" + nombre);
					System.out.println("\tIdentidad Secreta:" + identidad);
					System.out.println("\tTipo:" + tipoFichero);
					System.out.println("\tPeso:" + peso + " kilos.");
					System.out.println("\tAltura:" + altura + " cms.");
					System.out.println();
					
					//sumo personajes al contador
					contador = contador + 1;
				} 
		}	if(! siTipo) {
			System.out.println("No existe "+ tipoConsola+ " en el fichero.");
		} else {
			System.out.println("Hay " + contador + " personajes del tipo " + tipoConsola + ".");
			}
		
		raf.close();
			
		}catch(IOException e) {
			System.out.println("Error: " + e);
		}

	}
}
