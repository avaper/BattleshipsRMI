

package objetos;

import java.util.ArrayList;

/**
 * Clase que representa un barco
 * 
 * 
 */
public class Barco 
{
	private String proa;
	private String medio;
	private String popa;
	private boolean tocadoProa;
	private boolean tocadoMedio;
	private boolean tocadoPopa;
	private ArrayList <String> casillas = new ArrayList<String>();
	private ArrayList <String> casillasProhibidas = new ArrayList<String>();
	
	/**
	 * Constructor de la clase
	 * 
	 * @param posicion Posición del barco
	 */
	public Barco(String posicion) 
	{
		char filaProa;
		int columnaProa;
		char orientacion;
		
		filaProa = posicion.charAt(0);
		
		if (posicion.length() < 4)
		{
			columnaProa = Character.getNumericValue(posicion.charAt(1));
			orientacion = posicion.charAt(2);
		}
		else
		{
			columnaProa = Character.getNumericValue(posicion.charAt(1))*10 + Character.getNumericValue(posicion.charAt(2));
			orientacion = posicion.charAt(3);	
		}
		
		char filaMedio = 0;
		char filaPopa = 0;
		int columnaMedio = 0;
		int columnaPopa = 0;
		
		if (orientacion == 'H')
		{
			int direccion = 1;
			
			if (columnaProa < 9)
			{				
				columnaMedio = columnaProa + direccion;
				columnaPopa = columnaMedio + direccion;
			}
			else
			{
				columnaMedio = columnaProa - direccion;
				columnaPopa = columnaMedio - direccion;
			}
			filaMedio = filaProa;
			filaPopa = filaProa;
		}
		else if (orientacion == 'V')
		{
			char direccion = 1;
			
			if (Character.toUpperCase(filaProa) < 'I') 
			{
				filaMedio = (char) (filaProa + direccion);
				filaPopa = (char) (filaMedio + direccion);
			}
			else
			{
				filaMedio = (char) (filaProa - direccion);
				filaPopa = (char) (filaMedio - direccion);
			}

			columnaMedio = columnaProa;
			columnaPopa  = columnaProa;
		}
		
		proa  = Character.toString(filaProa) + columnaProa;
		medio = Character.toString(filaMedio) + columnaMedio;
		popa  = Character.toString(filaPopa) + columnaPopa;
		
		casillas.add(proa);
		casillas.add(medio);
		casillas.add(popa);	
		
		casillasProhibidas.add(proa);
		casillasProhibidas.add(medio);
		casillasProhibidas.add(popa);
		
		if (orientacion == 'H')
		{
			if (Character.toUpperCase(filaProa) > 'A')
			{
				String proaArriba  = "proaUp" + Character.toString((char) (filaProa - 1)) + columnaProa;
				String medioArriba = "medioUp" + Character.toString((char) (filaMedio - 1)) + columnaMedio;
				String popaArriba  = "popaUp" + Character.toString((char) (filaPopa - 1)) + columnaPopa;
					
				casillasProhibidas.add(proaArriba);
				casillasProhibidas.add(medioArriba);
				casillasProhibidas.add(popaArriba);	
			}
			
			if (Character.toUpperCase(filaProa) < 'J')
			{
				String proaAbajo  = "proaDown" + Character.toString((char) (filaProa + 1)) + columnaProa;
				String medioAbajo = "medioDown" + Character.toString((char) (filaMedio + 1)) + columnaMedio;
				String popaAbajo  = "popaDown" + Character.toString((char) (filaPopa + 1)) + columnaPopa;				

				casillasProhibidas.add(proaAbajo);
				casillasProhibidas.add(medioAbajo);
				casillasProhibidas.add(popaAbajo);					
			}
			
			if (columnaProa > 1)
			{
				if (columnaProa < 8)
				{
					String proaIzquierda = "proaIzq" + Character.toString(filaProa) + (columnaProa - 1);
					
					casillasProhibidas.add(proaIzquierda);
				}
				else
				{
					String popaIzquierda = "popaIzq" + Character.toString(filaPopa) + (columnaPopa - 1);
					
					casillasProhibidas.add(popaIzquierda);
				}
			}
			
			if (columnaProa < 8)
			{
				String popaDerecha = "popaDer" + Character.toString(filaPopa) + (columnaPopa + 1);
				
				casillasProhibidas.add(popaDerecha);
			}			
		}
		else if (orientacion == 'V')
		{
			if (Character.toUpperCase(filaProa) > 'A')
			{
				if (Character.toUpperCase(filaProa) < 'H')
				{
					String proaArriba = "proaUp" + Character.toString((char) (filaProa - 1)) + columnaProa;
				
					casillasProhibidas.add(proaArriba);
				}
				else
				{
					String popaArriba = "popaUp" + Character.toString((char) (filaPopa - 1)) + columnaPopa;
					
					casillasProhibidas.add(popaArriba);
				}
			}
			
			if (Character.toUpperCase(filaProa) < 'H')
			{
				String popaAbajo = "popaDown" + Character.toString((char) (filaPopa + 1)) + columnaPopa;
				
				casillasProhibidas.add(popaAbajo);
			}
			
			if (columnaProa > 1)
			{
				String proaIzquierda  = "proaIzq" + Character.toString(filaProa) + (columnaProa - 1);
				String medioIzquierda = "medioIzq" + Character.toString(filaMedio) + (columnaMedio - 1);
				String popaIzquierda  = "popaIzq" + Character.toString(filaPopa) + (columnaPopa - 1);
					
				casillasProhibidas.add(proaIzquierda);
				casillasProhibidas.add(medioIzquierda);
				casillasProhibidas.add(popaIzquierda);	
			}
			
			if (columnaProa < 8)
			{
				String proaDerecha  = "proaDer" + Character.toString(filaProa) + (columnaProa + 1);
				String medioDerecha = "medioDer" + Character.toString(filaMedio) + (columnaMedio + 1);
				String popaDerecha  = "popaDer" + Character.toString(filaPopa) + (columnaPopa + 1);
				
				casillasProhibidas.add(proaDerecha);
				casillasProhibidas.add(medioDerecha);
				casillasProhibidas.add(popaDerecha);	
			}
		}
	}

