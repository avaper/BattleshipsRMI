

package servidor;

import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

import servicios.ServicioGestorImpl;
import utiles.Utiles;

/**
 * Clase que implementa la interfaz gr�fica de 
 * usuario del Servidor
 * 
 * 
 */
public class ServidorUI 
{
	private static Scanner sn = new Scanner(System.in);
	
	/**
	 * M�todo que muestra el men� del Servidor
	 * 
	 * @param URLautenticacion URL del servicio de autenticaci�n
	 * @param URLgestor URL del servicio gestor
	 * @param gestor Servicio remoto gestor
	 */
	public static void menu(ServicioGestorImpl gestor, String URLgestor, String URLautenticacion)
	{	
        boolean salir = false;
        int opcion;
 
        while (!salir) 
        {
        	System.out.println("MENU del Servidor:\n");
        	System.out.println("\t1.- Informaci�n del Servidor.");
            System.out.println("\t2.- Estado de las partidas que se est�n jugando en este momento.");
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
                    	Utiles.printColor("VERDE", "\t" + URLautenticacion + ".\n\t" + URLgestor + ".\n\n");
                        break;
                        
                    case 2:
                    	
                    	Utiles.clearScreen();
						try 
						{
							gestor.listarPartidasEnJuego();
						} 
						catch (RemoteException e) 
						{
							System.out.println("Error. El servicio gestor no est� disponible.\n");
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
