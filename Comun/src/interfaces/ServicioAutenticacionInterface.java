

package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz del servicio de Autenticaci�n del Servidor
 * 
 * 
 */
public interface ServicioAutenticacionInterface extends Remote 
{
	String nombreServicio = "servicioAutenticacion";
	
	/**
	 * M�todo que registra a un usuario con su contrase�a
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado registrar
	 * @throws RemoteException
	 */
	public boolean registrar(String nombre, String password) throws RemoteException;
	
	/**
	 * M�todo que autentica a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contrase�a del usuario
	 * @return Verdadero si se ha logrado autenticar
	 * @throws RemoteException
	 */
	public boolean autenticar(String nombre, String password) throws RemoteException;
	
	/**
	 * M�todo que desloguea a un jugador
	 * 
	 * @param nombre Nombre del jugador
	 * @throws RemoteException
	 */
	public void logOut(String nombre) throws RemoteException;
}
