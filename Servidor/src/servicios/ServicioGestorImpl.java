

package servicios;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import interfaces.CallbackJugadorInterface;
import interfaces.ServicioDatosInterface;
import interfaces.ServicioGestorInterface;
import objetos.Partida;
import utiles.Utiles;

/**
 * Clase que implementa la interfaz del servicio 
 * gestor del Servidor
 * 
 * 
 */
public class ServicioGestorImpl extends UnicastRemoteObject implements ServicioGestorInterface
{
	private static final long serialVersionUID = 1L;
	private static ServicioDatosInterface datos;
	private HashMap<String, CallbackJugadorInterface> listaClientes = new HashMap<String, CallbackJugadorInterface>();	
	private HashMap<String, Partida> listaPartidas = new HashMap<String, Partida>();
	
	/**
	 * Constructor de la clase
	 * 
	 * @throws RemoteException
	 */
	public ServicioGestorImpl() throws RemoteException
	{	
		super();
		
		datos = (ServicioDatosInterface) Utiles.buscarServicio(Utiles.URL(ServicioDatosInterface.nombreServicio));
	}
	
	/**
	 * Método que registra el Callback de un usuario
	 * 
	 * @param callback Callback del usuario
	 * @param nombre Nombre del usuario
	 * @throws RemoteException
	 */
	public void registrarCallback(CallbackJugadorInterface callback, String nombre) throws RemoteException 
	{
		if (!(listaClientes.containsValue(callback))) listaClientes.put(nombre, callback);
	}

	/**
	 * Método que elimina el registro del Callback de un 
	 * usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @throws RemoteException
	 */
	public void desregistrarCallback(String nombre) throws RemoteException 
	{
		if (!(listaClientes.containsKey(nombre))) listaClientes.remove(nombre);
	}
	
	/**
	 * Método que crea una partida
	 * 
	 * @param callback Callback del creador de la partida
	 * @param nombreJugador1 Nombre del creador de la partida
	 * @return Identificador de la partida
	 * @throws RemoteException
	 */
	public String crearPartida(CallbackJugadorInterface callback, String nombreJugador1) throws RemoteException 
	{
		if (listaClientes.containsValue(callback)) 
		{
			CallbackJugadorInterface cbJugador1 = listaClientes.get(nombreJugador1);
			
			cbJugador1.notificar("A la espera de contrincante.");
			
			datos.crearPartida(nombreJugador1);
			
			listaPartidas.put(nombreJugador1, new Partida());
			listaPartidas.get(nombreJugador1).setJugador1(nombreJugador1);
						
			return nombreJugador1;
		}
		
		return null;
	}
	
	/**
	 * Método que entrega una lista de partidas en espera
	 * 
	 * @return Lista de partidas en espera
	 * @throws RemoteException
	 */
	public ArrayList<String> listarPartidasEnEspera() throws RemoteException 
	{
		return datos.getPartidas();
	}
	
	/**
	 * Método que muestra información sobre las partidas 
	 * en juego
	 * 
	 * @throws RemoteException
	 */
	public void listarPartidasEnJuego() throws RemoteException 
	{
		System.out.println("Partidas en juego:\n");
		
		if(listaPartidas.isEmpty())
		{
			Utiles.printColor("ROJO", "\tNo existen partidas en juego.\n\n");
		}
		else
		{
			int partidas = 0;
			
			for (Partida partida : listaPartidas.values()) 
			{	
				if (partida.isJugando())
				{
					Utiles.printColor("VERDE", "\tPartida creada por: " + partida.getJugador1() + "\n");
					
					Utiles.printColor("VERDE", "\n\t\tJugador 1: " + partida.getJugador1() + ". Puntos: " + partida.getPuntJugador1() + ".\n");
					Utiles.printColor("VERDE", "\t\tJugador 2: " + partida.getJugador2() + ". Puntos: " + partida.getPuntJugador2() + ".\n");
					
					System.out.println();
					
					partidas++;
				}
			}
			if (partidas == 0)
			{
				Utiles.printColor("ROJO", "\tNo existen partidas en juego\n\n");
			}			
		}
	}
	
	/**
	 * Método que accede a una partida
	 * 
	 * @param nombreJugador2 Nombre del jugador que accede
	 * @param partida Identificador numérico de la partida
	 * @return Identificador de la partida
	 * @throws RemoteException
	 */
	public String unirsePartida(String nombreJugador2, int partida) throws RemoteException 
	{	
		String nombreJugador1 = datos.buscarPartida(partida);
		
		if (!datos.eliminarPartida(partida))
		{
			return null;
		}
		else 
		{
			
			CallbackJugadorInterface cbJugador1 = listaClientes.get(nombreJugador1);
			CallbackJugadorInterface cbJugador2 = listaClientes.get(nombreJugador2);

			cbJugador1.notificar("Otro jugador se ha unido a tu partida.");
						
			listaPartidas.get(nombreJugador1).setJugador2(nombreJugador2);
			
			cbJugador1.notificar("Colocar barcos.");
			cbJugador2.notificar("Colocar barcos.");
		}
		
		return nombreJugador1;
	}
	
	/**
	 * Método que introduce las posiciones de los barcos
	 * 
	 * @param partidaID Identificador de la partida
	 * @param player Nombre del jugador
	 * @param barco1 Primer barco del jugador
	 * @param barco2 Segundo barco del jugador
	 * @throws RemoteException
	 */
	public boolean enviarPosicionBarcos(String partidaID, int player, String barco1, String barco2) throws RemoteException 
	{	
		Partida partida = listaPartidas.get(partidaID);
		
		return partida.setBarcos(player, barco1, barco2);
	}

