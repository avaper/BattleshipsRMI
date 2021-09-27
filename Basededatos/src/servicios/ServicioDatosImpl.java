

package servicios;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import interfaces.ServicioDatosInterface;
import objetos.Usuario;
import utiles.Utiles;

/**
 * Clase que implementa la interfaz del servicio 
 * de datos de la Base de Datos
 * 
 * 
 */
public class ServicioDatosImpl extends UnicastRemoteObject implements ServicioDatosInterface
{
	private static final long serialVersionUID = 1L;
	private static HashMap<String, Usuario> registrados = new HashMap<String, Usuario>();
	private static ArrayList<String> logueados = new ArrayList<String>();
	private static ArrayList<String> enEspera = new ArrayList<String>();

	/**
	 * Constructor de la clase
	 * 
	 * @throws RemoteException
	 */
	public ServicioDatosImpl() throws RemoteException 
	{
		super();
	}

	/**
	 * M�todo que da informaci�n sobre los usuarios registrados
	 * 
	 * @throws RemoteException
	 */
	public void infoJugadores() throws RemoteException 
	{
		System.out.println("Usuarios registrados:\n");
		
		if (registrados.isEmpty()) 
		{
			Utiles.printColor("ROJO", "\tNo existen jugadores en el registro.\n\n");
		}
		else
		{
			for (Entry<String, Usuario> usuario : registrados.entrySet()) 
			{
				Utiles.printColor("VERDE", "\tJugador: " + usuario.getKey() + ". Puntos: " + usuario.getValue().getPuntos() + ".\n");
			}
			System.out.println();
		}
	}

	/**
	 * M�todo que registra a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado registrar
	 * @throws RemoteException
	 */
	public boolean registrar(String nombre, String password) throws RemoteException 
	{
		if (registrados.containsKey(nombre)) return false;
		else
		{
			registrados.put(nombre, new Usuario(password, 0));
			return true;
		}
	}

	/**
	 * M�todo que autentica a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado autenticar
	 * @throws RemoteException
	 */
	public boolean autenticar(String nombre, String password) throws RemoteException 
	{
		if (registrados.containsKey(nombre))
		{	
			if (registrados.get(nombre).getPassword().equals(password))
			{
				if (!logueados.contains(nombre))
				{	
					logueados.add(nombre);
					return true;
				}
				else return false;
			}
		}
		return false;
	}

	/**
	 * M�todo que crea una partida
	 * 
	 * @param nombre Nombre del creador de la partida
	 * @throws RemoteException
	 */
	public void crearPartida(String nombre) throws RemoteException 
	{	
		enEspera.add(nombre);		
	}

	/**
	 * Getter de la lista de partidas en espera
	 * 
	 * @return Lista de partidas en espera
	 * @throws RemoteException
	 */
	public ArrayList<String> getPartidas() throws RemoteException 
	{
		return enEspera;
	}

	/**
	 * M�todo que busca partidas en espera
	 * 
	 * @param partida Elemento en la lista de partidas en espera
	 * @return Identificador de la partida
	 * @throws RemoteException
	 */
	public String buscarPartida(int partida) throws RemoteException
	{
		try 
		{
			return enEspera.get(partida);
		} 
		catch (IndexOutOfBoundsException e) 
		{		
			return null;
		}
	}

	/**
	 * M�todo que elimina una partida en espera
	 * 
	 * @param partida Elemento en la lista de partidas en espera
	 * @return Verdadero si se ha logrado eliminar
	 * @throws RemoteException
	 */
	public boolean eliminarPartida(int partida) throws RemoteException 
	{
		try
		{
			enEspera.remove(partida);
			return true;
		}
		catch (IndexOutOfBoundsException e) 
		{		
			return false;
		}
	}
	
	/**
	 * M�todo que entrega informaci�n sobre un jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @return Informaci�n sobre el jugador
	 * @throws RemoteException
	 */
	public String infoJugador(String jugador) throws RemoteException 
	{
		return "Jugador: " + jugador + ". Puntos: " + registrados.get(jugador).getPuntos();
	}
	
	/**
	 * M�todo que actualiza la puntuaci�n de un jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @param puntos Puntos del jugador
	 * @throws RemoteException
	 */
	public void actualizarPuntos(String jugador, int puntos) throws RemoteException 
	{
		registrados.get(jugador).sumarPuntos(puntos);
	}

	/**
	 * M�todo que desloguea a un jugador
	 * 
	 * @param nombre Nombre del jugador
	 * @throws RemoteException
	 */
	public void logOut(String nombre) throws RemoteException 
	{
		if (logueados.contains(nombre))
		{
			logueados.remove(nombre);
		}
	}
}
