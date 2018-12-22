/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */

package es.inf.uva.poo.PackageLocker;
import java.time.*;
import es.inf.uva.poo.paquete.Package;
import es.uva.inf.poo.maps.*;

/**
 * Es un tipo de dato que hace referencia al agrupamiento de paquetes.
 * En el podemos obtener informacion de la clase package, y movilizar los paquetes, a su vez
 * tiene funciones que ofrecen informacion sobre el packagelocker como su localizacion.
 * Con esta clase podemos controlar, modificar y movilizar los paquetes creados a traves 
 * de la clase package
 * @author OMEN
 *
 */

public class PackageLocker {

	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private String identificador; 
	private boolean operativo; // estado operativo o fuera de servicio
	private GPSCoordinate localizacionPackageLocker;
	boolean [] taquillasOperativas;
	private Package [] taquillas;
	
	/**
	 * Construye un packagelocker
	 * En este constructo no damos la opcion de dar un valor al estado, lo inicializamos
	 * directamente a false
	 * @param numeroTaquillas el numero total de taquillas que tendra el packagelocker
	 * @param localizacion donde se encuentra localizado el paquete
	 * Para ello hacemos uso de la clase GPSCoordinate
	 * @param codigo un codigo que identifica los diferentes packagelocker
	 * @param apertura hace referencia a la hora de apertura de dicho packagelocker
	 * @param cierre hace referencia a la hora de cierre de packagelocker
	 * Hemos hecho uso de la clase LocalTime
	 */
	
