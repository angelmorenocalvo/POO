/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
package es.inf.uva.poo.pickingpointssystem;
import java.util.ArrayList;

import es.inf.uva.poo.PackageLocker.PackageLocker;
import es.uva.inf.poo.maps.GPSCoordinate;

/** Herramienta que nos permite controlar el manejo de los package locker
 * funcionalidad de hacer modificaciones sobre package locker.
 * Con ayuda de esta clase obtendremos informacion referente a los packagelocker
 * @author OMEN
 *
 */

public class PickingPointsSystem {
	ArrayList<PackageLocker> listaPackageLockers;
	
	/**
	 * Inicializa el arraylist de packagelockers
	 */
	
	public PickingPointsSystem(){
		listaPackageLockers = new ArrayList<PackageLocker>();
	}
	
	/**
	 * Consulta el numero de packagelocker 
	 * @param listaPackageLockers el array list
	 * @return el tamano del array list que representa un entero equivalente al numero de packagelockers
	 */

	public int getNumeroPackageLocker(ArrayList<PackageLocker> listaPackageLockers) {
		return listaPackageLockers.size();
	}
	
	/**
	 * A�adir un nuevo packagelocker al arraylist
	 * @param nuevo packagelocker a introducir
	 * @param listaPackageLockers arraylist
	 * @return el arraylist con el packagelocker a�adido
	 * @throws IllegalArgumentException si {nuevo==null}
	 */
		