	/**
	 * Método que inicia la partida
	 * 
	 * @param partidaID Identificador de la partida
	 * @param player Nombre del jugador
	 * @throws RemoteException
	 */
	public void comenzarPartida(String partidaID) throws RemoteException
	{
		Partida partida = listaPartidas.get(partidaID);
		
		if (partida.checkJugadoresPreparados()) 
		{		
			CallbackJugadorInterface cbJugador1 = listaClientes.get(partida.getJugador1());
			CallbackJugadorInterface cbJugador2 = listaClientes.get(partida.getJugador2());

			partida.setJugando(true);
			
			cbJugador1.notificar("Comienza el juego.");
			cbJugador2.notificar("Comienza el juego.");
		}
	}

	/**
	 * Método que introduce la posición del disparo
	 * 
	 * @param partidaID Identificador de la partida
	 * @param player Nombre del jugador
	 * @param disparo Posición del disparo
	 * @throws RemoteException
	 */
	public void enviarPosicionDisparo(String partidaID, int player, String disparo) throws RemoteException 
	{
		Partida partida = listaPartidas.get(partidaID);
		
		CallbackJugadorInterface cbJugador1 = listaClientes.get(partida.getJugador1());
		CallbackJugadorInterface cbJugador2 = listaClientes.get(partida.getJugador2());

		int barcosJugador1 = partida.getBarcosJugador1();
		int barcosJugador2 = partida.getBarcosJugador2();
		
		if (player == 1)
		{	
			if (disparo.equalsIgnoreCase("Me rindo"))
			{
				partida.setPuntJugador1(0);
				partida.setPuntJugador2(16);
				
				partida.setJugando(false);
				listaPartidas.remove(partidaID);
				
				datos.actualizarPuntos(partida.getJugador1(), partida.getPuntJugador1());
				datos.actualizarPuntos(partida.getJugador2(), partida.getPuntJugador2());
								
				cbJugador1.notificar("Te has rendido (has perdido).");
				cbJugador2.notificar("Tu rival se ha rendido (has ganado).");
			}
			else if (partida.comprobarDisparo(player, disparo)) 
			{
				partida.sumarPuntos(player, 1);
				
				if (partida.getBarcosJugador2() == 0) 
				{
					partida.sumarPuntos(player, 10);
					
					partida.setJugando(false);
					listaPartidas.remove(partidaID);
					
					datos.actualizarPuntos(partida.getJugador1(), partida.getPuntJugador1());
					datos.actualizarPuntos(partida.getJugador2(), partida.getPuntJugador2());
					
					cbJugador1.notificar("Has hundido la flota (has ganado).");
					cbJugador2.notificar("Te han hundido la flota (has perdido).");
				}
				else if (barcosJugador2 > partida.getBarcosJugador2()) 
				{
					cbJugador1.notificar("Has hundido un barco.");
					cbJugador2.notificar("Te han hundido un barco.");
				}
				else
				{
					cbJugador1.notificar("Disparo realizado por ti (tocado).");
					cbJugador2.notificar("Disparo realizado por el contrario (tocado).");
				}
			}
			else 
			{
				cbJugador1.notificar("Disparo realizado por ti (agua).");
				cbJugador2.notificar("Disparo realizado por el contrario (agua).");
			}
		}
		else
		{				
			if (disparo.equalsIgnoreCase("Me rindo"))
			{
				partida.setPuntJugador1(16);
				partida.setPuntJugador2(0);
				
				partida.setJugando(false);
				listaPartidas.remove(partidaID);
				
				datos.actualizarPuntos(partida.getJugador1(), partida.getPuntJugador1());
				datos.actualizarPuntos(partida.getJugador2(), partida.getPuntJugador2());
				
				cbJugador1.notificar("Tu rival se ha rendido (has ganado).");
				cbJugador2.notificar("Te has rendido (has perdido).");
			}		
			else if (partida.comprobarDisparo(player, disparo)) 
			{
				partida.sumarPuntos(player, 1);
				
				if (partida.getBarcosJugador1() == 0) 
				{
					partida.sumarPuntos(player, 10);
					
					partida.setJugando(false);
					listaPartidas.remove(partidaID);
					
					datos.actualizarPuntos(partida.getJugador1(), partida.getPuntJugador1());
					datos.actualizarPuntos(partida.getJugador2(), partida.getPuntJugador2());
					
					cbJugador2.notificar("Has hundido la flota (has ganado).");
					cbJugador1.notificar("Te han hundido la flota (has perdido).");
				}
				else if (barcosJugador1 > partida.getBarcosJugador1()) 
				{
					cbJugador2.notificar("Has hundido un barco.");
					cbJugador1.notificar("Te han hundido un barco.");
				}
				else
				{
					cbJugador2.notificar("Disparo realizado por ti (tocado).");
					cbJugador1.notificar("Disparo realizado por el contrario (tocado).");
				}
			}
			else 
			{
				cbJugador2.notificar("Disparo realizado por ti (agua).");
				cbJugador1.notificar("Disparo realizado por el contrario (agua).");
			}
		}
	}

	/**
	 * Método que consulta la puntuación histórica de un 
	 * jugador
	 * 
	 * @param jugador Nombre del jugador
	 * @return Puntuación histórica del jugador
	 * @throws RemoteException
	 */
	public String obtenerPuntuacionHistorica(String jugador) throws RemoteException 
	{
		return datos.infoJugador(jugador);
	}

	/**
	 * Método que confirma que el servidor está conectado
	 * 
	 * @return Cierto
	 * @throws RemoteException
	 */
	public boolean ping() throws RemoteException 
	{
		return true;
	}
}
