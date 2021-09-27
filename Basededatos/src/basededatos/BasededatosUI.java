

package basededatos;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

import servicios.ServicioDatosImpl;
import utiles.Utiles;

/**
 * Clase que implementa la interfaz gr�fica de 
 * usuario de la Base de Datos
 * 
 * 
 */
public class BasededatosUI 
{
	private static Scanner sn = new Scanner(System.in);

	/**
	 * M�todo que muestra el men� de la Base de Datos
	 * 
	 * @param URLdatos URL del servicio de datos
	 * @param datos Servicio de datos
	 */
	public static void menu(ServicioDatosImpl datos, String URLdatos) 
	{	 	
        boolean salir = false;
        int opcion;

        while (!salir) 
        {
        	System.out.println("MENU de la Base de Datos:\n");

            System.out.println("\t1.- Informaci�n de la Base de Datos.");
            System.out.println("\t2.- Listar jugadores registrados (Sus puntuaciones).");
            System.out.println("\t3.- Salir.\n");
            System.out.println("Elige una de las opciones:");
 
            try 
            {
                opcion = sn.nextInt();
                sn.nextLine();
                System.out.println();
 
                switch (opcion) 
                {
                    case 1:
                    	
                    	Utiles.clearScreen(); 
                        System.out.println("Objetos remotos:\n");
                        Utiles.printColor("VERDE", "\t" + URLdatos + ".\n\n");  
                        break;
                        
                    case 2:	
                    	
                    	Utiles.clearScreen();
						try 
						{
							datos.infoJugadores();
						} 
						catch (RemoteException e) 
						{
							System.out.println("Error. El servicio de datos no est� disponible.\n");
						}
                        break;      
                        
                    case 3:   	
                    	
                        salir = true;
                        break;      
                        
                    default:  
                    	
                    	Utiles.clearScreen();
                    	System.out.println("Error:\n");
                        Utiles.printColor("ROJO", "\tSolo n�meros entre 1 y 3.\n\n");
                }  
            } 
            catch (InputMismatchException e) 
            {
            	System.out.println();
            	Utiles.clearScreen();
            	System.out.println("Error:\n");
            	Utiles.printColor("ROJO", "\tDebes insertar un n�mero.\n\n");

            	sn.next();
            }
        }
        sn.close();
	}
}
