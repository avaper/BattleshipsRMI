

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
	 * Método que da información sobre los usuarios registrados
	 * 
	 * @throws RemoteException
	 */
	public void infoJugadores () throws RemoteException;
	
	/**
	 * Método que registra a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contraseña del usuario
	 * @return Verdadero si se ha logrado registrar
	 * @throws RemoteException
	 */
	public boolean registrar (String nombre, String password) throws RemoteException;
	
	/**
	 * Método que autentica a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contraseña del usuario
	 * @return Verdadero si se ha logrado autenticar
	 * @throws RemoteException
	 */
	public boolean autenticar (String nombre, String password) throws RemoteException;
	
	/**
	 * Método que crea una partida
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
	 * Método que busca partidas en espera
	 * 
	 * @param partida Elemento en la lista de partidas en espera
	 * @return Identificador de la partida
	 * @throws RemoteException
	 */
	public String buscarPartida(int partida) throws RemoteException;
	
	/**
	 * Método que elimina una partida en espera
	 * 
	 * @param partida Elemento en la lista de partidas en espera
	 * @return Verdadero si se ha logrado eliminar
	 * @throws RemoteException
	 */
	public boolean eliminarPartida(int partida) throws RemoteException;
	
	/**
	 * Método que entrega información sobre un jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @return Información sobre el jugador
	 * @throws RemoteException
	 */
	public String infoJugador (String jugador) throws RemoteException;
	
	/**
	 * Método que actualiza la puntuación de un jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @param puntos Puntos del jugador
	 * @throws RemoteException
	 */
	public void actualizarPuntos(String jugador, int puntos) throws RemoteException;
	
	/**
	 * Método que desloguea a un jugador
	 * 
	 * @param nombre Nombre del jugador
	 * @throws RemoteException
	 */
	public void logOut(String nombre) throws RemoteException;
}
