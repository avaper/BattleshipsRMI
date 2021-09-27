

package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz del servicio de Autenticación del Servidor
 * 
 * 
 */
public interface ServicioAutenticacionInterface extends Remote 
{
	String nombreServicio = "servicioAutenticacion";
	
	/**
	 * Método que registra a un usuario con su contraseña
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contraseña del usuario
	 * @return Verdadero si se ha logrado registrar
	 * @throws RemoteException
	 */
	public boolean registrar(String nombre, String password) throws RemoteException;
	
	/**
	 * Método que autentica a un usuario
	 * 
	 * @param nombre Nombre del usuario
	 * @param password Contraseña del usuario
	 * @return Verdadero si se ha logrado autenticar
	 * @throws RemoteException
	 */
	public boolean autenticar(String nombre, String password) throws RemoteException;
	
	/**
	 * Método que desloguea a un jugador
	 * 
	 * @param nombre Nombre del jugador
	 * @throws RemoteException
	 */
	public void logOut(String nombre) throws RemoteException;
}
