

package servicios;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioDatosInterface;
import utiles.Utiles;

/**
 * Clase que implementa la interfaz del servicio
 * de autenticaci�n del Servidor
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
	 * M�todo que registra a un usuario con su contrase�a
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado registrar
	 * @throws RemoteException
	 */
	public boolean registrar(String nombre, String password) throws RemoteException 
	{
		return datos.registrar(nombre, password);
	}

	/**
	 * M�todo que autentica a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado autenticar
	 * @throws RemoteException
	 */
	public boolean autenticar(String nombre, String password) throws RemoteException 
	{
		return datos.autenticar(nombre, password);
	}

	/**
	 * M�todo que desloguea a un jugador
	 * 
	 * @param nombre Nombre del jugador
	 * @throws RemoteException
	 */
	public void logOut(String nombre) throws RemoteException 
	{
		datos.logOut(nombre);
	}
}