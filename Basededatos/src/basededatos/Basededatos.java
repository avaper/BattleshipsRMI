

package basededatos;

import servicios.ServicioDatosImpl;
import utiles.Utiles;

/**
 * Clase para la entidad Base de Datos
 * 
 * 
 */
public class Basededatos 
{
	private static ServicioDatosImpl datos;
	private static String URLdatos;
	private static boolean servicioDatosOK = false;
	
	private static boolean CONECTADA = false;

	/**
	 * Método que inicia la Base de Datos
	 */
	public static void iniciar()
	{	
		Utiles.crearRegistro();
		
		datos = (ServicioDatosImpl) Utiles.exportServicio(ServicioDatosImpl.class.getName(), null);
		URLdatos = Utiles.URL(ServicioDatosImpl.nombreServicio);
		servicioDatosOK = Utiles.bindServicio(URLdatos, datos);
	}
	
	/**
	 * Método que finaliza la Base de Datos
	 */
	public static void terminar()
	{
		if(CONECTADA)
		{
			servicioDatosOK = Utiles.unbindServicio(URLdatos);	
		}
		
		Utiles.unExportServicio(datos);
		
		if(CONECTADA) Utiles.printColor("VERDE", "Base de Datos desconectada.\n\n");
	}

	/**
	 * Método principal de la Base de Datos
	 * 
	 * @param args Vector de String pasados como argumento
	 */
	public static void main(String[] args) 
	{
		iniciar();
		
		if(servicioDatosOK)
		{
			System.out.println("Base de Datos:\n");
			Utiles.printColor("VERDE", "\tConectada.\n\n");
			CONECTADA  = true;
			BasededatosUI.menu(datos, URLdatos);
		}
		
		terminar();		
	}
}