

package utiles;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Clase de utilería
 * 
 * 
 */
public class Utiles 
{
	public static int numPuerto = 8000;
	public static String hostName;
	
	private static String errorRemote       = "No se pudo contactar con el registro";
	private static String errorMalformedURL = "La URL no tiene un formato válido";
	private static String errorAlreadyBound = "El servicio ya está registrado";
	
	/**
	 * Método que genera una URL para un servicio
	 * 
	 * @param nombreServicio nombre del servicio para el que se generará
	 * la URL
	 * @return URL generada para el servicio
	 */
	public static String URL (String nombreServicio) 
	{
		try
		{
			//hostName = InetAddress.getLocalHost().getHostAddress();
			hostName = InetAddress.getLocalHost().getHostName();
			return "rmi://" + hostName + ":" + numPuerto + "/" + nombreServicio;			
		}
		catch(UnknownHostException e) 
		{
			hostName = "localhost";
			return "rmi://" + hostName + ":" + numPuerto + "/" + nombreServicio;	
		}
	}
	
	/**
	 * Método que crea el rmiregistry
	 */
	public static void crearRegistro()
	{
		try
		{
			LocateRegistry.createRegistry(numPuerto);
		}
		catch(RemoteException e)
		{
			printColor("ROJO", "No se ha podido iniciar el registro\n\n");
		}
	}
	
	/**
	 * Método que obtiene el rmiregistry
	 */
	public static void obtenerRegistro()
	{
		try
		{
			LocateRegistry.getRegistry(numPuerto);
		}
		catch(RemoteException e)
		{
			printColor("ROJO", "No se ha podido acceder al registro\n\n");
		}
	}


	/**
	 * Método que exporta un servicio
	 * 
	 * @param servicio Nombre del servicio
	 * @param param Parámetro para el callback (lista sincronizada)
	 * @return Objeto exportado
	 */
	public static Remote exportServicio(String servicio, Object param) 
	{
		try 
		{
			if (param == null) 
			{
				return (Remote) Class.forName(servicio).newInstance();
			}
			else 
			{
				return (Remote) Class.forName(servicio).getConstructor(new Class[] {param.getClass()}).newInstance(param);
			}
		} 
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
		{
			printColor("ROJO", "ERROR, no se ha podido exportar el servicio\n\n");
			return null;
		}
	}
	
	/**
	 * Método que elimina un servicio exportado
	 * 
	 * @param servicio Servicio a eliminar
	 */
	public static void unExportServicio(Remote servicio)
	{
		try 
		{
			UnicastRemoteObject.unexportObject(servicio, true);
		} 
		catch (NoSuchObjectException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que registra un servicio
	 * 
	 * @param URL URL del servicio
	 * @param servicio Objeto remoto
	 * @return Verdadero si se ha logrado registrar
	 */
	public static boolean bindServicio(String URL, Remote servicio)
	{	
		String ERROR = "Error registrando el servicio: " + URL + ". ";
		
		try
		{
			Naming.bind(URL, servicio);
			return true;
		}
		catch(RemoteException e)
		{
			printColor("ROJO", ERROR + errorRemote + "\n\n");
			return false;
		} 
		catch (MalformedURLException e) 
		{
			printColor("ROJO", ERROR + errorMalformedURL + "\n\n");
			return false;
		} 
		catch (AlreadyBoundException e) 
		{
			printColor("ROJO", ERROR + errorAlreadyBound + "\n\n");
			return false;
		} 
	}
	
	/**
	 * Método que elimina del registro un servicio
	 * 
	 * @param URL URL del servicio
	 * @return Verdadero si se ha logrado eliminar
	 */
	public static boolean unbindServicio(String URL)
	{
		String ERROR = "Error buscando el servicio: " + URL + ". ";
		
		try
		{
			Naming.unbind(URL);
			return true;
		}
		
		catch(RemoteException e)
		{
			printColor("ROJO", ERROR + errorRemote + "\n\n");
			return false;
		} 
		catch (MalformedURLException e) 
		{
			printColor("ROJO", ERROR + errorMalformedURL + "\n\n");
			return false;
		} 
		catch (NotBoundException e) 
		{
			printColor("ROJO", ERROR + errorRemote + "\n\n");
			return false;
		}
	}
	
	/**
	 * Método que busca un servicio en el registro
	 * 
	 * @param URL URL del servicio
	 * @return Objeto remoto encontrado, null si no se encuentra
	 */
	public static Remote buscarServicio(String URL)
	{
		String ERROR = "Error buscando el servicio: " + URL + ". ";
		
		try
		{
			return Naming.lookup(URL);
		}
		catch(RemoteException e)
		{
			printColor("ROJO", ERROR + errorRemote + "\n\n");
		} 
		catch (MalformedURLException e) 
		{
			printColor("ROJO", ERROR + errorMalformedURL + "\n\n");
		} 
		catch (NotBoundException e) 
		{
			printColor("ROJO", ERROR + errorRemote + "\n\n");
		}
		return null;
	}
	
	/**
	 * Método que limpia la pantalla
	 */
	public static void clearScreen()
	{
		String OS = System.getProperty("os.name");
		Console console = System.console();

		if (console != null)
		{
			if (OS.contains("Windows"))
			{
				try 
				{
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				} 
				catch (InterruptedException | IOException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("\033[H\033[2J");
				System.out.flush();
			}
		}
		else
		{
			System.out.flush();
		}
	
    }
	
	/**
	 * Método que imprime a color
	 * 
	 * @param color Color (ROJO|VERDE|AMARILLO|AZUL)
	 * @param mensaje Mensaje a imprimir
	 */
	public static void printColor(String color, String mensaje)
	{
		String ROJO     = "\033[91m";
		String VERDE    = "\033[92m";
		String AMARILLO = "\033[93m";
		String AZUL 	= "\033[94m";
		String RESET    = "\033[0m";
		
		String COLOR;
		
		if (color == "ROJO") COLOR = ROJO;
		else if (color == "VERDE") COLOR = VERDE;
		else if (color == "AMARILLO") COLOR = AMARILLO;
		else if (color == "AZUL") COLOR = AZUL;
		else COLOR = RESET;

		String OS = System.getProperty("os.name");
		Console console = System.console();

		if ((console != null) && (OS.equals("Windows 10") || OS.equals("Linux")))
		{
			if (OS.equals("Windows 10"))
			{
				try 
				{		
					new ProcessBuilder("cmd", "/c").inheritIO().start().waitFor();
				} 
				catch (InterruptedException | IOException e) 
				{
					e.printStackTrace();
				}
			}
			
			console.printf("%s%s%s", COLOR, mensaje, RESET);	
		}
		else 
		{
			System.out.print(mensaje);
		}
	}
}