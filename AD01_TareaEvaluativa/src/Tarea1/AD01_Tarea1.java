package Tarea1;

//Katerin Sanz 
//AD01_Tarea01
//Escribe un programa en Java que lea una línea de texto desde un archivo y devuelva la misma línea, pero con las palabras invertidas individualmente. Es decir, si el archivo contiene "Hola Mundo", el archivo de salida debería contener "aloH odnuM".

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AD01_Tarea1 {

	public static void main(String[] args) throws IOException {
		mostrarFicheroAlReves();

	}
	
	private static void mostrarFicheroAlReves() throws IOException {
		
		String dir = ("." + File.separator + "src" + File.separator + "Tarea1" + File.separator + "texto.txt");
		String dir2 = ("." + File.separator + "src" + File.separator + "Tarea1" + File.separator + "textoAlReves.txt");
		
		File fil = new File(dir);
		File fil2 = new File(dir2);
		FileReader fr = new FileReader(fil);
		FileWriter fw = new FileWriter(fil2);
		
		int i = 0;
		int letra = 0;
		int fileSize = (int)fil.length();
		char[] letras = new char[fileSize];
		if (!fil.exists()) {
			
            System.out.println("El archivo de entrada no existe: " + dir);
            return;
        }
		
			try {
			
			System.out.println("En el fichero leemos: ");
			
			while((letra = fr.read()) !=-1) {
				
				letras [i] = (char) letra;
				System.out.print(letras[i]);
				i++;
			}
			
			System.out.println();
			System.out.println("El texto al reves es: ");
			
			for (int j = letras.length-1; j>=0; j--) {
				
				fw.write(letras[j]);
				System.out.print(letras[j]);	
			}
		
		} finally {
			
			 if (fr != null) {
	                fr.close();
	            }
	            if (fw != null) {
	                fw.close();
	            }
		}
		
	}
	
}
