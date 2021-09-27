

package jugador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import interfaces.ServicioAutenticacionInterface;
import interfaces.ServicioGestorInterface;
import objetos.ListaSincronizada;
import servicios.CallbackJugadorImpl;
import utiles.Utiles;

/**
 * Clase que implementa la interfaz gráfica de 
 * usuario del Jugador
 * 
 * 
 */
public class JugadorUI 
{
	private static Scanner sn = new Scanner(System.in);
	
	/**
	 * Método que muestra el menú del Jugador
	 * 
	 * @param gestor Servicio gestor del Servidor
	 * @param autenticacion Servicio de autenticación
	 * del Servidor
	 * @param callback Servicio callback del Jugador 
	 * @param listaEventos Lista sincronizada para 
	 * manejar eventos
	 */
	public static void menu(ListaSincronizada listaEventos, ServicioGestorInterface gestor, ServicioAutenticacionInterface autenticacion, CallbackJugadorImpl callback)
	{
		String Nombre = null;
		String Password = null;
		String partidaID = null;
		
		String ERROR = "El servidor o la base de datos han dejado de estar disponibles.\n\n";
		
        boolean salir = false;
        boolean logueado = false;
        int opcion;
 
        while (!salir && !logueado) 
        {
        	System.out.println("MENU del Jugador:\n");
            System.out.println("\t1.- Registrar un nuevo Jugador.");
            System.out.println("\t2.- Hacer login.");
            System.out.println("\t3.- Salir.\n");
            System.out.println("Elige una de las opciones:");
 
            try {
                opcion = sn.nextInt();
                sn.nextLine();
                System.out.println();
 
                switch (opcion) 
                {
                    case 1:
                    	
                    	Utiles.clearScreen();
                    	
                    	do
                    	{
                    		System.out.println("Introduce tu nombre:");
                    		Nombre = sn.nextLine();
                    		if(Nombre.isEmpty()) System.out.println("El nombre no puede estar vacío.\n");
                    	}
                    	while(Nombre.isEmpty());
                    	
                    	do
                    	{
                    		System.out.println("\nIntroduce la contraseña:");
                    		Password = sn.nextLine();
                    		if(Password.isEmpty()) System.out.println("La contraseña no puede estar vacía.");
                    	}
                    	while(Password.isEmpty());
                    	
                    	try
                    	{
	                        if (autenticacion.registrar(Nombre, Password))
	                        {
	                        	Utiles.printColor("VERDE", "\n\tUsuario registrado con éxito.\n\n");
	                        }
	                        else 
	                        {
	                        	Utiles.printColor("ROJO", "\n\tError. El usuario ya existe.\n\n"); 
	                        }
                    	}
                    	catch(RemoteException e)
                    	{
                    		Utiles.printColor("ROJO", "\n" + ERROR);
                    		salir = true;
                    	}
                        break;
                        
                    case 2:
                    	
                    	Utiles.clearScreen();
                    	
                    	do
                    	{
                    		System.out.println("Introduce tu nombre:");
                    		Nombre = sn.nextLine();
                    		if(Nombre.isEmpty()) System.out.println("El nombre no puede estar vacío.\n");
                    	}
                    	while(Nombre.isEmpty());
                    	
                    	do
                    	{
                    		System.out.println("\nIntroduce la contraseña:");
                    		Password = sn.nextLine();
                    		if(Password.isEmpty()) System.out.println("La contraseña no puede estar vacía.");
                    	}
                    	while(Password.isEmpty());
                    	
                    	try
                    	{
	                    	if (autenticacion.autenticar(Nombre, Password))
	                    	{
	                    		logueado = true;
	                    		Utiles.printColor("VERDE", "\n\tUsuario logueado con éxito.\n\n");
	                    		gestor.registrarCallback(callback, Nombre);
	                    	}
	                    	else Utiles.printColor("ROJO", "\n\tError. No se ha podido hacer login.\n\n");
                    	}
                    	catch(RemoteException e)
                    	{
                    		Utiles.printColor("ROJO", "\n" + ERROR);
                    		salir = true;
                    	}
                    	break;
                    	
                    case 3:    	
                    	
                        salir = true;
                        break;  
                        
                    default:  
                    	
                    	Utiles.clearScreen();
                    	System.out.println("Error:\n");
                        Utiles.printColor("ROJO", "\tSolo números entre 1 y 3.\n\n");
                }   
            } 
            catch (InputMismatchException e) 
            {
            	System.out.println();
            	Utiles.clearScreen();
            	System.out.println("Error:\n");
                Utiles.printColor("ROJO", "\tDebes insertar un número.\n\n");

                sn.nextLine();
            }
        }
 
        while (logueado) 
        {
        	opcion = 0;
        	
        	System.out.println("MENU de " + Nombre + ":\n");
        	System.out.println("\t1.- Información del Jugador (consultar puntuación histórica).");
        	System.out.println("\t2.- Iniciar una partida.");
        	System.out.println("\t3.- Listar partidas iniciadas a la espera de contrincante.");
        	System.out.println("\t4.- Unirse a una partida ya iniciada.");
        	System.out.println("\t5.- Salir \"Logout\".\n");
        	System.out.println("Elige una de las opciones:");
        	
            try 
            {  
                opcion = sn.nextInt();
            	sn.nextLine();
                System.out.println();
 
                switch (opcion) 
                {
                    case 1:	
                    	
                    	Utiles.clearScreen();
                    	
                    	String puntuacion = null;
                    	
                        try
                        {
                        	puntuacion = gestor.obtenerPuntuacionHistorica(Nombre);
                        }
                        catch(RemoteException e)
                        {
                        	logueado = false;
                        	Utiles.printColor("ROJO", ERROR);
                        }
                        
                        if (puntuacion != null)
                        {
                            System.out.println("Puntuación histórica:\n");
                            Utiles.printColor("VERDE", "\t" + puntuacion + ".\n\n");
                        }
                        
                        break;
                        
                    case 2:
                    	
                    	Utiles.clearScreen();
                    	
                    	try
                    	{
                    		partidaID = gestor.crearPartida(callback, Nombre);                    		
                    	}
                    	catch(RemoteException e)
                    	{
                    		logueado = false;
                    		Utiles.printColor("ROJO", ERROR);
                    	}
                        
                    	if (partidaID != null)
                    	{
                    		try 
                    		{
								jugar(1, partidaID, listaEventos, gestor);
							} 
                    		catch (RemoteException e) 
                    		{
                    			logueado = false;
                    			Utiles.printColor("ROJO", ERROR);
							}
                    	}
                    	
                        break; 
                        
                    case 3:   
                    	
                    	Utiles.clearScreen();
                    	
                    	ArrayList<String> partidas = null;
                    	
                    	try
                    	{
                    		partidas = gestor.listarPartidasEnEspera();	
                    	}
                    	catch(RemoteException e) 
                    	{
                    		logueado = false;
                    		Utiles.printColor("ROJO", ERROR);
                    	}

                    	if (partidas != null)
                    	{
	                    	System.out.println("Partidas iniciadas a la espera de contrincante:\n");
	                    	
	                    	if (partidas.isEmpty()) 
	                    	{
	                    		Utiles.printColor("ROJO", "\tNo existen partidas iniciadas a la espera de contrincante.\n\n");
	                    	}
	                    	else
	                    	{
	                    		for (String rival : partidas) 
	                    		{
	                    			Utiles.printColor("VERDE", "\tID: " + partidas.indexOf(rival) + ". Creador: " + rival + ".\n");
	                    		}
	                    		System.out.println();
	                    	}
                    	}
                        
                    	break; 
                        
                    case 4:
                    	
                    	Utiles.clearScreen();
                    	
                    	ArrayList<String> partidas2 = null;
                    	
                    	try
                    	{
                    		partidas2 = gestor.listarPartidasEnEspera();	
                    	}
                    	catch(RemoteException e) 
                    	{
                    		logueado = false;
                    		Utiles.printColor("ROJO", ERROR);
                    	}
                    	
                       	if (partidas2 != null)
                       	{
                       		System.out.println("Unirse a partida:\n");

                       		if (partidas2.isEmpty())
                       		{
                       			Utiles.printColor("ROJO", "\tNo existen partidas iniciadas a la espera de contrincante.\n\n");
                       		}
                       		else
                       		{                       			
                       			System.out.println("Selecciona la partida (ID):");
                       			
                       			int partida = sn.nextInt();
                       			sn.nextLine();
                       			System.out.println();
                       			
                       			try
                       			{
                       				partidaID = gestor.unirsePartida(Nombre, partida);
                       			}
                       			catch(RemoteException e)
                       			{
                       				logueado = false;
                       				Utiles.printColor("ROJO", ERROR);
                       			}
                       			
                       			if (partidaID != null)
                       			{
                       				try 
                       				{
										jugar(2, partidaID, listaEventos, gestor);
									} 
                       				catch (RemoteException e) 
                       				{
                       					logueado = false;
                       					Utiles.printColor("ROJO", ERROR);
									}                    		
                       			}
                       			else 
                       			{
                       				System.out.println("Error:\n");
                       				Utiles.printColor("ROJO", "\tLa partida seleccionada no existe.\n\n");
                       			}
                       		}
                       	}
                       	
                        break; 
                        
                    case 5:
                    	
                    	Utiles.clearScreen();

                    	try
                    	{
                    		autenticacion.logOut(Nombre);
                    		gestor.desregistrarCallback(Nombre);
                    		                    		
                    		System.out.println("Jugador:\n");
                    		Utiles.printColor("VERDE", "\tUsuario deslogueado con éxito.\n\n");
                    		
                    		logueado = false;
                    		
                    		menu(listaEventos, gestor, autenticacion, callback);
                    	}
                    	catch(RemoteException e)
                    	{
                    		logueado = false;
                    		Utiles.printColor("ROJO", ERROR);
                    	}
                    	
                        break;      
                        
                    default:     
                    	
                    	Utiles.clearScreen();
                    	System.out.println("Error:\n");
                    	Utiles.printColor("ROJO", "\tSolo números entre 1 y 5.\n\n");
                }
            } 
            catch (InputMismatchException e) 
            {	
            	System.out.println();
            	Utiles.clearScreen();
            	System.out.println("Error:\n");
                Utiles.printColor("ROJO", "\tDebes insertar un número.\n\n");

                sn.nextLine();
            }
        }
	}

