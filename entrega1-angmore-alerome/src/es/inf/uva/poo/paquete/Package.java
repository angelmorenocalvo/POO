/**
 * @author Alejandro Romero Pacho
 * @author Angel Moreno Calvo
 */
package es.inf.uva.poo.paquete;
import java.time.*;
import java.util.Arrays;
import java.util.ArrayList;
/**
 * Es un tipo de dato que equivale a los paquetes enviados por amazon
 * En esta implementacion el paquete constara de un codigo y una ficha de expirado,
 * con ello tendremos la posibilidad de conocer si un paquete esta disponible para su recogida o no
 * Todo paquete ira ligado a un codigo que lo referenciara
 * @author OMEN
 *
 */

public class Package {
	
	private String identificador; 
	private LocalDate fecha; 
	private boolean recogido; 
	private boolean devuelto; 
	private int precio; //*
	private boolean pagado; //*
	private boolean certificada; //*
	private String[] dni; //*
	
	// constructores -- uno para certificado y dni

	/**
	 * Inicialización de un paquete en el cual podemos poner el precio.
	 * @param codigo el identificador del paquete.
	 * @param fechaFin fecha en la que el paquete expira.
	 * @param coste el precio del paquete creado.
	 * 
	 * @throws IllegalArgumentException cuando {@code codigo == null}.
	 * @throws IllegalArgumentException cuando {@code fechaFin == null}.
	 * @throws IllegalArgumentException cuando codigo es diferente de 10 digitos.
	 * @throws IllegalArgumentException cuando no es entero.
	 * @throws IllegalArgumentException cuando el ultimo caracter no coincide con la suma de
	 * los anteriores.
	 * @throws IllegalArgumentException cuando {@code coste < 0}
	 */
	
