

package objetos;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ServicioGestorInterface;

/**
 * Clase que modela una lista sincronizada
 * 
 * 
 */
public class ListaSincronizada 
{
   private List <String> lista;
   
   /**
    * Constructor de la clase
    */
   public ListaSincronizada ()
   {
	   lista = new ArrayList<String>();
   }
   
   /**
    * Método que añade un evento
    * 
    * @param dato Evento que se añade
    */
   public synchronized void agregarElemento(String dato)
   {
	   lista.add(dato);
	   notifyAll();
   }
  
   /**
    * Método que obtiene un evento de la lista
    * 
    * @return dato Evento de la lista
    * @throws RemoteException 
    * @throws InterruptedException
    */
   public synchronized String obtenerEvento(ServicioGestorInterface gestor) throws RemoteException
   {
	   while(lista.isEmpty())
	   {
		   try 
		   {
			   wait(1000);
			   
			   gestor.ping();

		   } 
		   catch (InterruptedException e) 
		   {
			   System.out.println("Error. Proceso interrumpido.\n");
		   }
	   }
      
	   String dato = lista.get(0);
	   lista.remove(0);
      
	   return dato;
   } 
}