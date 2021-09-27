

package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Interfaz del servicio Gestor del Servidor
 * 
 * 
 */
public interface ServicioGestorInterface extends Remote 
{
	String nombreServicio = "servicioGestor";
	
	/**
	 * Método que registra el Callback de un usuario
	 * 
	 * @param callback Callback del usuario
	 * @param nombre Nombre del usuario
	 * @throws RemoteException
	 */
	public void registrarCallback(CallbackJugadorInterface callback, String nombre) throws RemoteException;
	
	/**
	 * Método que elimina el registro del Callback de un 
	 * usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @throws RemoteException
	 */
	public void desregistrarCallback(String nombre) throws RemoteException;
	
	/**
	 * Método que crea una partida
	 * 
	 * @param callback Callback del creador de la partida
	 * @param nombreJugador1 Nombre del creador de la partida
	 * @return Identificador de la partida
	 * @throws RemoteException
	 */
	public String crearPartida(CallbackJugadorInterface callback, String nombreJugador1) throws RemoteException;
	
	/**
	 * Método que entrega una lista de partidas en espera
	 * 
	 * @return Lista de partidas en espera
	 * @throws RemoteException
	 */
	public ArrayList<String> listarPartidasEnEspera() throws RemoteException;
	
	/**
	 * Método que muestra información sobre las partidas 
	 * en juego
	 * 
	 * @throws RemoteException
	 */
	public void listarPartidasEnJuego() throws RemoteException;
	
	/**
	 * Método que accede a una partida
	 * 
	 * @param nombreJugador2 Nombre del jugador que accede
	 * @param partida Identificador numérico de la partida
	 * @return Identificador de la partida
	 * @throws RemoteException
	 */
	public String unirsePartida(String nombreJugador2, int partida) throws RemoteException;
	
	/**
	 * Método que introduce las posiciones de los barcos
	 * 
	 * @param partidaID Identificador de la partida
	 * @param player Nombre del jugador
	 * @param barco1 Primer barco del jugador
	 * @param barco2 Segundo barco del jugador
	 * @return Falso si ha habido un error
	 * @throws RemoteException
	 */
	public boolean enviarPosicionBarcos(String partidaID, int player, String barco1, String barco2) throws RemoteException;
	
	/**
	 * Método que inicia la partida
	 * 
	 * @param partidaID Identificador de la partida
	 * @param player Nombre del jugador
	 * @throws RemoteException
	 */
	public void comenzarPartida(String partidaID) throws RemoteException;
	
	/**
	 * Método que introduce la posición del disparo
	 * 
	 * @param partidaID Identificador de la partida
	 * @param player Nombre del jugador
	 * @param disparo Posición del disparo
	 * @throws RemoteException
	 */
	public void enviarPosicionDisparo(String partidaID, int player, String disparo) throws RemoteException;
	
	/**
	 * Método que consulta la puntuación histórica de un 
	 * jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @return Puntuación histórica del jugador
	 * @throws RemoteException
	 */
	public String obtenerPuntuacionHistorica(String jugador) throws RemoteException;
	
	/**
	 * Método que confirma que el servidor está conectado
	 * 
	 * @return Cierto
	 * @throws RemoteException
	 */
	public boolean ping() throws RemoteException;
}