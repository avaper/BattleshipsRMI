

package servidor;

import servicios.ServicioAutenticacionImpl;
import servicios.ServicioGestorImpl;
import utiles.Utiles;

/**
 * Clase para la entidad Servidor
 * 
 * 
 */
public class Servidor 
{
	private static ServicioAutenticacionImpl autenticacion;
	private static String URLautenticacion;
	private static boolean servicioAutenticacionOK = false;
	
	private static ServicioGestorImpl gestor;
	private static String URLgestor;
	private static boolean servicioGestorOK = false;
		
	private static boolean CONECTADO = false;

	/**
	 * Método que inicia al Servidor
	 */
	public static void iniciar()
	{
		Utiles.obtenerRegistro();
		
		autenticacion = (ServicioAutenticacionImpl) Utiles.exportServicio(ServicioAutenticacionImpl.class.getName(), null);
		gestor = (ServicioGestorImpl) Utiles.exportServicio(ServicioGestorImpl.class.getName(), null);
		
		URLautenticacion = Utiles.URL(ServicioAutenticacionImpl.nombreServicio);
		URLgestor = Utiles.URL(ServicioGestorImpl.nombreServicio);
		
		servicioAutenticacionOK = Utiles.bindServicio(URLautenticacion, autenticacion);
		servicioGestorOK = Utiles.bindServicio(URLgestor, gestor);
	}
	
	/**
	 * Método que finaliza al Servidor
	 */
	public static void terminar() 
	{
		if(CONECTADO)
		{
			Utiles.unbindServicio(URLautenticacion);
			Utiles.unbindServicio(URLgestor);
		}
		
		Utiles.unExportServicio(autenticacion);
		Utiles.unExportServicio(gestor);
		
		if(CONECTADO) Utiles.printColor("VERDE", "Servidor desconectado.\n\n");
	}	
	
	/**
	 * Método principal del Servidor
	 * 
	 * @param args Vector de String pasados como argumento
	 */
	public static void main(String[] args)
	{
		iniciar();
		
		if(servicioAutenticacionOK && servicioGestorOK)
		{	
			System.out.println("Servidor:\n"); 
			Utiles.printColor("VERDE", "\tConectado.\n\n");
			CONECTADO  = true;
			ServidorUI.menu(gestor, URLgestor, URLautenticacion);		
		}
		
		terminar();
	}
}