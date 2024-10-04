package Tarea4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*Desde la editorial Marvel te han contratado para hacer una aplicación que gestione los datos de sus superhéroes y supervillanos. 
 * Para almacenar la información han decidido utilizar ficheros de distintas clases.
a). Realiza un programa en java para guardar datos de personajes en un fichero aleatorio, dale el nombre Marvel.dat. 
Introduce la información de los personajes a partir de los arrays que se te proporcionan en la plataforma Moodle. 
Cuando termine de realizar la carga de datos deberá informar al usuario de que la carga se ha realizado satisfactoriamente o no. 

Katerin Sanz
AD01_Tarea04a*/

public class Tarea4a {

	public static void main(String[] args) throws IOException{
		
		
		String dir = ("." + File.separator + "src" + File.separator + "Tarea4" + File.separator + "Marvel.dat");	
		File fil = new File(dir);
		
		RandomAccessFile raf = new RandomAccessFile(fil,"rw");
		
		int [] ids= {1, 2, 3, 4, 5, 6, 7};
		String[] dnis= {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
		String[] noms= {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
		String[] identidades = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
		String[] tipos = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
		int[] pesos = {76,84,66,136,78,102,70};
		int[] alturas = {178,183,156,152,177,182,188};
		
		int pini = ids.length;
		int pos = 0;
		int longitud = 110;// longitud de cada registro en bytes
		
		StringBuffer bufferDni = null;
		StringBuffer bufferNoms = null;
		StringBuffer bufferId = null;
		StringBuffer bufferTipo = null;
	
		
		try {
			
			for (int i = 0; i<pini; i++) {
				pos=i*longitud;
				raf.seek(pos);
			
				raf.writeInt(ids[i]);// insertar id
			
				bufferDni = new StringBuffer (dnis[i]);
				bufferDni.setLength(9);//9 caracteres DNI
				raf.writeChars(bufferDni.toString());//insertar DNI
			
				bufferNoms = new StringBuffer (noms[i]);
				bufferNoms.setLength(10);//10 caracteres Nombre
				raf.writeChars(bufferNoms.toString());//insertar Nombre
			
				bufferId = new StringBuffer (identidades[i]);
				bufferId.setLength(20);//20 caracteres identidades
				raf.writeChars(bufferId.toString());//insertar identidad
			
				bufferTipo = new StringBuffer (tipos[i]);
				bufferTipo.setLength(10);//10 caracteres tipo
				raf.writeChars(bufferTipo.toString());//insertar tipo
			
				raf.writeInt(pesos[i]);//insertar peso
				raf.writeInt(alturas[i]);//insertar altura
			
				}
			if (pos != (longitud * (pini-1))) {
					System.out.print("La carga de los personajes no se ha realizado");
				} else {
					System.out.print("La carga de los personajes ha terminado correctamente");
				}
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
				
		raf.close();
	}
}