	public ArrayList<PackageLocker> addPackageLocker(PackageLocker nuevo, ArrayList<PackageLocker> listaPackageLockers) {
		if(nuevo==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		listaPackageLockers.add(nuevo);
		return listaPackageLockers;
	}
	
	/**
	 * Eliminar un packagelocker por su identificador
	 * @param codigoIdentificador es el codigo del packagelocker que queremos eliminar
	 * @param listaPackageLockers el arraylist
	 * Recorremos el arraylist buscando un packagelocker que tenga el mismo
	 * codigo que el introducido para eliminar ese packagelocker
	 * @throws IllegalArgumentException si {codigoIdentificador==null}
	 * @throws IllegalArgumentException si {bandera==false}
	 */
	
	public void removePackageLocker(String codigoIdentificador, ArrayList<PackageLocker> listaPackageLockers) {
		if(codigoIdentificador==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		boolean bandera = false;
		for(int x=0; x < listaPackageLockers.size(); x++){
			if((listaPackageLockers.get(x)).getIdentificador()==codigoIdentificador) {
				listaPackageLockers.remove(x);	
				bandera = true;
			}
		}
		if(bandera==false)
			throw new IllegalArgumentException("no existe ningun paquete con ningun ");
	}
	
	/**
	 * Consultar si existe el packagelocker
	 * @param codigoIdentificador para buscar el packagelocker a comprobar
	 * @param listaPackageLockers el arraylist
	 * @return un valor booleano que va a ser true si existe ese paquete y false si no existe
	 * Para ello recorremos el arraylist a la vez que comprobamos si el codigo dado
	 * coincide con alguno de los packagelocker
	 * @throws IllegalArgumentException si {codigoIdentificador==null}
	 */
	
	public boolean existePackageLocker(String codigoIdentificador, ArrayList<PackageLocker> listaPackageLockers) {
		if(codigoIdentificador==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		for(int x=0; x < listaPackageLockers.size(); x++){
			if((listaPackageLockers.get(x)).getIdentificador()==codigoIdentificador) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Obtener una lista con los packagelocker operativos
	 * @param listaPackageLockers el array list
	 * @return un array con los packagelockers operativos
	 * Para ello cremos un array en el que vamos a ir almacenando los packagelocker operativos
	 * que seran los que tengan su estado = true
	 */
	
	public PackageLocker [] packageLockerOperativos(ArrayList<PackageLocker> listaPackageLockers){
		ArrayList<PackageLocker> listaPackageLockersOperativos = new ArrayList<PackageLocker>();
		for(int x=0; x < listaPackageLockers.size(); x++){
			if((listaPackageLockers.get(x)).getEstado()==true) {
				listaPackageLockersOperativos.add(listaPackageLockers.get(x));
			}
		}
		return listaPackageLockersOperativos.toArray(new PackageLocker[listaPackageLockersOperativos.size()]);
	}
	
	/**
	 * Obtener una lista con los packagelocker fuera de servicio
	 * @param listaPackageLockers el arraylist
	 * @return array con los packagelockers fuera de servicio
	 * Para ello creamos un array en el que vamos a ir guardando los packagelocker fuera de servicio
	 * que seran los que tengan su estado = false
	 */
	
	public PackageLocker [] packageLockerFueraDeServicio(ArrayList<PackageLocker> listaPackageLockers) {
		ArrayList<PackageLocker> listaPackageLockersFueraDeServicio = new ArrayList<PackageLocker>();
		for(int x=0; x < listaPackageLockers.size(); x++){
			if((listaPackageLockers.get(x)).getEstado()==false) {
				listaPackageLockersFueraDeServicio.add(listaPackageLockers.get(x));			
			}
		}
		return listaPackageLockersFueraDeServicio.toArray(new PackageLocker[listaPackageLockersFueraDeServicio.size()]);
	}
	
	/**
	 * Obtener una lista con los packagelocker que se encuentran a una distancia menor o igual de una coordenada
	 * @param d double que indica la distancia a la que quieres establecer el radio
	 * @param listaPackageLockers el arraylist
	 * @param coordenadas punto desde el que quieres buscar los packagelocker a una distancia menor o igual
	 * @return un array con los packagelocker en ese radio
	 * Para ello recorremos el arraylist comprobando que packagelocker se encuentran a una distancia menor o igual
	 * que la distancia establecida al punto de coordenadas.
	 * @throws IllegalArgumentException si {coordenadas==null}
	 * @throws IllegalArgumentException si {d<0}
	 */
	
	public PackageLocker [] packageLockerEnUnRadio(double d, ArrayList<PackageLocker> listaPackageLockers, GPSCoordinate coordenadas) {
		if(coordenadas==null)
			throw new IllegalArgumentException("Las coordenadas no pueden ser nulas");
		if(d<0)
			throw new IllegalArgumentException("La distancia no puede ser negativa");
		ArrayList<PackageLocker> listaPackageLockersEnUnRadio = new ArrayList<PackageLocker>();
		for(int x=0; x < listaPackageLockers.size(); x++){			
			if(((listaPackageLockers.get(x)).getCoordenadas().getDistanceTo(coordenadas))<=d){
				listaPackageLockersEnUnRadio.add(listaPackageLockers.get(x));
			}
		}
		return listaPackageLockersEnUnRadio.toArray(new PackageLocker[listaPackageLockersEnUnRadio.size()]);
	}
	
	/**
	 * Obtener una lista con los packagelocker que tienen alguna taquilla vacia
	 * @param listaPackageLockers el arraylist
	 * @return un array con los packagelocker con alguna taquilla vacia
	 * Para ello recorremos el arraylist comprobando si taquillas vacias es mayor que 0, en el caso
	 * de que lo fuera guardariamos ese packagelocker en el array
	 */
	
	public PackageLocker [] packageLockerConTaquillasVacias(ArrayList<PackageLocker> listaPackageLockers) {
		ArrayList<PackageLocker> listaPackageLockersConTaquillasVacias = new ArrayList<PackageLocker>();
		for(int x=0; x < listaPackageLockers.size(); x++){
			if((listaPackageLockers.get(x)).getTaquillasVacias()>0){
				listaPackageLockersConTaquillasVacias.add(listaPackageLockers.get(x));
			}
		}
		return listaPackageLockersConTaquillasVacias.toArray(new PackageLocker[listaPackageLockersConTaquillasVacias.size()]);
	}
}
