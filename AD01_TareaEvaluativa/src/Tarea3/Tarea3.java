package Tarea3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.io.FileInputStream;

/*Realiza un programa en Java que lea la cabecera de un fichero PDF y verifique si realmente se trata de un archivo PDF válido. Para que un archivo sea un PDF válido, debe comenzar con la 
 * siguiente secuencia de bytes: {37, 80, 68, 70}. Si la cabecera no coincide con esta secuencia, el programa debe informar al usuario de que el archivo no es válido (1 punto)
 * Katerin Sanz
 * AD01_Tarea03
 * 
 * */
public class Tarea3 {

	public static void main(String[] args) {
		comprobarCabeceraPDF();
	}

		private static void comprobarCabeceraPDF() {
			
			//String dir = ("." + File.separator + "src" + File.separator + "Tarea3" + File.separator + "documento.pdf");	
			String dir = ("." + File.separator + "src" + File.separator + "Tarea3" + File.separator + "Doc1.txt");	
			File fil = new File(dir);
			
			int[] cabeceraPDF = {37, 80, 68, 70};
			int[] cabeceraLeida = new int[4];
			
			try (InputStream is = new FileInputStream(fil)){				
				
				for(int i = 0; i<4;i++) {
					
					cabeceraLeida[i] = is.read();
					
				}
				
				if (Arrays.equals(cabeceraPDF, cabeceraLeida)) {
					System.out.println("El documento es un PDF");
				}else {
					System.out.println("El documento no es PDF.");
				}
				
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage()); 
			}
			
		}
}
