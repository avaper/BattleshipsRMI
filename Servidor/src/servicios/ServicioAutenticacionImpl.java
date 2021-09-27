

package servicios;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioDatosInterface;
import utiles.Utiles;

/**
 * Clase que implementa la interfaz del servicio
 * de autenticación del Servidor
 * 
 * 
 */
public class ServicioAutenticacionImpl extends UnicastRemoteObject implements ServicioAutenticacionInterface
{
	private static final long serialVersionUID = 1L;
	private static ServicioDatosInterface datos;
	
	/**
	 * Constructor de la clase
	 * 
	 * @throws RemoteException
	 */
	public ServicioAutenticacionImpl() throws RemoteException  
	{
		super();
		
		datos = (ServicioDatosInterface) Utiles.buscarServicio(Utiles.URL(ServicioDatosInterface.nombreServicio));
	}

	/**
	 * Método que registra a un usuario con su contraseña
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contraseña del usuario
	 * @return Verdadero si se ha logrado registrar
	 * @throws RemoteException
	 */
	public boolean registrar(String nombre, String password) throws RemoteException 
	{
		return datos.registrar(nombre, password);
	}

	/**
	 * Método que autentica a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contraseña del usuario
	 * @return Verdadero si se ha logrado autenticar
	 * @throws RemoteException
	 */
	public boolean autenticar(String nombre, String password) throws RemoteException 
	{
		return datos.autenticar(nombre, password);
	}

	/**
	 * Método que desloguea a un jugador
	 * 
	 * @param nombre Nombre del jugador
	 * @throws RemoteException
	 */
	public void logOut(String nombre) throws RemoteException 
	{
		datos.logOut(nombre);
	}
}