	/**
	 * Método que accede a la lista sincronizada de eventos
	 * 
	 * @param player Nombre del jugador
	 * @throws RemoteException 
	 */
	public static void jugar(int player, String partidaID, ListaSincronizada listaEventos , ServicioGestorInterface gestor) throws RemoteException
	{
		String disparo = null;
		String barco1 = null;
		String barco2 = null;
		boolean salir = false;
		
        while (!salir)
		{
			String evento = null;
			
			evento = listaEventos.obtenerEvento(gestor);

			switch (evento) 
			{
				case "Colocar barcos.":
					
					barco1 = introducirBarco(barco1, 1);
					System.out.println();
					barco2 = introducirBarco(barco2, 2);
					
					while(!gestor.enviarPosicionBarcos(partidaID, player, barco1, barco2))
					{	
						System.out.println("\nError. Los barcos con la misma orientación no pueden estar juntos.\n");		
						barco2 = introducirBarco(barco2, 2);							
					}
					
					gestor.comenzarPartida(partidaID);
	            	
					break;
					
				case "Comienza el juego.":
					
					System.out.println();
					
					Utiles.clearScreen();
					
					System.out.println("Comienza el juego.");
					
					if (player == 1)
					{
						disparo = introducirDisparo(disparo);
						
						gestor.enviarPosicionDisparo(partidaID, player, disparo);				
					}
					else System.out.println();
					
					break;

				case "Disparo realizado por ti (tocado).":
					
					Utiles.printColor("VERDE", evento + "\n\n");
					
					break;
					
				case "Disparo realizado por ti (agua).":
					
					Utiles.printColor("AZUL", evento + "\n\n");
					
					break;
					
				case "Disparo realizado por el contrario (tocado).":
					
					Utiles.printColor("ROJO", evento + "\n");				
					disparo = introducirDisparo(disparo);
					gestor.enviarPosicionDisparo(partidaID, player, disparo);
					
					break;
					
				case "Disparo realizado por el contrario (agua).":
					
					Utiles.printColor("AZUL", evento + "\n");
					disparo = introducirDisparo(disparo);
					gestor.enviarPosicionDisparo(partidaID, player, disparo);
					
					break;
					
				case "Has hundido un barco.":

					Utiles.printColor("AZUL", evento + "\n\n");
					
					break;
					
				case "Te han hundido un barco.":

					Utiles.printColor("AZUL", evento + "\n");
					disparo = introducirDisparo(disparo);
					gestor.enviarPosicionDisparo(partidaID, player, disparo);
					
					break;

				case "Has hundido la flota (has ganado).":
					
					Utiles.clearScreen();
					System.out.println("Resultado:\n");
					Utiles.printColor("AMARILLO", "\t" + evento + "\n\n");
					salir = true;
					
					break;
					
				case "Te han hundido la flota (has perdido).":
					
					Utiles.clearScreen();
					System.out.println("Resultado:\n");
					Utiles.printColor("ROJO", "\t" + evento + "\n\n");
					salir = true;
					
					break;

				case "Tu rival se ha rendido (has ganado).":
					
					Utiles.clearScreen();
					System.out.println("Resultado:\n");
					Utiles.printColor("AMARILLO", "\t" + evento + "\n\n");
					salir = true;
					
					break;
					
				case "Te has rendido (has perdido).":
					
					Utiles.clearScreen();
					System.out.println("Resultado:\n");
					Utiles.printColor("ROJO", "\t" + evento + "\n\n");
					salir = true;
					
					break;
	
				default:
					
					System.out.printf(evento + "\n\n");
					
					break;		
			}			
		}
	}
	