	public Package(String codigo, LocalDate fechaFin, int coste) {
		if(codigo ==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
				
		if(fechaFin ==null)
			throw new IllegalArgumentException("La fecha no puede tomar el valor null");
		if (codigo.length()!=10 ) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene 10 carateres");}
		if(noEsEntero(codigo)) {
			throw new IllegalArgumentException("LLamada incorrecta al identificador paquete, identificador debe tener solo enteros.");
		}
		if (!comprobacionCodigo(codigo)) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene bien el número de verificación");}
		if (coste<0) {
			throw new IllegalArgumentException("El coste del paquete no puede ser negativo.");
		}
		
		identificador = codigo;
		fecha =  fechaFin;
		recogido = false;
		devuelto = false;
		precio = coste;
		certificada = false;
		pagado = (coste == 0);
	}
	
	/**
	 * Inicialización de un paquete sin la pedida del precio.
	 * @param codigo el identificador del paquete.
	 * @param fechaFin fecha en la que el paquete expira.
	 * 
	 * Excepciones:
	 * @throws IllegalArgumentException cuando {@code codigo == null}.
	 * @throws IllegalArgumentException cuando {@code fechaFin == null}.
	 * @throws IllegalArgumentException cuando codigo es diferente de 10 digitos.
	 * @throws IllegalArgumentException cuando no es entero.
	 * @throws IllegalArgumentException cuando el ultimo caracter no coincide con la suma de
	 * los anteriores.
	 */
	
	public Package(String codigo, LocalDate fechaFin) {
		if(codigo ==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
				
		if(fechaFin ==null)
			throw new IllegalArgumentException("La fecha no puede tomar el valor null");
		if (codigo.length()!=10 ) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene 10 carateres");}
		if(noEsEntero(codigo)) {
			throw new IllegalArgumentException("LLamada incorrecta al identificador paquete, identificador debe tener solo enteros.");
		}
		if (!comprobacionCodigo(codigo)) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene bien el número de verificación");}
		
		identificador = codigo;
		fecha =  fechaFin;
		recogido = false;
		devuelto = false;
		certificada = false;
		precio = 0;
		pagado = true;
	}
	/**
	 * Inicialización de package con la que se puede hacer certificado.
	 * 
	 *  Excepcciones:
	 *  
	 * @throws IllegalArgumentException cuando {@code codigo == null}.
	 * @throws IllegalArgumentException cuando {@code fechaFin == null}.
	 * @throws IllegalArgumentException cuando codigo es diferente de 10 digitos.
	 * @throws IllegalArgumentException cuando no es entero.
	 * @throws IllegalArgumentException cuando el ultimo caracter no coincide con la suma de
	 * los anteriores.
	 * @throws IllegalArgumentException cuando {@code coste < 0}
	 * @throws IllegalArgumentException cuando el paquete sea certificado y no haya al menos un DNI
	 * @throws IllegalArgumentException cuando algún DNI es NULL.
	 *
	 */
	public Package(String codigo, LocalDate fechaFin, int coste, boolean certificado,String[] dniRecogidaAutorizada ) {
	
		if(codigo == null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
				
		if(fechaFin == null)
			throw new IllegalArgumentException("La fecha no puede tomar el valor null");
		if (codigo.length()!=10 ) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene 10 carateres");}
		if(noEsEntero(codigo)) {
			throw new IllegalArgumentException("LLamada incorrecta al identificador paquete, identificador debe tener solo enteros.");
		}
		if (!comprobacionCodigo(codigo)) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene bien el número de verificación");}
		if (coste<0) {
			throw new IllegalArgumentException("El coste del paquete no puede ser negativo.");
		}
		if(certificado && (dniRecogidaAutorizada.length == 0))
			throw new IllegalArgumentException("Se necesita un DNI minimo para poder ser un paquete certificado");
		for(String i :dniRecogidaAutorizada	) {
			if (i == null) {
				throw new IllegalArgumentException("Ningun elemento de la lista de DNIs puede ser null, recuerde rellenarlo entero y de forma correcta");
			}
		}
		identificador = codigo;
		fecha =  fechaFin;
		recogido = false;
		devuelto = false;
		precio = coste;
		dni = Arrays.copyOf(dniRecogidaAutorizada, dniRecogidaAutorizada.length);
		certificada = certificado;
		pagado = (coste == 0);
		
	}
	
	/**
	 * Obtener el codigo que identifica al paquete.
	 * @return String de 10 digitos con el codigo del paquete
	 */
	
	public String getIdentificador() {
		return identificador;
	}
	
	
	/**
	 * Obtener si el paquete ha sido recogido por el cliente.
	 * @return un valor boolean.
	 */
	
	public boolean getRecogido() {
		
		return recogido;
	}
	/**
	 * obtener el precio del paquete
	 * @return el valor del precio del paquete en tipo entero
	 */
	
	public int getPrecio() {
		return precio;
	}
	
	/**
	 * Obtener si el paquete se encuentra en estado pagado.
	 * @return si el paquete esta pagado se encuentra a {@code true}, si no esta pagado {@code false}
	 */
	public boolean getPagado() {
		return pagado;
	}
	
	/**
	 * Obtener si el paquete es certificado o no.
	 * @return devuelve un booleano.
	 */
	public boolean getCertificado() {
		
		return certificada;
	}
	
	/**
	 * Obtener si el paquete ha sido devuelto a la central.
	 * @return un valor boolean.
	 */
	
	public boolean getDevuelto() {
		return devuelto;
	}
	/**
	 * Añadir Dnis a la lista. Es necesario que el paquete sea certificado.
	 * 
	 * @throws IllegalArgumentException si el paquete no es certificado
	 * @throws IllegalArgumentException si no haya al menos un DNI
	 * @throws IllegalArgumentException cuando algún DNI es NULL.
	 */
	public void setDni(String[] dniAnadidos) {
		if(!certificada) {
			throw new IllegalArgumentException("El paquete necesita ser certificado para poder anadir dni");
		}
		if((dniAnadidos.length == 0))
			throw new IllegalArgumentException("No se puede añadir una lista vacia");
		for(String i :dniAnadidos) {
			if (i == null) {
				throw new IllegalArgumentException("Ningun elemento de la lista de DNIs puede ser null, recuerde rellenarlo entero y de forma correcta");
			}
			
			ArrayList<String> aList = new ArrayList<>(Arrays.asList(dni));
			aList.addAll(Arrays.asList(dniAnadidos));
			dni = aList.toArray(new String[aList.size()]);
			
		}
		
		
	}
	
	/**
	 * funcion pedida
	 * Consulta el valor de la variable fecha
	 * @return una fecha de la clase date
	 */
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	/**
	 * modifica el valor de la variable recogido
	 * @param valor booleano
	 */
	
	public void setRecogido(boolean estado) {
		recogido = estado;
	}
	
	
	/**
	 * modifica el valor de la variable devuelto
	 * @param valor booleano
	 */
	
	public void setDevuelto(boolean estado) {
		devuelto = estado;
	}
	
	/**
	 * Cambiar el valor del codigo identificador del paquete
	 * @param codigo nuevo valor para el identificador codigo. Cualquier string es admitido
	 * @throws IllegalArgumentException si {codigo==null}
	 * @throws IllegalArgumentException si {codigo.length!=10}
	 * @throws IllegalArgumentException si {noEsEntero(codigo)}
	 * @throws IllegalArgumentException si {!comprobacionCodigo(codigo)}
	 */
	
	public void setIdentificador(String codigo) {
		if(codigo ==null)
			throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, identificador == null");
				
		if (codigo.length()!=10 ) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene 10 carateres");}
		if(noEsEntero(codigo)) {
			throw new IllegalArgumentException("LLamada incorrecta al identificador paquete, identificador debe tener solo enteros.");
		}
		if (!comprobacionCodigo(codigo)) 
			{throw new IllegalArgumentException("llamada incorrecta al identificador del paquete, el identificador no tiene bien el número de verificación");}
		identificador = codigo;
	}
	
	/**
	 * El paquete pasa a estar pagado y su precio ya es 0.
	 *
	 * @throws IllegalArgumentException cuando el paquete ya esta pagado.
	 */
	public void setPagado() {
		if (pagado)
			throw new IllegalArgumentException("No se puede pagar un paquete que ya se encuentra pagado");
		pagado = true;
		precio = 0;
	}
	
	/**
	 * Obtener la lista de dnis de las personas que pueden acceder al paquete.
	 * @return un array de strings.
	 * 
	 * @throws IllegalArgumentException si el paquete no es certificado.
	 */
	public String[] getDni() {
		if(!certificada) {
			throw new IllegalArgumentException("No se puede acceder a la lista de dnis si no es un paquete certificado");
		}
		return dni;
	}
	
	
	
	/**
	 * Comprobar si el paquete ha expirado su fecha de recogida.
	 * @param hoy es la fecha del dia en el que estamos para saber si nos hemos pasado de fecha
	 * @return devuelve un valor boolenao, en el caso de que la fecha haya pasado sera true
	 * @throws IllegalArgumentException si {hoy==null}
	 */
	
	public boolean expirado(LocalDate hoy) {
		if(hoy == null)
			throw new IllegalArgumentException("La fución expirado necesita que la variable hoy != null");
		
		
		return (fecha.compareTo(hoy)>0);
	}
	
	/**
	 * Quitamos un dni de la lista, pero al menos tiene que quedar uno.
	 * @param dniQuitar un dni que quiere ser borrado de la lista del paquete
	 * Exceptions:
	 * @throws IllegalArgumentException si el paquete no es certificado
	 * @throws IllegalArgumentException si {@code (dniQuitar == null)}
	 * @throws IllegalArgumentException si la url no esta incluida en la lista de dnis del paquete
	 */
	
	public void quitarDni(String dniQuitar) {
		if(!certificada)
			throw new IllegalArgumentException("El paquete debe ser certificado para poder quitar un dni");
		if(dniQuitar == null)
			throw new IllegalArgumentException("dni no puede ser null");
		if(dni.length<2)
			throw new IllegalArgumentException("Tiene que haber al menos un dni identificativo");
		boolean bandera = false;
		for(String i :dni	) {
			if (i == dniQuitar) {
				bandera = true;
			}
		}
		if (!bandera)
			throw new IllegalArgumentException("El dni a quitar debe estar en la lista de dnis del paquete");
		
		ArrayList<String> aList = new ArrayList<>(Arrays.asList(dni));
		aList.remove(dniQuitar);
		dni = aList.toArray(new String[aList.size()]);
	}
	
	/**
	 * Quitamos un dni de la lista, pero al menos tiene que quedar uno.
	 * @param dniAnadir un dni que se quiere anadir a la lista del paquete, debe ser tipo String.
	 * Exceptions:
	 * @throws IllegalArgumentException si el paquete no es certificado
	 * @throws IllegalArgumentException si {@code (dniAnadir == null)}
	 * @throws IllegalArgumentException si la url  esta incluida en la lista de dnis del paquete
	 */
	
	public void addDni(String dniAnadir) {
		if(!certificada)
			throw new IllegalArgumentException("El paquete debe ser certificado para poder quitar un dni");
		if(dniAnadir == null)
			throw new IllegalArgumentException("dni no puede ser null");
		if(dni.length<2)
			throw new IllegalArgumentException("Tiene que haber al menos un dni identificativo");
		boolean bandera = false;
		for(String i :dni	) {
			if (i == dniAnadir) {
				bandera = true;
			}
		}
		if (bandera)
			throw new IllegalArgumentException("El dni a quitar no debe estar en la lista de dnis del paquete");
		
		ArrayList<String> aList = new ArrayList<>(Arrays.asList(dni));
		aList.add(dniAnadir);
		dni = aList.toArray(new String[aList.size()]);
	}
	
	/**
	 * Comprueba si un objeto es igual al paquete. El paquete se considera igual si tienen el mismo identificador.
	 * 
	 * @param p Objeto, hace la comparación para cualquier objeto.
	 * 
	 * @return Retornara {@code true} si el objeto es un paquete y tiene el mismo identificador. En el caso contrario sera {@code false}
	 * 
	 */
	@Override
	public boolean equals(Object p) {
		if (p == null) return false;
	    
		if (p == this) return true;
	    
		if (!(p instanceof Package))return false;
		Package paquete = (Package)p;
		return (!getIdentificador().equals(paquete.getIdentificador()));
		
		
	}
	/**
	 * Esta funcion esta creada para que el codigo no produzca error.
	 * @return {@code 0}
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	
	/**
	 * 
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder("Package with identifer: ");
		buffer.append(getIdentificador());
		buffer.append(". Date: ");
		buffer.append(fecha.toString());
		if (getPrecio() != 0) {
			buffer.append(".Its a package with cash on delivery. Have a price of: ");
			buffer.append(getPrecio());
			if(getPagado()) {
				buffer.append("And it's paid out");
			}
			else {
				buffer.append("without paying");
			}
		}
		if (getCertificado()) {
			buffer.append(". The package is cetificate. The DNI that can pick up the package are: ");
			for(String  i : getDni() ) {
				buffer.append(i);
				buffer.append(", ");
			}
			buffer.deleteCharAt(buffer.length()-2);
			
			if(getRecogido())
				buffer.append("The package has been colleted");
			else
				buffer.append("The package hasn't been collected");
			if (getDevuelto())
				buffer.append("state: returned");
			else
				buffer.append("state: in");
		}
		
		return buffer.toString();
	}
	
	/**
	 * Comprobar el codigo recibido es correcto
	 * @return valor booleano, true si es correcto.
	 */
	
	private boolean comprobacionCodigo(String code) { // hay que llamarlo despues de haber comprobado de que hay 10 numeros,si no verga
		long codigo = Long.parseLong(code) /10;
		long valorFinal = Long.parseLong(code) %10;
		long suma = 0;
		for (int i = 0; i<9; i++) {
			suma = suma  + codigo %10;
			codigo = codigo/10;
		}
		suma = suma%10;
		return (suma == valorFinal);

	}
	
	/**
	 * Consulta si el condigo no es un string numerico
	 * @param codigo
	 * @return un valor booleano
	 * Dicho valor booleano sera false si el string esta lleno de char numericos y true si 
	 * hay algun string no numerico
	 */
	
	private boolean noEsEntero(String codigo) {
		for(char i: codigo.toCharArray()) {
			if((i>57 || i<48))
				return true;
		}
		return false;
	}
}