

package objetos;

/**
 * Clase que representa una partida del juego
 * Hundir la flota
 * 
 * 
 */
public class Partida 
{
	private String jugador1;
	private String jugador2;
	private Barco barco1Jugador1;
	private Barco barco2Jugador1;
	private Barco barco1Jugador2;
	private Barco barco2Jugador2;
	private int puntJugador1 = 0;
	private int puntJugador2 = 0;
	private int barcosJugador1 = 2;
	private int barcosJugador2 = 2;
	
	private boolean jugando = false;
	
	private boolean jugador1Preparado = false;
	private boolean jugador2Preparado = false;

	/**
	 * Método que setea los barcos de un jugador
	 * 
	 * @param player Nombre del jugador
	 * @param barco1 Primer barco del jugador
	 * @param barco2 Segundo barco del jugador
	 * @return Verdadero si todos los barcos han sido fijados
	 */
	public boolean setBarcos(int player, String barco1, String barco2)
	{	
		if(!comprobarSolapamiento(barco1, barco2))
		{
			return false;
		}
		else 
		{		
			if (player == 1)
			{	
				barco1Jugador1 = new Barco(barco1);
				barco2Jugador1 = new Barco(barco2);
				
				jugador1Preparado  = true;
			}
			else if (player == 2)
			{	
				barco1Jugador2 = new Barco(barco1);
				barco2Jugador2 = new Barco(barco2);	
				
				jugador2Preparado  = true;
			}
			
			return true;
		}
	}
	
	/**
	 * Método que comprueba si un barco ha sido impactado
	 * 
	 * @param player Jugador que dispara
	 * @param disparo Posición del disparo
	 * @return Verdadero si el barco ha sido impactado
	 */
	public boolean comprobarDisparo(int player, String disparo)
	{
		if (player == 1) 
		{
			if(barco1Jugador2.comprobarDisparo(disparo))
			{
				if (barco1Jugador2.isHundido()) setBarcosJugador2(getBarcosJugador2() - 1); 
				return true;
			}
			else if (barco2Jugador2.comprobarDisparo(disparo))
			{
				if (barco2Jugador2.isHundido()) setBarcosJugador2(getBarcosJugador2() - 1); 
				return true;
			}
			else return false;
		}

		else if (player == 2) 
		{
			if(barco1Jugador1.comprobarDisparo(disparo))
			{
				if (barco1Jugador1.isHundido()) setBarcosJugador1(getBarcosJugador1() - 1); 
				return true;
			}
			else if (barco2Jugador1.comprobarDisparo(disparo))
			{
				if (barco2Jugador1.isHundido()) setBarcosJugador1(getBarcosJugador1() - 1); 
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	/**
	 * Método que comprueba si un jugador tiene barcos hundidos
	 * 
	 * @param player Nombre del jugador
	 * @return Verdadero si el rival tiene hundido algún barco
	 */
	public boolean comprobarHundidos(int player)
	{
		if (player == 1) return (barco1Jugador2.isHundido() || barco2Jugador2.isHundido());

		else if (player == 2) return (barco1Jugador1.isHundido() || barco2Jugador1.isHundido());
		
		else return false;
	}
	
	/**
	 * Método que suma puntos a un jugador
	 * 
	 * @param player Nombre del jugador
	 * @param cantidad Cantidad de puntos
	 */
	public void sumarPuntos(int player, int cantidad)
	{
		if (player == 1) puntJugador1 += cantidad;

		else if (player == 2) puntJugador2 += cantidad;
	}
	
	/**
	 * Método que comprueba si se está jugando la partida
	 * 
	 * @return verdadero si se está jugando la partida
	 */
	public boolean isJugando() {
		return jugando;
	}

	/**
	 * Setter de la variable jugando
	 * 
	 * @param jugando Variable que indica si la partida se
	 * está jugando
	 */
	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}

	/**
	 * Getter del nombre del jugador 1
	 * 
	 * @return Nombre del jugador 1
	 */
	public String getJugador1() {
		return jugador1;
	}

	/**
	 * Setter del nombre del jugador 1
	 * 
	 * @param jugador1 Nombre del jugador 1
	 */
	public void setJugador1(String jugador1) {
		this.jugador1 = jugador1;
	}

	/**
	 * Getter del nombre del jugador 2
	 * 
	 * @return Nombre del jugador 2
	 */
	public String getJugador2() 
	{
		return jugador2;
	}

	/**
	 * Setter del nombre del jugador 2
	 * 
	 * @param jugador2 Nombre del jugador 2
	 */
	public void setJugador2(String jugador2) 
	{
		this.jugador2 = jugador2;
	}

	/**
	 * Getter de la puntuación del jugador 1
	 * 
	 * @return Puntuación del jugador 1
	 */
	public int getPuntJugador1() 
	{
		return puntJugador1;
	}

	/**
	 * Setter de la puntuación del jugador 1
	 * 
	 * @param puntJugador1 Puntuación del jugador 1
	 */
	public void setPuntJugador1(int puntJugador1) 
	{
		this.puntJugador1 = puntJugador1;
	}

	/**
	 * Getter de la puntuación del jugador 2
	 * 
	 * @return Puntuación del jugador 2
	 */
	public int getPuntJugador2() 
	{
		return puntJugador2;
	}

	/**
	 * Setter de la puntuación del jugador 2
	 * 
	 * @param puntJugador2 Puntuación del jugador 2
	 */
	public void setPuntJugador2(int puntJugador2) 
	{
		this.puntJugador2 = puntJugador2;
	}

	/**
	 * Getter del número de barcos del jugador 1
	 * 
	 * @return Número de barcos del jugador 1
	 */
	public int getBarcosJugador1() 
	{
		return barcosJugador1;
	}

	/**
	 * Setter del número de barcos del jugador 1
	 * 
	 * @param barcosJugador1 Número de barcos del jugador 1
	 */
	public void setBarcosJugador1(int barcosJugador1) 
	{
		this.barcosJugador1 = barcosJugador1;
	}

	/**
	 * Getter del número de barcos del jugador 2
	 * 
	 * @return Número de barcos del jugador 2
	 */
	public int getBarcosJugador2() 
	{
		return barcosJugador2;
	}

	/**
	 * Setter del número de barcos del jugador 2
	 * 
	 * @param barcosJugador2 Número de barcos del jugador 2
	 */
	public void setBarcosJugador2(int barcosJugador2) 
	{
		this.barcosJugador2 = barcosJugador2;
	}
	
	/**
	 * Método que comprueba si dos barcos se solapan. Dos 
	 * barcos se solapan si tienen la misma dirección y 
	 * coinciden, o bien están juntos
	 * 
	 * @param barco1 Posición del barco 1
	 * @param barco2 Posición del barco 2
	 * @return Verdadero si los barcos no solapan
	 */
	public boolean comprobarSolapamiento(String barco1, String barco2)
	{	
		Barco primerBarco = new Barco(barco1);
		Barco segundoBarco = new Barco(barco2);
		
		if(barco1.charAt(barco1.length() - 1) == barco2.charAt(barco2.length() - 1))
		{
			for (String casilla : primerBarco.getCasillas())
			{
				if(segundoBarco.getCasillasProhibidas().contains(casilla))
				{
					return false;
				}
			}
		}
			
		return true;
	}

	/**
	 * Getter del número de jugadores preparados 
	 * 
	 * @return Número de jugadores preparados
	 */
	public boolean checkJugadoresPreparados() 
	{
		return (jugador1Preparado && jugador2Preparado);
	}

}