	/**
	 * Método que introduce un barco
	 * 
	 * @param barco Posición del barco
	 * @param num Número del barco del jugador
	 * @return Barco en formato válido
	 */
	public static String introducirBarco(String barco, int num)
	{
		do 
		{
			System.out.println("Introduce las coordenadas del barco " + num + ":");
	    	barco = sn.nextLine().toUpperCase();
		} 
		while (!comprobarBarco(barco));
    	
		return barco;
	}
	
	/**
	 * Método que valida un barco
	 * 
	 * @param barco Posición del barco
	 * @return Verdadero si el barco tiene el formato adecuado
	 */
	public static boolean comprobarBarco(String barco)
	{	
		String patron = "[a-jA-J]([1-9]|10)[h|H|v|V]";
		
		if (barco.matches(patron))
		{
			return true;
		}
		else
		{
			System.out.println("\nError. Debe indicarse: Fila (A-J), Columna (1-10), Orientación (V/H).\n");
			return false;
		}
	}
	
	/**
	 * Método que introduce el disparo del jugador
	 * 
	 * @param disparo Posición del disparo
	 * @return Disparo en formato válido
	 */
	public static String introducirDisparo(String disparo)
	{
		do
		{
			System.out.println("\nIntroduce las coordenadas del disparo:");
			disparo = sn.nextLine().toUpperCase();
			System.out.println();
		}
		while(!comprobarDisparo(disparo));
		
		return disparo;
	}
	
	/**
	 * Método que valida un disparo
	 * 
	 * @param disparo Posición del disparo
	 * @return Verdadero si el disparo tiene el formato adecuado
	 */
	public static boolean comprobarDisparo(String disparo)
	{
		String patron = "[a-jA-J]([1-9]|10)";
		
		if (disparo.matches(patron) || disparo.equalsIgnoreCase("Me rindo"))
		{
			return true;
		}
		else
		{
			System.out.println("Error. Debe indicarse: Fila (A-J), Columna (1-10).");
			return false;
		}
	}	
}