	/**
	 * Método que comprueba si el barco ha recibido un 
	 * impacto
	 * 
	 * @param disparo Posición del disparo
	 * @return Verdadero si el barco ha sido impactado
	 */
	public boolean comprobarDisparo(String disparo)
	{
		boolean tocado = false;
		
		if (!isHundido())
		{
			if (disparo.equalsIgnoreCase(proa))
			{
				if (!tocadoProa)
				{
					tocadoProa = true;
					tocado = true;
				}
			}
			else if (disparo.equalsIgnoreCase(medio))
			{
				if (!tocadoMedio)
				{
					tocadoMedio = true;
					tocado = true;
				}
			}		
			else if (disparo.equalsIgnoreCase(popa))
			{
				if (!tocadoPopa)
				{
					tocadoPopa = true;
					tocado = true;
				}
			}
		}
		
		return tocado;
	}

	/**
	 * Getter de casillas del barco
	 * 
	 * @return Lista de casillas en las que 
	 * se ubica el barco
	 */
	public ArrayList<String> getCasillas()
	{
		return casillas;
	}
	
	/**
	 * Getter de casillas prohibidas para colocar
	 * otro barco
	 * 
	 * @return Lista de casillas prohibidas para
	 * colocar otro barco
	 */
	public ArrayList<String> getCasillasProhibidas()
	{
		return casillasProhibidas;
	}
	
	/**
	 * Método que consulta el estado del barco
	 * 
	 * @return Verdadero si el barco está hundido
	 */
	public boolean isHundido()
	{
		return (tocadoProa && tocadoMedio && tocadoPopa);
	}
}