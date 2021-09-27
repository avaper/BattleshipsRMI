

package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interfaz del servicio de Datos de la Base de Datos
 * 
 * 
 */
public interface ServicioDatosInterface extends Remote 
{
	String nombreServicio = "servicioDatos";
	
	/**
	 * M�todo que da informaci�n sobre los usuarios registrados
	 * 
	 * @throws RemoteException
	 */
	public void infoJugadores () throws RemoteException;
	
	/**
	 * M�todo que registra a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado registrar
	 * @throws RemoteException
	 */
	public boolean registrar (String nombre, String password) throws RemoteException;
	
	/**
	 * M�todo que autentica a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado autenticar
	 * @throws RemoteException
	 */
	public boolean autenticar (String nombre, String password) throws RemoteException;
	
	/**
	 * M�todo que crea una partida
	 * 
	 * @param nombre Nombre del creador de la partida
	 * @throws RemoteException
	 */
	public void crearPartida(String nombre) throws RemoteException;
	
	/**
	 * Getter de la lista de partidas en espera
	 * 
	 * @return Lista de partidas en espera
	 * @throws RemoteException
	 */
	public ArrayList<String> getPartidas() throws RemoteException;
	
	/**
	 * M�todo que busca partidas en espera
	 * 
	 * @param partida Elemento en la lista de partidas en espera
	 * @return Identificador de la partida
	 * @throws RemoteException
	 */
	public String buscarPartida(int partida) throws RemoteException;
	
	/**
	 * M�todo que elimina una partida en espera
	 * 
	 * @param partida Elemento en la lista de partidas en espera
	 * @return Verdadero si se ha logrado eliminar
	 * @throws RemoteException
	 */
	public boolean eliminarPartida(int partida) throws RemoteException;
	
	/**
	 * M�todo que entrega informaci�n sobre un jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @return Informaci�n sobre el jugador
	 * @throws RemoteException
	 */
	public String infoJugador (String jugador) throws RemoteException;
	
	/**
	 * M�todo que actualiza la puntuaci�n de un jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @param puntos Puntos del jugador
	 * @throws RemoteException
	 */
	public void actualizarPuntos(String jugador, int puntos) throws RemoteException;
	
	/**
	 * M�todo que desloguea a un jugador
	 * 
	 * @param nombre Nombre del jugador
	 * @throws RemoteException
	 */
	public void logOut(String nombre) throws RemoteException;
}
