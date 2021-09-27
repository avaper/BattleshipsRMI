

package jugador;

import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioGestorInterface;
import servicios.CallbackJugadorImpl;
import utiles.Utiles;
import objetos.ListaSincronizada;

/**
 * Clase para la entidad Jugador
 * 
 * 
 */
public class Jugador 
{
	private static CallbackJugadorImpl callback;
	private static String URLcallback;
	private static boolean servicioCallbackJugadorOK = false;

	private static ServicioAutenticacionInterface autenticacion;
	private static String URLautenticacion;
	private static boolean servicioAutenticacionOK = false;
	
	private static ServicioGestorInterface gestor;
	private static String URLgestor;
	private static boolean servicioGestorOK = false;
	
	private static boolean CONECTADO = false;
	private static ListaSincronizada ListaEventos = new ListaSincronizada();

	/**
	 * Método que inicia al Jugador
	 */
	public static void iniciar()
	{		
		Utiles.obtenerRegistro();

		callback = (CallbackJugadorImpl) Utiles.exportServicio(CallbackJugadorImpl.class.getName(), ListaEventos);		
		URLcallback = Utiles.URL(CallbackJugadorImpl.nombreServicio) + callback.hashCode();
		servicioCallbackJugadorOK = Utiles.bindServicio(URLcallback, callback);

		URLautenticacion = Utiles.URL(ServicioAutenticacionInterface.nombreServicio);
		autenticacion = (ServicioAutenticacionInterface) Utiles.buscarServicio(URLautenticacion);
		servicioAutenticacionOK = (autenticacion != null);
		
		URLgestor = Utiles.URL(ServicioGestorInterface.nombreServicio);
		gestor = (ServicioGestorInterface) Utiles.buscarServicio(URLgestor);
		servicioGestorOK = (gestor != null);
	}
	
	/**
	 * Método que finaliza al Jugador
	 */
	public static void terminar() 
	{	
		if(CONECTADO) 
		{
			Utiles.unbindServicio(URLcallback);
		}
		
		Utiles.unExportServicio(callback);
		
		if(CONECTADO) Utiles.printColor("VERDE", "Jugador desconectado.\n\n");
	}
	
	/**
	 * Método principal del Jugador
	 * 
	 * @param args Vector de String pasados como argumento
	 */
	public static void main(String[] args)
	{
		iniciar();
		
		if (servicioCallbackJugadorOK && servicioAutenticacionOK && servicioGestorOK)
		{
			System.out.println("Jugador:\n");
			Utiles.printColor("VERDE", "\tConectado.\n\n");
			CONECTADO  = true;
			JugadorUI.menu(ListaEventos, gestor, autenticacion, callback);
		}
		
		terminar();
	}
}