	public PackageLocker(int numeroTaquillas, GPSCoordinate localizacion, String codigo, LocalTime apertura, LocalTime cierre) {
		if(apertura == null) 
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		if(cierre == null) 
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		if(apertura.compareTo(cierre)>0) {
			throw new IllegalArgumentException("La hora de apertura no puede ser mayor que la de cierre.");
		}
		if (numeroTaquillas<0)
			throw new IllegalArgumentException("el numero debe ser positivo.");
		if(codigo == null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		if(localizacion == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		horarioApertura=apertura;
		horarioCierre=cierre;
		identificador = codigo;
		localizacionPackageLocker = localizacion;
		taquillas = new Package[numeroTaquillas];
		taquillasOperativas = new boolean[numeroTaquillas];
	}

	/**
	 * Constructor para dar valor al estado del packagelocker
 * @param numeroTaquillas el numero total de taquillas que tendra el packagelocker
	 * @param localizacion donde se encuentra localizado el paquete
	 * Para ello hacemos uso de la clase GPSCoordinate
	 * @param codigo un codigo que identifica los diferentes packagelocker
	 * @param apertura hace referencia a la hora de apertura de dicho packagelocker
	 * @param cierre hace referencia a la hora de cierre de packagelocker
	 * @param estado comprueba si esta abierto, es decir entre horario apertura y de cierre
	 */
	
	public PackageLocker(int numeroTaquillas, GPSCoordinate localizacion, boolean estado, String codigo, LocalTime apertura, LocalTime cierre) {
		if(codigo == null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		if(apertura == null) 
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		if(cierre == null) 
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		if(localizacion == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		if(apertura.compareTo(cierre)>0) {
			throw new IllegalArgumentException("La hora de apertura no puede ser mayor que la de cierre.");
		}
		horarioApertura=apertura;
		horarioCierre=cierre;
		this.operativo = estado;
		this.identificador = codigo;
		localizacionPackageLocker = localizacion;
		taquillas = new Package[numeroTaquillas];
		taquillasOperativas = new boolean[numeroTaquillas];
	}
	
	/**
	 * modificar el valor de la variable estado
	 * @param estado para saber si esta abierto o cerrado, es decir entre horario apertura y de cierre
	 */
	
	public void setEstado(boolean estado) {
		operativo=estado;
	}
	
	/**
	 * consultar el valor del estado
	 * @return operativo valor del estado 
	 */
	
	public boolean getEstado() {
		return operativo;
	}
	
	/**
	 * modificar el valor del codigo identificador
	 * @param codigo
	 */
	
	public void setIdentificador(String codigo) {
		if(codigo == null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");		
		identificador=codigo;
	}
	
	/**
	 * Consultar el codigo identificador 
	 * @return identificador
	 */
	
	public String getIdentificador() {
		return identificador;
	}
	
	/**
	 * modifica el valor de las coordenadas en las que se encuentra un packagelocker
	 * @param x
	 * @param y
	 */
	
	public void setCoordenadas(GPSCoordinate localizacion) {
		if(localizacion == null)
			throw new IllegalArgumentException("Las coordenadas no pueden tomar valor null");
		
		localizacionPackageLocker = localizacion;
	}
	
	/**
	 * Consulta el valor de las coordenadas 
	 * @return localizacionPackageLocker coordenadas del packagelocker
	 */
	
	public 	GPSCoordinate getCoordenadas(){
		return localizacionPackageLocker;
	}
		
	/**
	 * modifica el valor de la hora de apertura del package locker
	 * @param variable tipo Time
	 * @throws IllegalArgumentException si {apertura==null}
	 */
	
	public void setHoraApertura(LocalTime apertura){
		if(apertura == null) 
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		
		
		horarioApertura = apertura;
	}
	
	/**
	 * Consulta el valor de la hora de apertura del package locker
	 * @return una hora de la clase Time
	 */
	
	public LocalTime getHoraApertura() {	
		return horarioApertura;
	}
	
	/**
	 * Consulta el valor de la hora de cierre del package locker
	 * @return una hora de la clase Time
	 */
	
	public Package getPaquete(int numeroCelda) { // MIRAR SI FALLA COMO A PABLO
		if (numeroCelda<0)
			throw new IllegalArgumentException("el numero debe ser positivo.");
		if (numeroCelda> taquillas.length)
			throw new IllegalArgumentException("el numero debe estar en el rango de taquillas.");
		if(taquillas[numeroCelda-1] == null)
			throw new AssertionError("La celda no contiene paquete.");
		
		return taquillas[numeroCelda-1];
	}
	
	/**
	 * Consulta la hora de cierre
	 * @return variable localtime horarioCierre
	 */
	
	public LocalTime getHoraCierre() {
		return horarioCierre;
	}

	/**
	 * modifica el valor de la hora de cierre del package locker
	 * @param variable tipo Time
	 */
	
	public void setHoraCierre(LocalTime cierre) {
		if(cierre == null) 
			throw new IllegalArgumentException("llamada incorrecta a la hora de cierre, horaDeCierre == null");
		
		horarioCierre = cierre;
	}

	/**
	 * Consulta el numero de las taquillas de las que consta el package locker
	 * @return valor entero positivo (tendremos que ajustar esto para que sea asi)
	 */
	
	public int getnumeroTaquillas() {
		return taquillas.length;
	}
	
	/**
	 * Consulta el numero de las taquillas que se encuentran vacias en  el package locker
	 * @return valor entero positivo (tendremos que ajustar esto para que sea asi)
	 */
	
	public int getTaquillasVacias(){
		int contador = 0;
		for(Package i : taquillas) {
			if (i == null)
				contador++;
		}
		return contador;
	}

	/**
	 * Consulta el numero de las taquillas que se encuentran ocupadas en  el package locker
	 * @return valor entero positivo
	 */
	
	public int getTaquillasOcupadas() {
		return taquillas.length - getTaquillasVacias();
	}
	
	/**
	 *Consulta el numero de las taquillas que se encuentran operativas 
	 * @return valor entero con el numero de taquillas operativas
	 */
	
	public int getTaquillasOperativas() {
		int numeroTaquillasOperativas = 0;
		for(boolean i: taquillasOperativas) {
			if(!i)
				numeroTaquillasOperativas++;
		}
		return numeroTaquillasOperativas;
	}
	
	/**
	 *Consulta el numero de las taquillas que se encuentran inoperativas 
	 * @return valor entero con el numero de taquillas inoperativas
	 */
	
	public int getTaquillasInoperativas() {
		return taquillasOperativas.length - getTaquillasOperativas() ;
	}
	
	/**
	 * Cambia el estado de una taquilla de operativa a inoperativa
	 * @throws IllegalArgumentException si {numeroCelda > taquillas.length}
	 * @throws IllegalArgumentException si {numeroCelda<0}
	 */
	
	public void celdaInoperativa(int numeroCelda) {  // VER SI HAY ALGO MEJOR
		if (numeroCelda<0)
			throw new IllegalArgumentException("el numero debe ser positivo.");
		if (numeroCelda> taquillas.length)
			throw new IllegalArgumentException("el numero debe estar en el rango de taquillas.");
		taquillasOperativas[numeroCelda] = true;
	}
	
	/**
	 * Cambia el estado de una taquilla de inoperativa a operativa
	 * @throws IllegalArgumentException si {numeroCelda<0}
	 * @throws IllegalArgumentException si {numeroCelda>taquillas.length}
	 */
	
	public void celdaOperativa(int numeroCelda) {
		if (numeroCelda<0)
			throw new IllegalArgumentException("el numero debe ser positivo.");
		if (numeroCelda> taquillas.length)
			throw new IllegalArgumentException("el numero debe estar en el rango de taquillas.");
		taquillasOperativas[numeroCelda] = false;
	}
	
	/**
	 * Da la posibilidad de introducir un paquete en una taquilla del packagelocker
	 * @param x posicion es la que vamos a introducir dicho paquete
	 * Habra que restar uno a ese numero puesto que el array empieza desde 0
	 * @param paqueteAIntroducir
	 * @throws IllegalArgumentException si {numeroCelda<0}
	 * @throws IllegalArgumentException si {numeroCelda>taquillas.length}
	 * @throws IllegalArgumentException si {taquillas[numeroCelda-1]!=null}
	 * @throws IllegalArgumentException si {taquillasOperativas[numeroCelda-1]}
	 */
	
	public void asignarTaquilla(int numeroCelda, Package paqueteAIntroducir) {
		if (numeroCelda<0)
			throw new IllegalArgumentException("el numero debe ser positivo.");
		if (numeroCelda> taquillas.length)
			throw new IllegalArgumentException("el numero debe estar en el rango de taquillas.");
		if(taquillas[numeroCelda-1] != null)
			throw new AssertionError("La taquilla ya contiene un paquete, borralo antes");
		if(taquillasOperativas[numeroCelda-1])
			throw new AssertionError("La taquilla no esta operativa");
		taquillas[numeroCelda-1] = paqueteAIntroducir;
	}
	
	/**
	 * Comprueba si una celda tiene un paquete
	 * @param numeroCelda celda a comprobar
	 * @return la posicion del array de ese numero de celda - 1 ya que el array empieza en 0
	 * @throws IllegalArgumentException si {numeroCelda<0}
	 * @throws IllegalArgumentException si {numeroCelda>taquillas.length}
	 */
	
	public Package mirarEnTaquilla(int numeroCelda) {
		if (numeroCelda<0)
			throw new IllegalArgumentException("el numero debe ser positivo.");
		if (numeroCelda> taquillas.length)
			throw new IllegalArgumentException("el numero debe estar en el rango de taquillas.");
		return taquillas[numeroCelda-1];
	}
	
	/**
	 * Comprueba con un codigo dado en que taquilla se encuentra dicho paquete
	 * @param CodigoAComprobar 
	 */
	
	public double getDistanciaAOtroPackageLocker(PackageLocker otro) {
		return (localizacionPackageLocker.getDistanceTo(otro.getCoordenadas()));
	}
	
	/**
	 * Busca un paquete a traves de su codigo
	 * @param codigoAComprobar
	 * @return el paquete buscado
	 * Para ello recorre el array comparando cada posicion con el codigo del paquete buscado
	 * @throws IllegalArgumentException si no existe paquete con ese codigo
	 */
	
	public Package buscarPaquete(String codigoAComprobar) {
		if (codigoAComprobar == null)
			throw new IllegalArgumentException("el codigo no puede ser null");
		
		for(Package i : taquillas) {
			if (i != null)
				
				if(i.getIdentificador() == codigoAComprobar) {
					if (taquillasOperativas[posicionPackage(i)])
						throw new AssertionError("La taquilla esta deshabilitada");
					return i;
				}
		}
		throw new IllegalArgumentException("El paquete no exite");

	}
	
	/**
	 * Sacamos un paquete de una taquilla buscandolo por su codigo
	 * @param codigoAComprobar
	 * @throws IllegalArgumentException si no existe paquete con ese codigo
	 */
	
	public void quitarPaqueteTaquilla(String codigoAComprobar) { 
		if(codigoAComprobar == null) {
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		}
		boolean bandera = false;
		for(int i = 0; i<taquillas.length;i++) {
			if(taquillas[i] != null)
				if (taquillas[i].getIdentificador().equals(codigoAComprobar)) {
					taquillas[i] = null;
					bandera = true;
				}
		}
		if(bandera == false)
			throw new IllegalArgumentException("El paquete no exite");
	}
	
	/**
	 * 
	 * @param codigoDeBusqueda
	 */
	
	public void paqueteRecogidoCliente(String codigoDeBusqueda) {
		if(codigoDeBusqueda == null) {
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		}
		for(Package i : taquillas) { // HABRA QUE MIRAR QUE NO ESTE RECOGIDO NI QUE ESTE MANDADO A CENTRAL
			if(i != null)
				
				if (i.getIdentificador() == codigoDeBusqueda ) {
					if (taquillasOperativas[posicionPackage(i)])
						throw new AssertionError("La taquilla esta deshabilitada, no puede ser recogido por el cliente");
					if(taquillas[posicionPackage(i)].getDevuelto())
						throw new AssertionError("El paquete ya ha sido recogido por el cliente");
					if(taquillas[posicionPackage(i)].getDevuelto())
						throw new AssertionError("El paquete se ha devuelto al centro");
					i .setRecogido(true);
				}
		}
	}
	public void paqueteMandadoCentral(String codigoDeBusqueda) {
		if(codigoDeBusqueda == null) {
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		}
		for(Package i : taquillas) {
			if(i != null)
				if (i.getIdentificador() == codigoDeBusqueda) {
					if (taquillasOperativas[posicionPackage(i)])
						throw new AssertionError("La taquilla esta deshabilitada, no puede ser recogido por el cliente");
					if(taquillas[posicionPackage(i)].getDevuelto())
						throw new AssertionError("El paquete ya ha sido recogido por el cliente");
					if(taquillas[posicionPackage(i)].getDevuelto())
						throw new AssertionError("El paquete se ha devuelto al centro");
					i .setDevuelto(true);
				}
		}
		throw new IllegalArgumentException("El paquete no exite");

	}
	
	/**
	 * Elimina un paquete a traves de su codigo
	 * @param codigoPaqueteAEliminar
	 * @throws IllegalArgumentException si {codigopaqueteaeliminar==null}
	 */
	
	public void eliminarPaquete(String codigoPaqueteAEliminar) {	
		if(codigoPaqueteAEliminar == null) {
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
		}
		for(int i=0; i<taquillas.length;i++) {
			if (taquillas[i]!=null && !taquillasOperativas[i]) {
				if(taquillas[i].getIdentificador()==codigoPaqueteAEliminar) {
					taquillas[i] = null;
				}
			}
		}
		throw new IllegalArgumentException("El paquete no exite");
	}
	
	/**
	 * Comprueba si el paquete ha pasado de fecha
	 * @param hoy
	 * @param paquete
	 * @throws IllegalArgumentException si {hoy==null}
	 */
	
	public void PaquetePasadoFecha(LocalDate hoy, Package paquete) {
		if (hoy == null)
			throw new IllegalArgumentException("La fecha de hoy no puede ser null.");
		if(paquete.expirado(hoy))
			paquete.setDevuelto(true);
	}
	
	/**
	 * Retorna la posicion en la que se encuentra el paquete
	 * @param paquetebuscado
	 * @return la posicion
	 * @throws AssertionError si no encuentra la posicion del paquete
	 */
	
	private int posicionPackage(Package paquetebuscado) {
		if(paquetebuscado == null) {
			throw new IllegalArgumentException("El paquete introducido es null, introduzca un paquete creado.");
		}
		for(int i = 0;i<taquillas.length;i++) {
			if(paquetebuscado == taquillas[i])
				return i;
		}
		throw new AssertionError("no encontrada la posicion del paquete");
	}	
}