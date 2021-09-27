

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
	 * @param pass Contraseña del usuario
	 * @param pts Puntos del usuario
	 */
	public Usuario(String pass, int pts) 
	{
		setPassword(pass);
		setPuntos(pts);
	}
	
	/**
	 * Método que suma puntos al usuario
	 * 
	 * @param pts Puntos que se sumarán
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
	 * Getter de la contraseña del usuario
	 * 
	 * @return Contraseña del usuario
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * Setter de la contraseña del usuario
	 * 
	 * @param password Contraseña del usuario
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
}
