package Tarea2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Crea un programa en Java que lea un archivo con nombres y apellidos separados por espacios, y escriba en un nuevo archivo solo los nombres que tienen exactamente cinco letras.
//Katerin Sanz
//Ad01_Tarea02

public class Tarea2 {

	public static void main(String[] args)  throws IOException {
		escribirNombres5();
	}
	
	
	private static void escribirNombres5(){
		
		String dir = ("." + File.separator + "src" + File.separator + "Tarea2" + File.separator + "nombres.txt");
		String dir2 = ("." + File.separator + "src" + File.separator + "Tarea2" + File.separator + "nombres5Letras.txt");
		
		File fil = new File(dir);
		File fil2 = new File(dir2);
		String linea;
		String nombre;
		
		if (!fil.exists()) {
			System.out.println("El archivo de entrada no existe: " + dir);
		    return;
		}
		
		try {
			
			BufferedReader br = new BufferedReader (new FileReader(fil));
			BufferedWriter bw = new BufferedWriter (new FileWriter(fil2));
			
			System.out.println("Los nombres que tienen 5 letras son: ");
			
			while ((linea = br.readLine())!=null) {
				
				String[] palabras = linea.split(" ");
				nombre = palabras[0];
				
				if(nombre.length()==5) {
					
					bw.write(nombre);
					bw.newLine();
					System.out.println(nombre);
					
				}
				
			}
			
			if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
		
		} catch (IOException e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
	}
}
