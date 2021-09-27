

package objetos;

/**
 * Clase que implementa un usuario registrado
 * 
 * 
 */
public class Usuario 
{
	private String password;
	private int puntos;
	
	/**
	 * Constructor de la clase Usuario
	 * 
	 * @param pass Contrase�a del usuario
	 * @param pts Puntos del usuario
	 */
	public Usuario(String pass, int pts) 
	{
		setPassword(pass);
		setPuntos(pts);
	}
	
	/**
	 * M�todo que suma puntos al usuario
	 * 
	 * @param pts Puntos que se sumar�n
	 */
	public void sumarPuntos(int pts)
	{
		puntos += pts;
	}

	/**
	 * Getter de los puntos del usuario
	 * 
	 * @return Puntos del usuario
	 */
	public int getPuntos() 
	{
		return puntos;
	}

	/**
	 * Setter de los puntos del usuario
	 * 
	 * @param puntos Puntos del usuario
	 */
	public void setPuntos(int puntos) 
	{
		this.puntos = puntos;
	}

	/**
	 * Getter de la contrase�a del usuario
	 * 
	 * @return Contrase�a del usuario
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * Setter de la contrase�a del usuario
	 * 
	 * @param password Contrase�a del usuario
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
