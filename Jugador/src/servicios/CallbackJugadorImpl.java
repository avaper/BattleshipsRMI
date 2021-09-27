

package servicios;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.CallbackJugadorInterface;
import objetos.ListaSincronizada;

/**
 * Clase que implementa la interfaz del servicio 
 * Callback del Jugador
 * 
 * 
 */
public class CallbackJugadorImpl extends UnicastRemoteObject implements CallbackJugadorInterface
{
	private static final long serialVersionUID = 1L;
	private ListaSincronizada ListaNotificaEventos;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param listaEventos Lista sincronizada
	 * @throws RemoteException
	 */
	public CallbackJugadorImpl(ListaSincronizada lista) throws RemoteException 
	{
		super();
		ListaNotificaEventos = lista;
	}
	
	/**
	 * Método Callback del Jugador
	 * 
	 * @param evento Evento notificado por el Servidor
	 * @throws RemoteException
	 */
	public void notificar(String evento) throws RemoteException 
	{
		ListaNotificaEventos.agregarElemento(evento);
	}
}