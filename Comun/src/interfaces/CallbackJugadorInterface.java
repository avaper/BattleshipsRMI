

package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz del servicio Callback del Jugador
 * 
 * 
 */
public interface CallbackJugadorInterface extends Remote 
{
	String nombreServicio = "servicioCallback";
	
	/**
	 * Método Callback del Jugador
	 * 
	 * @param evento Evento notificado por el Servidor
	 * @throws RemoteException
	 */
	public void notificar(String evento) throws RemoteException;
}
